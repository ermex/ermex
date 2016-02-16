/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Ratificaciones;
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
public class RatificacionesFacade extends AbstractFacade<Ratificaciones> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatificacionesFacade() {
        super(Ratificaciones.class);
    }
    
    public List<Ratificaciones> findAllOrder()
    {
        TypedQuery<Ratificaciones> query =
        em.createNamedQuery("Ratificaciones.findAll",Ratificaciones.class);
        List<Ratificaciones> results = query.getResultList();
        return results;
    }
}
