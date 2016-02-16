/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Instituciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ermex
 */
@Stateless
public class InstitucionesFacade extends AbstractFacade<Instituciones> {
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitucionesFacade() {
        super(Instituciones.class);
    }
    
    //Metodos del programador
    public List<Instituciones> findAllOrder()
    {
        TypedQuery<Instituciones> query =
        em.createNamedQuery("Instituciones.findAll",Instituciones.class);
        List<Instituciones> results = query.getResultList();
        return results;
    }
    
    public List<Instituciones> findOrganismos(long idorganismo)
    {
    //Regresa la lista de instituciones deacuerdo a cierto organismo  "parametro->idorganismo"          
        TypedQuery<Instituciones> query =
        em.createNamedQuery("Instituciones.findByOrganismos",Instituciones.class).setParameter("idorganismo",idorganismo);
        List<Instituciones> results = query.getResultList();
        return results;
        
        //Query query = em.createQuery("SELECT i FROM Instituciones i where i.idorganismo.idorganismo = :o ").setParameter("o", a);
        //return (List<Instituciones>) query.getResultList();
    }  
    
}
