/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Procesoratificacion;
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
public class ProcesoratificacionFacade extends AbstractFacade<Procesoratificacion> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoratificacionFacade() {
        super(Procesoratificacion.class);
    }
    
    public List<Procesoratificacion> findAllOrder()
    {
        TypedQuery<Procesoratificacion> query =
        em.createNamedQuery("Procesoratificacion.findAll",Procesoratificacion.class);
        List<Procesoratificacion> results = query.getResultList();
        return results;
    }
    public List<Procesoratificacion> findAllByRatificacion(long idratificacion)
    {
        TypedQuery<Procesoratificacion> query =
        em.createNamedQuery("Procesoratificacion.findByRatificacion",Procesoratificacion.class).setParameter("idratificacion",idratificacion);
        List<Procesoratificacion> results = query.getResultList();
        return results;
    }
    public List<Procesoratificacion> findByStatus(boolean status)
    {
        TypedQuery<Procesoratificacion> query =
        em.createNamedQuery("Procesoratificacion.findByStatus",Procesoratificacion.class).setParameter("status",status);
        List<Procesoratificacion> results = query.getResultList();
        return results;
    }
}
