/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
    public reportesController() {
    }
    
}
