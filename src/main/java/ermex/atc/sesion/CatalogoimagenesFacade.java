/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.EscenasProcesadas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.config.QueryType;

/**
 *
 * @author gilberto
 */
@Stateless
public class CatalogoimagenesFacade extends AbstractFacade<Catalogoimagenes> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoimagenesFacade() {
        super(Catalogoimagenes.class);
    }
    
    public List<Catalogoimagenes> itemxActivos()
    {
        List<Catalogoimagenes>  result;
        TypedQuery <Catalogoimagenes> query= em.createNamedQuery("Catalogoimagenes.findByStatus", Catalogoimagenes.class);
        query.setParameter("status", "activo");
        result= query.getResultList();
        return  result;
    }
    
    public List<String> obtenerSatelies()
    {
        String senetencia="select satelite from escenas_procesadas group by satelite order by satelite desc;";
        List<String> satelite;
        Query query=em.createNativeQuery(senetencia);
        satelite=query.getResultList();
        return satelite;
    }
    public List<String> obtenerModo(int satelite)
    {
        List<String> modonivel;
        String senetencia="select modo from escenas_procesadas where satelite=? group by modo";
        Query query=em.createNativeQuery(senetencia);
        query.setParameter(1, satelite);
        modonivel=query.getResultList();
        return  modonivel;
    }
    
    public List<String> obtenerNivel(int satelite)
    {
        List<String> modonivel;
        String senetencia="select nivel from escenas_procesadas where satelite=? group by nivel";
        Query query=em.createNativeQuery(senetencia);
        query.setParameter(1, satelite);
        modonivel=query.getResultList();
        return  modonivel;
    }
    
}
