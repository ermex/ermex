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
    public List<Gestores> findByNoStatus(String a){
//            Query query = em.createQuery("SELECT i FROM Instituciones i where i.idorganismo.idorganismo = :o ").setParameter("o", a);
//            return (List<Instituciones>) query.getResultList();
        TypedQuery<Gestores> query =
        em.createNamedQuery("Gestores.findByNoStatus",Gestores.class).setParameter("status","baja");
        List<Gestores> results = query.getResultList();
        return results;
    }
    public Gestores findByGestor(String gestor){
        TypedQuery<Gestores> query =
        em.createNamedQuery("Gestores.findByGestor",Gestores.class).setParameter("gestor",gestor);
        return query.getSingleResult();
    }
  
}
