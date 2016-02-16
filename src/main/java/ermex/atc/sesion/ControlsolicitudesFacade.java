/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Controlsolicitudes;
import ermex.atc.entidad.SolicitudesInternet;
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
public class ControlsolicitudesFacade extends AbstractFacade<Controlsolicitudes> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlsolicitudesFacade() {
        super(Controlsolicitudes.class);
    }
    public Controlsolicitudes findBySolicitud(SolicitudesInternet solicitud)
    {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("ermex");
        EntityManager em1= emf.createEntityManager();
        Query query= em1.createNamedQuery("Controlsolicitudes.findBySolicitud", Controlsolicitudes.class);
        query.setParameter("solicitud", solicitud);  
        return  (Controlsolicitudes) query.getSingleResult();
    }
}
