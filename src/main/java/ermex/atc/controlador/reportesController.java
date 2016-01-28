/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import ermex.atc.sesion.reporteFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ermex
 */
@Named(value = "reportesController")
@SessionScoped
public class reportesController implements Serializable {

    /**
     * Creates a new instance of reportesController
     */
    private List<Object> resultadoContoller;
    private Date Fechafinal;
    private Date Fechainicio;
    private int opcion=0;
    private reporteFacade repofk;
    public reportesController() {
        this.repofk= new reporteFacade();
        opcion=0;
    }

    public void setFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }

    public void setFechainicio(Date Fechainicio) {
        this.Fechainicio = Fechainicio;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
    

    public List<Object> getResultadoContoller() {
         resultadoContoller=generarReporte();
        return resultadoContoller;
    }
   private reporteFacade getFacade() {
        return repofk;
    }

    public reporteFacade getRepofk() {
        return repofk;
    }
   
   
    public Date getFechafinal() {
        return Fechafinal;
    }

    public Date getFechainicio() {
        return Fechainicio;
    }
    public int getOpcion() {
        return opcion;
    }
    public List<Object> generarReporte()
    {   
        String fecha1=null;
        String fecha2=null;
        
        if (Fechainicio!=null && Fechafinal!=null &opcion!=0) {
            DateFormat  formatofeha= new SimpleDateFormat("yyyy-MM-dd");
            fecha1= formatofeha.format(Fechainicio);
            fecha2= formatofeha.format(Fechafinal);
            System.out.println("Estamos en elc ontroler con los siguientes valores");
            System.out.println(fecha1);
            System.out.println(fecha2);
            resultadoContoller=getFacade().reporteDepen(fecha1,fecha2, 1);   
        }

         
         return resultadoContoller;
    }
    

    
}
