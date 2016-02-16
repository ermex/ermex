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
}
