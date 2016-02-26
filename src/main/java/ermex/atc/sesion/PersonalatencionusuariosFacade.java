/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Personalatencionusuarios;
import java.sql.ResultSet;
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
        Personalatencionusuarios user = null;
        EntityManagerFactory emf;
        EntityManager emFacade;
        emf= Persistence.createEntityManagerFactory("ermex");
        emFacade= emf.createEntityManager();
        
       String sentencia="Select * from personalatencionusuarios where usuario = ? and pwd=? and status=1";
       Query query = emFacade.createNativeQuery(sentencia, Personalatencionusuarios.class);
       query.setParameter(1, usuario);
       query.setParameter(2, pwd);
        try {
         user=(Personalatencionusuarios)query.getSingleResult();   
         
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("valores del login ");
        System.out.println(pwd);
        System.out.println(usuario);

        return user;
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
