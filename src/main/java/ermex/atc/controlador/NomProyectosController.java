package ermex.atc.controlador;

import ermex.atc.entidad.NomProyectos;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.sesion.NomProyectosFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("nomProyectosController")
@SessionScoped
public class NomProyectosController implements Serializable {

    @EJB
    private ermex.atc.sesion.NomProyectosFacade ejbFacade;
    private List<NomProyectos> items = null;
    private NomProyectos selected;

    public NomProyectosController() {
    }

    public NomProyectos getSelected() {
        return selected;
    }

    public void setSelected(NomProyectos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NomProyectosFacade getFacade() {
        return ejbFacade;
    }

    public NomProyectos prepareCreate() {
        selected = new NomProyectos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NomProyectosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NomProyectosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NomProyectosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<NomProyectos> getItems() {
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

    public NomProyectos getNomProyectos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<NomProyectos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<NomProyectos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = NomProyectos.class)
    public static class NomProyectosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NomProyectosController controller = (NomProyectosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nomProyectosController");
            return controller.getNomProyectos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof NomProyectos) {
                NomProyectos o = (NomProyectos) object;
                return getStringKey(o.getNumero());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NomProyectos.class.getName()});
                return null;
            }
        }

    }

}
