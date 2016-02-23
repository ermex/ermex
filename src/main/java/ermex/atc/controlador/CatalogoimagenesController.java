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
    private List<String> satelites;
    private List<String> modo;
    private List<String> nivel;

    public CatalogoimagenesController() {
    }

    public List<String> getSatelites() {
        return satelites;
    }

    public List<String> getModo() {
        return modo;
    }

    public List<String> getNivel() {
        return nivel;
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
        if (satelites==null) {
            satelites=ejbFacade.obtenerSatelies();
        }
        initializeEmbeddableKey();
        return selected;
    }
    
    public void obtenerMoVi(){
        int sateli;
        try {
            if (selected.getSatelite()!=null) {
             sateli=Integer.parseInt(selected.getSatelite());
             modo=ejbFacade.obtenerModo(sateli);
             nivel=ejbFacade.obtenerNivel(sateli);
             
            }
        } catch (Exception e) {
        }
        
    }
    public List<Catalogoimagenes> itemsActivos()
    {
        List <Catalogoimagenes> imgActivos;
        imgActivos=ejbFacade.itemxActivos();
        return imgActivos;
    }
    public void obtenerResolucion()
    {
        String sateliteR;
        String nivelR;
        if (selected!=null) {
            sateliteR=selected.getSatelite();
            selected.setResolucion(null);
            selected.setTipo(null);
            nivelR=selected.getTipo();

            
            
            if (sateliteR.compareTo("7")==0 || sateliteR.compareTo("6")==0) {
                if (nivelR.compareTo("G")==0 ||nivelR.compareTo("C")==0) {
                    selected.setResolucion(1.5);
                }else
                {
                    selected.setResolucion(6.0);
                }
                
            }else	
            {
                if (sateliteR.compareTo("5")==0) {
                    if (nivelR.compareTo("F")==0 ||nivelR.compareTo("T")==0 ) {
                        selected.setResolucion(2.5);
                    }else
                    {
                        if (nivelR.compareTo("HM")==0 ||nivelR.compareTo("A")==0 ||nivelR.compareTo("B")==0) {
                            selected.setResolucion(5.0);
                        }
                        else
                        {
                            if (nivelR.compareTo("X")==0 ||nivelR.compareTo("J")==0 ) {
                                selected.setResolucion(10.0);
                            }else
                            {
                                System.out.println("no se encontro el nivel en sateliet 5");
                            }
                        }
                    }
                }
                else
                {
                    if (sateliteR.compareTo("4")==0) {
                        if (nivelR.compareTo("I")==0 ||nivelR.compareTo("X")==0  ) {
                             selected.setResolucion(20.0);
                        }else
                        {
                            if (nivelR.compareTo("M")==0) {
                             selected.setResolucion(10.0);
                            }
                        }
                    }
                    else
                    {
                        if (sateliteR.compareTo("2")==0) {
                            if (nivelR.compareTo("X")==0) {
                                selected.setResolucion(20.0);
                            }else
                            {
                                if (nivelR.compareTo("P")==0) {
                                    selected.setResolucion(10.0);
                                }
                            }
                        }
                    }
                           
                }
            }
        }
    }
    public void create() {
        if (selected!=null) {
            String idcatalosoli;
            idcatalosoli= selected.getSatelite() + selected.getTipo()+ selected.getNivel();
            selected.setIdentificador(idcatalosoli);
        }
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
