/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Histdesignagestor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author FABY
 */
@Stateless
public class HistdesignagestorFacade extends AbstractFacade<Histdesignagestor> {

     @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistdesignagestorFacade() {
        super(Histdesignagestor.class);
    }
    
    public List<Histdesignagestor> findAllByGestor(String gestor)
    {
        TypedQuery<Histdesignagestor> query =
        em.createNamedQuery("Histdesignagestor.findByGestor",Histdesignagestor.class).setParameter("gestor",gestor);
        List<Histdesignagestor> results = query.getResultList();
        return results;
    }
}
