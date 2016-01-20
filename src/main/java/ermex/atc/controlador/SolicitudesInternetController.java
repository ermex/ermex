package ermex.atc.controlador;

import ermex.atc.entidad.SolicitudesInternet;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Gestores;
import ermex.atc.sesion.SolicitudesInternetFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.UploadedFile;

@Named("solicitudesInternetController")
@SessionScoped
public class SolicitudesInternetController implements Serializable {

    @EJB
    private ermex.atc.sesion.SolicitudesInternetFacade ejbFacade;
    private List<SolicitudesInternet> items = null;
    private SolicitudesInternet selected;
    private Gestores selectGestores;
    private UploadedFile file;

    public Gestores getSelectGestores() {
        return selectGestores;
    }

    public void setSelectGestores(Gestores selectGestores) {
        this.selectGestores = selectGestores;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public SolicitudesInternetController() {
    }

    public SolicitudesInternet getSelected() {
        return selected;
    }

    public void setSelected(SolicitudesInternet selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SolicitudesInternetFacade getFacade() {
        return ejbFacade;
    }

    public SolicitudesInternet prepareCreate() {
        selected = new SolicitudesInternet();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        FacesMessage mensaje=null;
        FacesContext context= FacesContext.getCurrentInstance();
        if (selected.getStatus()==2) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetUpdated"));
        }else
        {
             mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos no actualizados","Verifique dato");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    public void leerTxt()
    {
        if (file!=null) {
            System.out.println("El valor de File" + file);
        }
        else
            System.out.println("Es nulo file");
    }

    public void destroy() {
        System.out.println("Valores al eliminar" + selected.getStatus() + selected.getSolicitud());
       // persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetDeleted"));
       
        selected.setStatus(0);
          persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SolicitudesInternet> getItems() {
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

    public SolicitudesInternet getSolicitudesInternet(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<SolicitudesInternet> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SolicitudesInternet> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SolicitudesInternet.class)
    public static class SolicitudesInternetControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudesInternetController controller = (SolicitudesInternetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudesInternetController");
            return controller.getSolicitudesInternet(getKey(value));
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
            if (object instanceof SolicitudesInternet) {
                SolicitudesInternet o = (SolicitudesInternet) object;
                return getStringKey(o.getSolicitud());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SolicitudesInternet.class.getName()});
                return null;
            }
        }

    }

}
