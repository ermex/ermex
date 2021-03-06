/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Gestores;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ermex
 */
@Stateless
public class GestoresFacade extends AbstractFacade<Gestores> {
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GestoresFacade() {
        super(Gestores.class);
    }
    
    //Metodos del programador
    public List<Gestores> findByStatusActivo(String status)
    {
    //Regresa la lista de los gestores que no tenga el status  "parametro->nostatus"  
            
        TypedQuery<Gestores> query =
        em.createNamedQuery("Gestores.findByStatus",Gestores.class).setParameter("status",status);
        List<Gestores> results = query.getResultList();
        return results;
    }
    public List<Gestores> findByNoStatus(String nostatus)
    {
    //Regresa la lista de los gestores que no tenga el status  "parametro->nostatus"  
            
        TypedQuery<Gestores> query =
        em.createNamedQuery("Gestores.findByNoStatus",Gestores.class).setParameter("status",nostatus);
        List<Gestores> results = query.getResultList();
        return results;
        
        //Query query = em.createQuery("SELECT i FROM Instituciones i where i.idorganismo.idorganismo = :o ").setParameter("o", a);
        //return (List<Instituciones>) query.getResultList();
    }
    
    public List<Gestores> findByNoStatus(String status1,String status2,String status3)
    {
        
        Query query = em.createQuery("SELECT g FROM Gestores g WHERE g.status != :status1 and g.status != :status2 and g.status != :status3 order by g.gestor desc ").setParameter("status1", status1).setParameter("status2", status2).setParameter("status3", status3);
        return (List<Gestores>) query.getResultList();
    }
    public List<Gestores> findByErmex()
    {
        
        Query query = em.createQuery("SELECT g FROM Gestores g WHERE g.gestor LIKE 'ermex____'");
        return (List<Gestores>) query.getResultList();
    }
    public Gestores findByGestor(String gestor)
    {
    //Regresa el gestor que tiene id "parametro->gestor"  
        
        TypedQuery<Gestores> query =
        em.createNamedQuery("Gestores.findByGestor",Gestores.class).setParameter("gestor",gestor);
        return query.getSingleResult();
    }
    
   public List<Gestores> findByStatusActivoCompra(String status, String status1, String status2)
    {
        //Regresa los gestores activos y los de compra
        String senetencia="SELECT g FROM Gestores g WHERE g.status !=:status and g.status !=:status1 and g.status !=:status2 order by g.gestor desc";
        Query query =   em.createQuery(senetencia,Gestores.class);
        query.setParameter("status", status);
        query.setParameter("status1", status1);
        query.setParameter("status2", status2);
        return (List<Gestores>) query.getResultList();
    }
  
    public List<Object> reporteMensualProcesadas()
    {
    //Regresa el gestor que tiene id "parametro->gestor"  
        Query query=em.createNativeQuery("select tac.satelite,tf.mes,tf.anio,tac.acom acomulado\n" +
"			from\n" +
"			(\n" +
"				select ta.satelite,tm.mes,ta.anio\n" +
"				from\n" +
"				(\n" +
"					select satelite, count (*) mes from escenas_procesadas \n" +
"					where to_char(fechap,'yyyy-mm-dd') between '2015-11-01' and '2015-11-29'\n" +
"					group by satelite\n" +
"				)tm\n" +
"				full outer JOIN\n" +
"				(\n" +
"					select satelite, count (*) anio from escenas_procesadas \n" +
"					where to_char(fechap,'yyyy-mm-dd') between '2015-01-01' and '2015-11-29'\n" +
"					group by satelite\n" +
"				)ta on tm.satelite=ta.satelite\n" +
"			)tf\n" +
"			full outer JOIN\n" +
"			(\n" +
"				select satelite, count (*) acom from escenas_procesadas \n" +
"				where  to_char(fechap,'yyyy-mm-dd') between '2003-01-01' and '2015-11-29'\n" +
"				group by satelite\n" +
"			)tac on tf.satelite=tac.satelite order by tac.satelite DESC");
        
    return (List<Object>) query.getResultList();       
    }
}
