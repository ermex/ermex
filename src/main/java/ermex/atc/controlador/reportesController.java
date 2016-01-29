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
import java.util.Calendar;

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
    private Date fechalimite;
    private int opcion;
    private reporteFacade repofk;
    private String encabezadoTabla;
    public reportesController() {
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
        asiganrEncabezado();
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

    public String getEncabezadoTabla() {
        return encabezadoTabla;
    }

    public Date getFechalimite() {
        fechalimite=Calendar.getInstance().getTime();
        return fechalimite;
    }
    
    
    //metodo generado por el programador para obtener consulta de los reportes por periodos
    // ya sea por dependencia, organismo, institucion y gestor
    public List<Object> generarReporte()
    {   repofk= new reporteFacade();
        String fecha1=null;
        String fecha2=null;
        
        if (Fechainicio!=null && Fechafinal!=null & opcion!=0) {
            DateFormat  formatofeha= new SimpleDateFormat("yyyy-MM-dd");
            fecha1= formatofeha.format(Fechainicio);
            fecha2= formatofeha.format(Fechafinal);
            resultadoContoller=getFacade().reporteDepen(fecha1,fecha2, this.opcion);  
            opcion=0;
            Fechainicio=null;
            Fechafinal=null;
        }        

         
         return resultadoContoller;
    }
    //metodo generado por el programador para cabiar en encabezado de la tabla de list de reportes
    private void asiganrEncabezado()
    {
        System.out.println("Valor del opcion en el case " + opcion);
        switch(opcion)
        {
            case 1 :
                encabezadoTabla="DEPENDENCIA";
                break;
            case 2 :
                encabezadoTabla="ORGANISMO";
                break;
            case 3 :
                encabezadoTabla="INSTITUCION";
                break;
        }
    }

    
}
