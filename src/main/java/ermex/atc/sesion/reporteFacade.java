/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import java.io.Serializable;
import java.util.ArrayList;
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
public class reporteFacade implements Serializable{

    
    @PersistenceContext(unitName = "ermex")
    private EntityManager em;
    private final EntityManagerFactory emf;
    private final EntityManager emFacade;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
 
    protected EntityManager getEntityManager()  {
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
        List<Object> result=new ArrayList<>();
        String sqldependencia="";
        try 
        {
            switch(a){
                case 1:
                    System.out.println("Nos encontramos en la primera obcion en el facade");
                    sqldependencia ="select * from reportemendep(?,?) as (siglas character varying, img numeric)";
                    break;
                case 2:
                    System.out.println("Valor del opcion en el case " + a);
                    sqldependencia ="select * from reportemenorg(?, ?) as (siglas text, img numeric)";
                    break;
                case 3:
                    System.out.println("Valor del opcion en el case " + a);
                     sqldependencia ="select * from reportemenins(?, ?) as (siglas text, img numeric)";
                    break;
                case 4:
                    sqldependencia ="select * from reportemenges(?, ?) as (gestor text, img numeric)";                     
                    break;
            }
            query=emFacade.createNativeQuery(sqldependencia);
            query.setParameter(1, fechainicial);
            query.setParameter(2, fechafinal);
            result=(List<Object>)query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    } 
    public List<Object> reporteRecibidas( String fechainicial, String fechafinal)
    {
        Query query;
        List<Object> result=new ArrayList<>();
        String sqldependencia="";
        try 
        {
            sqldependencia ="select * from reporteimgrecibidas(?,?) as (satelite numeric, img bigint)";
            query=emFacade.createNativeQuery(sqldependencia);
            query.setParameter(1, fechainicial);
            query.setParameter(2, fechafinal);
            result=(List<Object>)query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }   
    public List<Object> reporteProcesadas( String fechainicial, String fechafinal)
    {
        Query query;
        List<Object> result=new ArrayList<>();
        String sqldependencia="";
        try 
        {
            sqldependencia ="select * from reporteimgprocesadas(?,?) as (satelite numeric, img bigint)";
            query=emFacade.createNativeQuery(sqldependencia);
            query.setParameter(1, fechainicial);
            query.setParameter(2, fechafinal);
            result=(List<Object>)query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }   
}
