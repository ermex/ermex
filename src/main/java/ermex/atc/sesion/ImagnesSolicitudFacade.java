/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.Imagnesolicitudes;
import ermex.atc.entidad.SolicitudesInternet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gilberto
 */
@Stateless
public class ImagnesSolicitudFacade extends AbstractFacade<Imagnesolicitudes> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagnesSolicitudFacade() {
        super(Imagnesolicitudes.class);
        
    }
    public void createImgS(String solicitud, String identificador){
        String sentences="insert into imagnesolicitudes(solicitud,identificador) values(?,?)";
        Query query= em.createNativeQuery(sentences);
        query.executeUpdate();
        em.flush();
        em.getTransaction();
        
    }   
    
    public List<Imagnesolicitudes> imgSolBayidsol(String id)
    {
        Query query=em.createNamedQuery("Imagnesolicitudes.findSolicitud",Imagnesolicitudes.class);
        query.setParameter("solicitud", id);
        return query.getResultList();
    }
}
