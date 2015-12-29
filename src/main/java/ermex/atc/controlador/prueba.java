/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import ermex.atc.entidad.Instituciones;
import ermex.atc.entidad.Organismos;
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
public class prueba implements Serializable {
    private Long  dependencia;
    private Long  organismo;
    private Long institucion;
    private Instituciones i;
    @EJB
    private ermex.atc.sesion.OrganismosFacade ejbFacadeOrganismo;
    @EJB
    private ermex.atc.sesion.InstitucionesFacade ejbFacadeInstitucion;
    
    /**
     * Creates a new instance of prueba
     */
    
    public List<Organismos> getOrganismosXDependencia() {     
        return ejbFacadeOrganismo.findorganismosdependencia(dependencia); 
    }
    
    public List<Instituciones> getInstitucionXOrganismo() {
        return ejbFacadeInstitucion.findOrganismos(organismo); 
    }
    
    public prueba() {
//        dependencia = Long.parseLong("1");
////        organismo = Long.parseLong("1");
//        institucion = Long.parseLong("1");
    }

    public Long getDependencia() {
        return dependencia;
    }

    public void setDependencia(Long dependencia) {
        this.dependencia = dependencia;
    }

    public Long getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Long organismo) {
        this.organismo = organismo;
    }

    public Long getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Long institucion) {
        this.institucion = institucion;
    }

    public Instituciones getI() {
        return i;
    }

    public void setI(Instituciones i) {
        this.i = i;
    }
    
}
