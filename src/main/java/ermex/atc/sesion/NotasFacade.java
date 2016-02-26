/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Notas;
import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.entidad.imgEntreNo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FABY
 */
@Stateless
public class NotasFacade extends AbstractFacade<Notas> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotasFacade() {
        super(Notas.class);
    }
    public  List<Object> consultarImagen(String idnota)
    {
               
       EntityManagerFactory emf= Persistence.createEntityManagerFactory("ermex");
       EntityManager emNotas= emf.createEntityManager();
        //List<Object> imgEntreNota=null;
        String funcion="select * from tablanotas(?) as (satelite numeric, KJ text,fecha text, nivelprocedencia text,copia character varying )";
        Query sql= emNotas.createNativeQuery(funcion);
        sql.setParameter(1,idnota);   
        //imgEntreNota=sql.getResultList();
        
        return sql.getResultList();
    }
    public Object obtenerNonuto()
    {
        
        String senetencia="select last_value+1 from numeronota";
        Query query= em.createNativeQuery(senetencia);
        
        return  query.getSingleResult();
                
    }
}
