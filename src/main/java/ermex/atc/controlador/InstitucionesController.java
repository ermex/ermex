package ermex.atc.controlador;

import ermex.atc.entidad.Instituciones;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Organismos;
import ermex.atc.sesion.InstitucionesFacade;

import java.io.Serializable;
import java.util.HashMap;
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

@Named("institucionesController")
@SessionScoped
public class InstitucionesController implements Serializable {

    @EJB
    private ermex.atc.sesion.InstitucionesFacade ejbFacade;
    private List<Instituciones> items = null;
    private Instituciones selected;
    
    @EJB
    private ermex.atc.sesion.OrganismosFacade ejbFacadeOrganismo;
    private long dependencia=1;
    private List<Organismos> itemsDependencia=null;
    private HashMap<String, String> tipo=null;
    

    public InstitucionesController() {
        tipo = new HashMap<>();
        tipo.put("Universidad Pública", "Universidad Pública");
        tipo.put("Centro Público de Investigación", "Centro Público de Investigación");
        tipo.put("Gobierno Municipal", "Gobierno Municipal");
        tipo.put("Gobierno Federal", "Gobierno Federal");
        tipo.put("Gobierno Estatal", "Gobierno Estatal");
    }

    public Instituciones getSelected() {
        return selected;
    }

    public void setSelected(Instituciones selected) {
        this.selected = selected;
        try
        {
           dependencia=Integer.parseInt(selected.getIdorganismo().getIddependencia().getIddependencia().toString());
        }catch(Exception e){
           dependencia=1;
        }
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstitucionesFacade getFacade() {
        return ejbFacade;
    }

    public Instituciones prepareCreate() {
        selected = new Instituciones();
        initializeEmbeddableKey();
        return selected;
    }
    public void preparate(Organismos organismo) {
        this.selected = new Instituciones();
        this.selected.setIdorganismo(organismo);
        initializeEmbeddableKey();
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstitucionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstitucionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstitucionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Instituciones> getItems() {
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

    public Instituciones getInstituciones(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Instituciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Instituciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the dependencia
     */
    public long getDependencia() {
            return dependencia;       
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(long dependencia) {
        this.dependencia = dependencia;
    }

    @FacesConverter(forClass = Instituciones.class)
    public static class InstitucionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstitucionesController controller = (InstitucionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "institucionesController");
            return controller.getInstituciones(getKey(value));
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
            if (object instanceof Instituciones) {
                Instituciones o = (Instituciones) object;
                return getStringKey(o.getIdinstitucion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Instituciones.class.getName()});
                return null;
            }
        }
        
        

    }

    public List<Organismos> getItemsDependencia() {
        return ejbFacadeOrganismo.findorganismosdependencia(this.getDependencia());        
    }

    public void setItemsDependencia(List<Organismos> itemsDependencia) {
        this.itemsDependencia = itemsDependencia;
    }

    public HashMap<String, String> getTipo() {
        return tipo;
    }

    public void setTipo(HashMap<String, String> tipo) {
        this.tipo = tipo;
    }
        
}
