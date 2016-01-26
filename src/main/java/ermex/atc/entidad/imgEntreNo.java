/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;

/**
 *
 * @author ermex
 */
public class imgEntreNo implements Serializable{

    private Integer satelite;
    private String jk;
    private String fecha;
    private String nivelProce;
    private String copia;

    public imgEntreNo() {

    }

    public imgEntreNo(Integer satelite, String jk, String fecha, String nivelPro, String copia) {
        this.copia = copia;
        this.jk = jk;
        this.fecha = fecha;
        this.nivelProce = nivelPro;
        this.copia = copia;
    }

    public Integer getSatelite() {
        return satelite;
    }

    public void setSatelite(Integer satelite) {
        this.satelite = satelite;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNivelProce() {
        return nivelProce;
    }

    public void setNivelProce(String nivelProce) {
        this.nivelProce = nivelProce;
    }

    public String getCopia() {
        return copia;
    }

    public void setCopia(String copia) {
        this.copia = copia;
    }

}
