/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Personalatencionusuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author FABY
 */
@Stateless
public class PersonalatencionusuariosFacade extends AbstractFacade<Personalatencionusuarios> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalatencionusuariosFacade() {
        super(Personalatencionusuarios.class);
    }
    public  Personalatencionusuarios acceso( String usuario, String pwd)
    {
        EntityManagerFactory emf;
        EntityManager emFacade;
        emf= Persistence.createEntityManagerFactory("ermex");
        emFacade= emf.createEntityManager();
        try{
            TypedQuery<Personalatencionusuarios> query =
            emFacade.createNamedQuery("Personalatencionusuarios.findUno",Personalatencionusuarios.class).setParameter("usuario",usuario).setParameter("pwd",pwd);
            return query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }    
    
    public List<Personalatencionusuarios> usuariosActivos()
    {
        List <Personalatencionusuarios> activos;
        TypedQuery query= em.createNamedQuery("Personalatencionusuarios.findByStatus", Personalatencionusuarios.class);
        query.setParameter("status",1);
        activos=query.getResultList();
        return activos;        
    }
}
