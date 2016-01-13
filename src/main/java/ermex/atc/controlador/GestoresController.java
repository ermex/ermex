package ermex.atc.controlador;
/*fabiola*/
import ermex.atc.entidad.Gestores;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Dependencias;
import ermex.atc.entidad.Instituciones;
import ermex.atc.entidad.Organismos;
import ermex.atc.entidad.Personas;
import ermex.atc.sesion.GestoresFacade;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("gestoresController")
@SessionScoped

public class GestoresController implements Serializable {
    // Atributos por default
    @EJB
    private ermex.atc.sesion.GestoresFacade ejbFacade;
    private List<Gestores> items = null;
    private Gestores selected=new Gestores();
    // Atributos del Programador
    @EJB
    private ermex.atc.sesion.OrganismosFacade ejbFacadeOrganismo;
    @EJB
    private ermex.atc.sesion.InstitucionesFacade ejbFacadeInstitucion;
    @EJB
    private ermex.atc.sesion.PersonasFacade ejbFacadePersona;    
    private List<Gestores> itemsActivos = null;
    private Dependencias selectedDependencia;
    private Organismos  selectedOrganismos;
    private Instituciones selectedInstitucion;
    private HashMap<String, String> status=null;
    private UploadedFile Updesignacion;
    private String nomgestor;
    
    public GestoresController() {
        status = new HashMap<>();
        status.put("baja", "baja");
        status.put("pendiente", "pendiente");
        status.put("ratificacion", "ratificacion"); 
        status.put("activo", "activo");
    }

    public String getNomgestor() {
        return nomgestor;
    }

    public void setNomgestor(String nomgestor) {
        this.nomgestor = nomgestor;
    }

    public UploadedFile getUpdesignacion() {
        return Updesignacion;
    }

    public void setUpdesignacion(UploadedFile Updesignacion) {
        this.Updesignacion = Updesignacion;
    }

    public void subirDesignacion(FileUploadEvent event) {  
       Updesignacion = event.getFile();
       this.selected.setDesignacion(Updesignacion.getContents());
    }
    
    public StreamedContent getImgDesignacion() throws IOException { 
            System.out.println("");
            String a=nomgestor;
//            if(nomgestor.compareTo("ermex0002")==0||nomgestor.compareTo("ermex0004")==0)
//            {
                return new DefaultStreamedContent(new ByteArrayInputStream(ejbFacade.findByGestor(nomgestor).getDesignacion()));
//            }else
//            {
//                this.getItems();
//                return new DefaultStreamedContent(new ByteArrayInputStream(items.get(493).getDesignacion()));   
//            }                  
        }

    public Dependencias getSelectedDependencia() {
        return selectedDependencia;
    }

    public void setSelectedDependencia(Dependencias selectedDependencia) {
        this.selectedDependencia = selectedDependencia;
    }

    public Organismos getSelectedOrganismos() {
        return selectedOrganismos;
    }

    public void setSelectedOrganismos(Organismos selectedOrganismos) {
        this.selectedOrganismos = selectedOrganismos;
    }

    public Instituciones getSelectedInstitucion() {
        return selectedInstitucion;
    }

    public void setSelectedInstitucion(Instituciones selectedInstitucion) {
        this.selectedInstitucion = selectedInstitucion;
    }

    public List<Gestores> getItemsActivos() {
        return ejbFacade.findByNoStatus("baja");
    }
    public HashMap<String, String> getStatus() {
        return status;
    }

    public void setStatus(HashMap<String, String> status) {
        this.status = status;
    }
    
    public void setItemsActivos(List<Gestores> itemsActivos) {
        this.itemsActivos = itemsActivos;
    }   

    public List<Organismos> getItemOrganismosXDependencia() {
        if(selectedDependencia== null){
            return ejbFacadeOrganismo.findorganismosdependencia(1);      
        }
        return ejbFacadeOrganismo.findorganismosdependencia(selectedDependencia.getIddependencia()); 
    }
    
    public List<Instituciones> getItemInstitucionXOrganismo() {
        if(selectedOrganismos== null){
            return ejbFacadeInstitucion.findOrganismos(1);     
        }
        return ejbFacadeInstitucion.findOrganismos(selectedOrganismos.getIdorganismo()); 
    }

    public List<Personas> getItemsfindGestorInstitucion(String tipo) {
        if(this.selectedInstitucion==null){
            return null;
        }
        return ejbFacadePersona.findTipoInstitucion(tipo,this.selectedInstitucion.getIdinstitucion());
    }

    public void reset(){
        selected=new Gestores();
        selectedDependencia=null;
        selectedOrganismos=null;
        selectedInstitucion=null;
    }
    //////////////////////////////////////////////////////
    
    public Gestores getSelected() {
        return selected;
    }

    public void setSelected(Gestores selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GestoresFacade getFacade() {
        return ejbFacade;
    }

    public Gestores prepareCreate() {
        selected = new Gestores();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GestoresCreated"));
        reset();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {        
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GestoresUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GestoresDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Gestores> getItems() {
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

    public Gestores getGestores(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Gestores> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Gestores> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Gestores.class)
    public static class GestoresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GestoresController controller = (GestoresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gestoresController");
            return controller.getGestores(getKey(value));
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
            if (object instanceof Gestores) {
                Gestores o = (Gestores) object;
                return getStringKey(o.getGestor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Gestores.class.getName()});
                return null;
            }
        }

    }

}
