/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.EscenasProcesadas;
import ermex.atc.entidad.SolicitudesInternet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<Object> tiposimagens() {
        List<Object> resultado=null;
        String consulta="select satelite, modo, nivel, tipo from \n" +
                "(\n" +
                "select satelite, modo, nivel, tipo, count(tipo) contador from escenas_procesadas group by satelite, modo, nivel, tipo order by satelite desc\n" +
                    ") c1 where c1.contador > 15";
        try {
            Query query = em.createNativeQuery(consulta);
            resultado=query.getResultList();
        } catch (Exception e) {
        }
        return resultado;
    }
}
