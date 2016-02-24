package ermex.atc.controlador;

import ermex.atc.clases.sessionBean;
import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;

import ermex.atc.sesion.PersonalatencionusuariosFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@ManagedBean(name="personalatencionusuariosController", eager=true)
@SessionScoped
public class PersonalatencionusuariosController implements Serializable {

    @Inject
    private ermex.atc.sesion.PersonalatencionusuariosFacade ejbFacade;
    private List<Personalatencionusuarios> items = null;
    private Personalatencionusuarios selected;
    
   

    public PersonalatencionusuariosController() {
    }

    public Personalatencionusuarios getSelected() {
        return selected;
    }

    public void setSelected(Personalatencionusuarios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonalatencionusuariosFacade getFacade() {
        return ejbFacade;
    }

    public Personalatencionusuarios prepareCreate() {
        selected = new Personalatencionusuarios();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonalatencionusuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonalatencionusuariosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonalatencionusuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Personalatencionusuarios> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction==PersistAction.CREATE) {
                        getFacade().create(selected);
                    }else
                    {
                    getFacade().edit(selected);
                    }
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Personalatencionusuarios getPersonalatencionusuarios(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Personalatencionusuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Personalatencionusuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public  boolean login(String user, String pwd){        
        FacesContext context1= FacesContext.getCurrentInstance();
        ExternalContext contExternal=context1.getExternalContext();
        boolean log=false;
        if (user!=null && pwd!=null) {
            ejbFacade= new PersonalatencionusuariosFacade();
           Personalatencionusuarios  acceso = ejbFacade.acceso(user,pwd);
            if (null!=acceso) {
                log=true;                
            }else
            {
                log=false;
            }
        }else
        {
            System.out.println("El valor de U es nulo");
        }
        return log;
    }

    @FacesConverter(forClass = Personalatencionusuarios.class)
    public static class PersonalatencionusuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonalatencionusuariosController controller = (PersonalatencionusuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personalatencionusuariosController");
            return controller.getPersonalatencionusuarios(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Personalatencionusuarios) {
                Personalatencionusuarios o = (Personalatencionusuarios) object;
                return getStringKey(o.getUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Personalatencionusuarios.class.getName()});
                return null;
            }
        }

    }
    public List<Personalatencionusuarios> usuariosActivos()
    {
        List<Personalatencionusuarios> activos;
        activos= ejbFacade.usuariosActivos();
        return activos;
    }

}
