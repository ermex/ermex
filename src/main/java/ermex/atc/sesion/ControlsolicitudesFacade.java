/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Controlsolicitudes;
import ermex.atc.entidad.SolicitudesInternet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
   public List<Controlsolicitudes> findByUsuario(String usuario)
   {
       String sentencia="Select * from controlsolicitudes where idpersonalatencion=?";
         Query query= em.createNativeQuery(sentencia, Controlsolicitudes.class);
        
        query.setParameter(1, usuario);
        return (List<Controlsolicitudes>) query.getResultList();
   }
   public List<Controlsolicitudes> findByUsuariostatus(String usuario, int status)
   {
       String senetencia= "select * from controlsolicitudes where idpersonalatencion=? and status=?";
       Query query = em.createNativeQuery(senetencia, Controlsolicitudes.class);
       query.setParameter(1, usuario);
       query.setParameter(2, status);
       return  (List<Controlsolicitudes>) query.getResultList();
   }
}
