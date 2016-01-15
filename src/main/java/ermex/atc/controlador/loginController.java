/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.sesion.PersonalatencionusuariosFacade;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ermex
 */
public class loginController {

    /**
     * Creates a new instance of loginController
     */
    private Personalatencionusuarios usuario;
    private PersonalatencionusuariosFacade facadeUsuario;
    public Personalatencionusuarios getUsuario() {
        return usuario;
    }

    public void setFacadeUsuario(PersonalatencionusuariosFacade facdeUsuario) {
        this.facadeUsuario = facdeUsuario;
    }

    public PersonalatencionusuariosFacade getFacadeUsuario() {
        return facadeUsuario;
    }

    public void setUsuario(Personalatencionusuarios usuario) {
        this.usuario = usuario;
    }
    
    public loginController() {
        usuario=new Personalatencionusuarios();        
    }    
    public void autenticar() throws IOException
    {
        boolean login=false;
        FacesMessage mensaje=null;  

        FacesContext context= FacesContext.getCurrentInstance();
        ExternalContext contExternal=context.getExternalContext();
        
        facadeUsuario= new PersonalatencionusuariosFacade();
        System.out.println("Estamos en el loginController");
        usuario=facadeUsuario.acceso(usuario.getUsuario(),usuario.getPwd());
        
        if (usuario!= null) {
            login=true;            
                contExternal.redirect("index.xhtml");

        }
        
        if (login==true) {
             mensaje= new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido","Datos Correctos");
        }
        else
        {
         mensaje= new FacesMessage(FacesMessage.SEVERITY_WARN, "Error","Datos incorerectos");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
       
    }
}
