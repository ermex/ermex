package ermex.atc.controlador;

import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Cambiopersona;
import ermex.atc.entidad.Personas;
import ermex.atc.sesion.CambiopersonaFacade;

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

@Named("cambiopersonaController")
@SessionScoped
public class CambiopersonaController implements Serializable {

    @EJB
    private ermex.atc.sesion.CambiopersonaFacade ejbFacade;
    private List<Cambiopersona> items = null;
    private Cambiopersona selected;
    private Personas selectedPersona;

    public CambiopersonaController() {
    }

    public Personas getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(Personas selectedPersona) {
        this.selectedPersona = selectedPersona;
    }
    public void seleccionarPersona(Personas selectedPersona)
    {
        this.selectedPersona=selectedPersona;
    }
    public List<Cambiopersona> getItemsByPersona() {
        if(selectedPersona == null)
        {return getFacade().findAllByPersona(Long.parseLong("1"));
        }
        return getFacade().findAllByPersona(selectedPersona.getIdpersona());
    }
    public Cambiopersona getSelected() {
        return selected;
    }

    public void setSelected(Cambiopersona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CambiopersonaFacade getFacade() {
        return ejbFacade;
    }

    public Cambiopersona prepareCreate() {
        selected = new Cambiopersona();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CambiopersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CambiopersonaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CambiopersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cambiopersona> getItems() {
        return items = getFacade().findAll();
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
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

    public Cambiopersona getCambiopersona(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Cambiopersona> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cambiopersona> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cambiopersona.class)
    public static class CambiopersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CambiopersonaController controller = (CambiopersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cambiopersonaController");
            return controller.getCambiopersona(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cambiopersona) {
                Cambiopersona o = (Cambiopersona) object;
                return getStringKey(o.getIdcp());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cambiopersona.class.getName()});
                return null;
            }
        }

    }

}
