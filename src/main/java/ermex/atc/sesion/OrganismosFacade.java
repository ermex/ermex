/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Organismos;
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
public class OrganismosFacade extends AbstractFacade<Organismos> {
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganismosFacade() {
      
        super(Organismos.class);
    }
    
    public List<Organismos> findorganismosdependencia(long a){
        //    Query query = em.createQuery("SELECT o FROM Organismos o where o.iddependencia.iddependencia = :s ").setParameter("s", a);
        //    return (List<Organismos>) query.getResultList();
        TypedQuery<Organismos> query =
        em.createNamedQuery("Organismos.findByDependencia",Organismos.class).setParameter("iddependencia",a);
        List<Organismos> results = query.getResultList();
        return results;
    }    
}
