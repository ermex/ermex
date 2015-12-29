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
    
    public List<Instituciones> findOrganismos(long a){
//            Query query = em.createQuery("SELECT i FROM Instituciones i where i.idorganismo.idorganismo = :o ").setParameter("o", a);
//            return (List<Instituciones>) query.getResultList();
        TypedQuery<Instituciones> query =
        em.createNamedQuery("Instituciones.findByOrganismos",Instituciones.class).setParameter("idorganismo",a);
        List<Instituciones> results = query.getResultList();
        return results;
    }  
    
}
