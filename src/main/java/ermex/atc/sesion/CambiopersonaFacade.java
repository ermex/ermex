/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Cambiopersona;
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
public class CambiopersonaFacade extends AbstractFacade<Cambiopersona> {

     @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CambiopersonaFacade() {
        super(Cambiopersona.class);
    }
    
    public List<Cambiopersona> findAllByPersona(Long idpersona)
    {
        TypedQuery<Cambiopersona> query =
        em.createNamedQuery("Cambiopersona.findByPersona",Cambiopersona.class).setParameter("idpersona",idpersona);
        List<Cambiopersona> results = query.getResultList();
        return results;
    }
}
