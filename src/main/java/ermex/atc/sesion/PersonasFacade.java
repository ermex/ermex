/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;


import ermex.atc.entidad.Personas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ermex
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> {
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }
    
    public List<Personas> findTipoInstitucion(String tipo,Long idinstitucion){
            Query query = em.createQuery("SELECT p FROM Personas p WHERE (p.tipo = :tipo OR  p.tipo='A') and p.idinstitucion.idinstitucion = :idinstitucion").setParameter("tipo",tipo).setParameter("idinstitucion",idinstitucion);
            return (List<Personas>) query.getResultList();
//        TypedQuery<Instituciones> query =
//        em.createNamedQuery("Instituciones.findByOrganismos",Instituciones.class).setParameter("idorganismo",a);
//        List<Instituciones> results = query.getResultList();
//        return results;
    } 
}
