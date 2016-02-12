/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.Imagnesolicitudes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
