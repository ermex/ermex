/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Catalogoimagenes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
