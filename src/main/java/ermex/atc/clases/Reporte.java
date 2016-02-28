/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.clases;

/**
 *
 * @author FABY
 */
public class Reporte {
    private String opcion;
    private long total;

    public Reporte(String opcion,long total){
        this.opcion=opcion;
        this.total=total;
    }
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
}
