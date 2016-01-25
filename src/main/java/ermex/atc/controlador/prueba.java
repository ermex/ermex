/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
/**
 *
 * @author ermex
 */

@Named(value = "prueba")
@SessionScoped
public class prueba implements Serializable 
{   
    @EJB
    private ermex.atc.sesion.GestoresFacade ejbFacade;
    private List<Object> reporteMensualProcesadas;
    public prueba(){
    }

    public List<Object> getReporteMensualProcesadas() {
        return reporteMensualProcesadas=ejbFacade.reporteMensualProcesadas();
    }

    public void setReporteMensualProcesadas(List<Object> reporteMensualProcesadas) {
        this.reporteMensualProcesadas = reporteMensualProcesadas;
    }
    public long getProcesadasTotalMensual() {
        long total=0;
        reporteMensualProcesadas=ejbFacade.reporteMensualProcesadas();
        for (Object obj:reporteMensualProcesadas) 
        {
            Object[]aa =(Object[])obj;
            if(aa[1]!=null){
            total=total+(long)aa[1];}
        }
        return total;
      }
    public long getProcesadasTotalAnual() {
        long total=0;
        for (Object obj:reporteMensualProcesadas) 
        {
            Object[]aa =(Object[])obj;
            if(aa[1]!=null){
            total=total+(long)aa[2];}
        }
        return total;
      }
    public long getProcesadasTotalAcumulado() {
        long total=0;
        for (Object obj:reporteMensualProcesadas) 
        {
            Object[]aa =(Object[])obj;
            if(aa[1]!=null){
            total=total+(long)aa[3];}
        }
        return total;
      }    
}
