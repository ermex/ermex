package ermex.atc.controlador;

import ermex.atc.entidad.Ratificaciones;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Procesoratificacion;
import ermex.atc.sesion.RatificacionesFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
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

@Named("ratificacionesController")
@SessionScoped
public class RatificacionesController implements Serializable {

    @EJB
    private ermex.atc.sesion.RatificacionesFacade ejbFacade;
    private List<Ratificaciones> items = null;
    private Ratificaciones selected;

    public RatificacionesController() {
    }
    public List<Ratificaciones> getByStatus() {
        return getFacade().findByStatus(true);
    }
    public Ratificaciones getSelected() {
        return selected;
    }

    public void setSelected(Ratificaciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RatificacionesFacade getFacade() {
        return ejbFacade;
    }
    public Date getFechaInicioMin(){
        Calendar c2 = new GregorianCalendar();
        c2.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        c2.set(c2.get(Calendar.YEAR),0, 1);
        return c2.getTime();
    }
    public Date getFechaInicioMax(){
        Calendar c2 = new GregorianCalendar();
        c2.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        c2.set(c2.get(Calendar.YEAR),11,31);
        return c2.getTime();
    }
    public Ratificaciones prepareCreate() {
        selected = new Ratificaciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RatificacionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RatificacionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RatificacionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ratificaciones> getfindByStatus() {
        return items = getFacade().findByStatus(true);
    }
    
    public List<Ratificaciones> getItems() {
        return items = getFacade().findAllOrder();
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                }if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                }
                else {
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
                    if("Transaction marked for rollback.".equals(msg))
                    {
                        JsfUtil.addErrorMessage("No se pudo realizar la acción. Porque hay gestores en ratificación");
                    }else if (msg.contains("Ya hay dos ratificacion en el mismo a"))
                    {
                        JsfUtil.addErrorMessage("Ya hay dos ratificaciones en el mismo año");
                    }
                    else
                    {                       
                        JsfUtil.addErrorMessage(msg);
                    }
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Ratificaciones getRatificaciones(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Ratificaciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ratificaciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ratificaciones.class)
    public static class RatificacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RatificacionesController controller = (RatificacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ratificacionesController");
            return controller.getRatificaciones(getKey(value));
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
            if (object instanceof Ratificaciones) {
                Ratificaciones o = (Ratificaciones) object;
                return getStringKey(o.getIdratificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ratificaciones.class.getName()});
                return null;
            }
        }

    }

}
