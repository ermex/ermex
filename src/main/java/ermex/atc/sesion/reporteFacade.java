/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author ermex
 */
public class reporteFacade {

    
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;
    private final EntityManagerFactory emf;
    private final EntityManager emFacade;

    protected EntityManager getEntityManager() {
        return em;
    }
    /**
     * Creates a new instance of reporteFacade
     */
    public reporteFacade() {
        this.emf= Persistence.createEntityManagerFactory("ermex");
        this.emFacade= emf.createEntityManager();
    }
    public List<Object> reporteDepen( String fechainicial, String fechafinal, int  a)
    {
        Query query;
        List<Object> resul=null;
        String sqldependencia;
        try {
                    if (a==1) {
            System.out.println("Nos encontramos en la primera obcion en el facade");
            sqldependencia ="select * from reportemendep(?,?) as (siglas character varying, img numeric)";
            query=emFacade.createNativeQuery(sqldependencia);
            query.setParameter(1, fechainicial);
            query.setParameter(2, fechafinal);
            resul=query.getResultList();
        }else
        {
            if (a==2) {
              sqldependencia ="select * from reportemenorg(?, ?) as (siglas text, img numeric)";
            query=emFacade.createNativeQuery(sqldependencia);
            resul=query.getResultList();
            }else
            {
                if (a==3) {
                      sqldependencia ="select * from reportemenins(?, ?) as (siglas text, img numeric)";
                    query=emFacade.createNativeQuery(sqldependencia);
            resul=query.getResultList();
                }else
                {
                  sqldependencia ="select * from reportemenges(?, ?) as (gestor text, nombre text, apellidop text,apellidom text, img numeric)";    
                  query=emFacade.createNativeQuery(sqldependencia);
                resul=query.getResultList();
                }
            }
        }
            
        } catch (Exception e) {
            System.out.println(e);
        }

       return  resul;
    }
    
}
