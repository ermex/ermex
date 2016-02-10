
package ermex.atc.clases;


import ermex.atc.controlador.PersonalatencionusuariosController;
import ermex.atc.entidad.Personalatencionusuarios;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


//@ManagedBean(name = "loginv")
@FacesValidator("LoginValidator")
public class LoginValidator implements Validator {
    
    
    @ManagedProperty(value = "#{personalatencionusuariosController}")
    private PersonalatencionusuariosController usuario;

    String user;
    String pwd;

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

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        user = value.toString();
        HtmlInputSecret uiInputPwd= (HtmlInputSecret) component.getAttributes().get("pwd");
        pwd=uiInputPwd.getSubmittedValue().toString();
        Personalatencionusuarios u = new Personalatencionusuarios();
        u.setUsuario(user);
        u.setPwd(pwd);
        usuario = new PersonalatencionusuariosController();
        usuario.login(u);
        
    }
    

}

