package ermex.atc.controlador;

import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.sesion.CatalogoimagenesFacade;

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

@Named("catalogoimagenesController")
@SessionScoped
public class CatalogoimagenesController implements Serializable {

    @EJB
    private ermex.atc.sesion.CatalogoimagenesFacade ejbFacade;
    private List<Catalogoimagenes> items = null;
   // private List<Catalogoimagenes> itemsActivos = null;
    private List<Catalogoimagenes> imgSelect=null;
    private Catalogoimagenes selected;

    public CatalogoimagenesController() {
    }

    public Catalogoimagenes getSelected() {
        return selected;
    }

    public List<Catalogoimagenes> getImgSelect() {
        return imgSelect;
    }

    public void setImgSelect(List<Catalogoimagenes> imgSelect) {
        this.imgSelect = imgSelect;
    }

    public void setSelected(Catalogoimagenes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CatalogoimagenesFacade getFacade() {
        return ejbFacade;
    }

    public Catalogoimagenes prepareCreate() {
        this.selected = new Catalogoimagenes();
        System.out.println("Estamos en preparetCA");
        initializeEmbeddableKey();
        return selected;
    }
    
    public void imgSeleccionada(Catalogoimagenes img)
    {
        if (img!=null) {
            
        }
    }
    public List<Catalogoimagenes> itemsActivos()
    {
        List <Catalogoimagenes> imgActivos;
        imgActivos=ejbFacade.itemxActivos();
        return imgActivos;
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCatalogoImg").getString("CatalogoimagenesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCatalogoImg").getString("CatalogoimagenesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCatalogoImg").getString("CatalogoimagenesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Catalogoimagenes> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCatalogoImg").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCatalogoImg").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Catalogoimagenes getCatalogoimagenes(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Catalogoimagenes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Catalogoimagenes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Catalogoimagenes.class)
    public static class CatalogoimagenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatalogoimagenesController controller = (CatalogoimagenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catalogoimagenesController");
            return controller.getCatalogoimagenes(getKey(value));
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
            if (object instanceof Catalogoimagenes) {
                Catalogoimagenes o = (Catalogoimagenes) object;
                return getStringKey(o.getIdentificador());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Catalogoimagenes.class.getName()});
                return null;
            }
        }

    }

}
