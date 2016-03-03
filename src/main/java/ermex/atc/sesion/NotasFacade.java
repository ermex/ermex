/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Notas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FABY
 */
@Stateless
public class NotasFacade extends AbstractFacade<Notas> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotasFacade() {
        super(Notas.class);
    }
    public  List<Object> consultarImagen(String idnota)
    {
               
       EntityManagerFactory emf= Persistence.createEntityManagerFactory("ermex");
       EntityManager emNotas= emf.createEntityManager();
        //List<Object> imgEntreNota=null;
        String funcion="select * from tablanotas(?) as (satelite numeric, KJ text,fecha text, nivelprocedencia text,copia character varying )";
        Query sql= emNotas.createNativeQuery(funcion);
        sql.setParameter(1,idnota);   
        //imgEntreNota=sql.getResultList();
        
        return sql.getResultList();
    }
    public Object obtenerNonuto()
    {
        //obtienen el valor de lasecuencia y le suma 1
        String senetencia="select last_value+1 from numeronota";
        Query query= em.createNativeQuery(senetencia);
        
        return  query.getSingleResult();
                
    }
    public List<Notas> notasBayResponsableAndStatus(String responsable,int status)
    {
        String consulta="select n.idnota,n.idcontrolsolicitud,n.nonota,n.nombre,n.noimagen,n.nooficio,n.fechacreacion, n.observaciones,\n" +
                                 "n.status,n.fechaoficio,n.fechaacuse,n.reposiciones,n.dispotivioentrega,n.idorganismo,n.pwdnota from notas n\n" +
                            "join controlsolicitudes as s on (s.idcontrolsolicitud=n.idcontrolsolicitud) where s.idpersonalatencion=? and n.status=?";
        Query query= em.createNativeQuery(consulta, Notas.class);
        query.setParameter(1, responsable);
        query.setParameter(2, status);
        return query.getResultList();
        
    }
}
