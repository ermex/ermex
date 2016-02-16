/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Dependencias;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ermex
 */
@Stateless
public class DependenciasFacade extends AbstractFacade<Dependencias> {
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DependenciasFacade() {
        super(Dependencias.class);
    }
    
    public List<Dependencias> findAllOrder()
    {
        TypedQuery<Dependencias> query =
        em.createNamedQuery("Dependencias.findAll",Dependencias.class);
        List<Dependencias> results = query.getResultList();
        return results;
    }
    
}
