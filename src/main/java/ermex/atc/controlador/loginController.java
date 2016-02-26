/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import ermex.atc.clases.sessionBean;
import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.sesion.PersonalatencionusuariosFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.SessionBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ermex
 */
@Named("loginController")
@SessionScoped
public class loginController implements Serializable{

    /**
     * Creates a new instance of loginController
     */
    private PersonalatencionusuariosFacade facadeUsuario;
    private PersonalatencionusuariosController controlerusuario;
    private static final long serialVersionUID = 1194801825228386363L;
    
   private String user;
   private String pwd;


    public void setFacadeUsuario(PersonalatencionusuariosFacade facdeUsuario) {
        this.facadeUsuario = facdeUsuario;
    }

    public PersonalatencionusuariosFacade getFacadeUsuario() {
        return facadeUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    
    public loginController() {
       
    }    
   
    public void autenticar() throws IOException
    {
        boolean login;
        FacesMessage mensaje=null;
        FacesContext context= FacesContext.getCurrentInstance();
        ExternalContext contExternal=context.getExternalContext();
        controlerusuario= new PersonalatencionusuariosController();
        //se consulta si se enuentra el usuario registrado 
        login=controlerusuario.login(user, pwd);
        if (login==true)
        {
            //se crea una session 
            HttpSession session =  sessionBean.getSession();            
            //se asigna el nombre de la session
            session.setAttribute("username", user);
            contExternal.redirect("./vistas/gestores/List.xhtml");   
             mensaje= new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvendido", user);
        }else
        {
             mensaje= new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR","Datos Incorrectos");
           
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
}
