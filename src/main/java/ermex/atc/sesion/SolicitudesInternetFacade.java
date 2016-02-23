/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.EscenasProcesadas;
import ermex.atc.entidad.Imagnesolicitudes;
import ermex.atc.entidad.SolicitudesInternet;
import java.sql.SQLException;
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
 * @author ermex
 */
@Stateless
public class SolicitudesInternetFacade extends AbstractFacade<SolicitudesInternet> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudesInternetFacade() {
        super(SolicitudesInternet.class);
    }  
  public void createImgS(String solicitud, String identificador){
        String sentences="insert into imagnesolicitudes(solicitud,identificador) values(?,?)";
        Query query= em.createNativeQuery(sentences);
        query.setParameter(1, solicitud);
        query.setParameter(2, identificador);
        query.executeUpdate();
        em.flush();
        
    }
  public void creatImgsoliv(Imagnesolicitudes img)
  {
      getEntityManager().persist(img);
      getEntityManager().flush();
      getEntityManager().refresh(img);
  }  
  //metodo para eliminar un registro de la tabla imagnesolicitudes
  public void deletaImgSoli(Imagnesolicitudes id)
  {
     int id1=id.getIdmagenesolicitud();
     String query="delete from imagnesolicitudes where idmagenesolicitud=?";
      try {
          Query query1= em.createNativeQuery(query);
          query1.setParameter(1, id1);
          query1.executeUpdate();
          em.flush();
      } catch (Exception e) {
          
      }

      
  }
    public List<Imagnesolicitudes> updateRegistro(String id)
    {
        String query="Select * from imagnesolicitudes where solicitud=?";
        List<Imagnesolicitudes> solicitud;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("ermex");
        EntityManager manager= emf.createEntityManager();
        Query consulta= manager.createNativeQuery(query, Imagnesolicitudes.class);
        consulta.setParameter(1, id);
        
        return consulta.getResultList();                
    }
   
    //metodo para obtener las solictudes activas 
    public List<SolicitudesInternet> findByActivos()
    {
        TypedQuery query =em.createNamedQuery("SolicitudesInternet.findByActivos", SolicitudesInternet.class);
         query.setParameter("status", 1);
         return query.getResultList();
    }
        //metodo para obtener las solictudes activas 
    public List<SolicitudesInternet> findByCancelados()
    {
        TypedQuery query =em.createNamedQuery("SolicitudesInternet.findByActivos", SolicitudesInternet.class);
         query.setParameter("status", 2);
         return query.getResultList();
    }
        //metodo para obtener las solictudes activas 
    public List<SolicitudesInternet> findByTerminados()
    {
        TypedQuery query =em.createNamedQuery("SolicitudesInternet.findByActivos", SolicitudesInternet.class);
         query.setParameter("status", 3);
         return query.getResultList();
    }
        public List<SolicitudesInternet> findByAsignados()
    {
        TypedQuery query =em.createNamedQuery("SolicitudesInternet.findByActivos", SolicitudesInternet.class);
         query.setParameter("status", 4);
         return query.getResultList();
    }
}
