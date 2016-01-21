package ermex.atc.controlador;

import ermex.atc.entidad.Personas;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Dependencias;
import ermex.atc.entidad.Instituciones;
import ermex.atc.entidad.Organismos;
import ermex.atc.sesion.PersonasFacade;
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
import org.primefaces.model.UploadedFile;

@Named("personasController")
@SessionScoped
public class PersonasController implements Serializable {

    @EJB
    private ermex.atc.sesion.PersonasFacade ejbFacade;
    private List<Personas> items = null;
    private Personas selected=new Personas();
    
    private HashMap<String, String> tipo=null;
    private Dependencias selectedDepedencia;
    private Organismos selectedOrganismo;
    @EJB
    private ermex.atc.sesion.OrganismosFacade ejbFacadeOrganismo;
    @EJB
    private ermex.atc.sesion.InstitucionesFacade ejbFacadeInstitucion;
    private UploadedFile imagen;
    private String nombreidoficialanv;
    private String nombreidoficialrev;
    private String nombrecredoficialanv;
    private String nombrecredoficialrev;
    private String nombreimgcurp;
    private String nombrefoto;
    private String nombrehuellapulgar;
    private String nombrehuellamanoizq;
    private String nombrehuellamanoder;
    private String nombrenombramiento;

    public PersonasController() {
        tipo = new HashMap<>();
        tipo.put("Designador", "D");
        tipo.put("Gestor", "G");
        tipo.put("Designador y Gestor", "A");
    }

    public String getNombreidoficialanv() {
        return nombreidoficialanv;
    }

    public void setNombreidoficialanv(String nombreidoficialanv) {
        this.nombreidoficialanv = nombreidoficialanv;
    }

    public String getNombreidoficialrev() {
        return nombreidoficialrev;
    }

    public void setNombreidoficialrev(String nombreidoficialrev) {
        this.nombreidoficialrev = nombreidoficialrev;
    }

    public String getNombrecredoficialanv() {
        return nombrecredoficialanv;
    }

    public void setNombrecredoficialanv(String nombrecredoficialanv) {
        this.nombrecredoficialanv = nombrecredoficialanv;
    }

    public String getNombrecredoficialrev() {
        return nombrecredoficialrev;
    }

    public void setNombrecredoficialrev(String nombrecredoficialrev) {
        this.nombrecredoficialrev = nombrecredoficialrev;
    }

    public String getNombreimgcurp() {
        return nombreimgcurp;
    }

    public void setNombreimgcurp(String nombreimgcurp) {
        this.nombreimgcurp = nombreimgcurp;
    }

    public String getNombrefoto() {
        return nombrefoto;
    }

    public void setNombrefoto(String nombrefoto) {
        this.nombrefoto = nombrefoto;
    }

    public String getNombrehuellapulgar() {
        return nombrehuellapulgar;
    }

    public void setNombrehuellapulgar(String nombrehuellapulgar) {
        this.nombrehuellapulgar = nombrehuellapulgar;
    }

    public String getNombrehuellamanoizq() {
        return nombrehuellamanoizq;
    }

    public void setNombrehuellamanoizq(String nombrehuellamanoizq) {
        this.nombrehuellamanoizq = nombrehuellamanoizq;
    }

    public String getNombrehuellamanoder() {
        return nombrehuellamanoder;
    }

    public void setNombrehuellamanoder(String nombrehuellamanoder) {
        this.nombrehuellamanoder = nombrehuellamanoder;
    }

    public String getNombrenombramiento() {
        return nombrenombramiento;
    }

    public void setNombrenombramiento(String nombrenombramiento) {
        this.nombrenombramiento = nombrenombramiento;
    }
    
    public HashMap<String, String> getTipo() {
        return tipo;
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile Imagen) {
        this.imagen = Imagen;
    }
    
    public void subirIdOficialAnv(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setIdoficialanv(imagen.getContents()); 
       this.nombreidoficialanv=event.getFile().getFileName();
    }
    
    public void subirIdOficialRev(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setIdoficialrev(imagen.getContents());  
       this.nombreidoficialrev=event.getFile().getFileName();
    }
    public void subirCredOficialAnv(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setCredoficialanv(imagen.getContents()); 
       this.nombrecredoficialanv=event.getFile().getFileName();
    }
    
    public void subirCredOficialRev(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setCredoficialrev(imagen.getContents());  
       this.nombrecredoficialrev=event.getFile().getFileName();
    }
    public void subirHuellaManoDer(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setHuellamanoder(imagen.getContents()); 
       this.nombrehuellamanoder=event.getFile().getFileName();
    }
    
    public void subirHuellaManoIzq(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setHuellamanoizq(imagen.getContents()); 
       this.nombrehuellamanoizq=event.getFile().getFileName();
    }
    
    public void subirHuellaPulgar(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setHuellapulgar(imagen.getContents()); 
       this.nombrehuellapulgar=event.getFile().getFileName();
    }
    public void subirImgCurp(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setImgcurp(imagen.getContents()); 
       this.nombreimgcurp=event.getFile().getFileName();
    }
    
    public void subirFoto(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setFoto(imagen.getContents()); 
       this.nombrefoto=event.getFile().getFileName();
    }
    
    public void subirNombramiento(FileUploadEvent event) {  
       imagen = event.getFile();
       this.selected.setNombramiento(imagen.getContents()); 
       this.nombrenombramiento=event.getFile().getFileName();
    }
    
    public void reset(){
        selected=new Personas();
        selectedDepedencia=null;
        selectedOrganismo=null; 
        nombreidoficialanv="";
        nombreidoficialrev="";
        nombrecredoficialanv="";
        nombrecredoficialrev="";
        nombreimgcurp="";
        nombrefoto="";
        nombrehuellapulgar="";
        nombrehuellamanoizq="";
        nombrehuellamanoder="";
        nombrenombramiento="";
    }
    public Personas getSelected() {
        return selected;
    }

    public void setSelected(Personas selected) {
        this.selected = selected;
        try
        {
            this.selectedOrganismo=selected.getIdinstitucion().getIdorganismo();
            this.selectedDepedencia=selected.getIdinstitucion().getIdorganismo().getIddependencia();
        }catch(Exception e){
//            this.selectedOrganismo=selected.getIdinstitucion().getIdorganismo();
//            this.selectedDepedencia=selected.getIdinstitucion().getIdorganismo().getIddependencia();
        }
    }
    public List<Organismos> getItemOrganismosXDependencia() {
        if(selectedDepedencia== null){
            return ejbFacadeOrganismo.findorganismosdependencia(1);      
        }
        return ejbFacadeOrganismo.findorganismosdependencia(selectedDepedencia.getIddependencia()); 
    }
    
    public List<Instituciones> getItemInstitucionXOrganismo() {
        if(selectedOrganismo== null){
            return ejbFacadeInstitucion.findOrganismos(1);     
        }
        return ejbFacadeInstitucion.findOrganismos(selectedOrganismo.getIdorganismo()); 
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonasFacade getFacade() {
        return ejbFacade;
    }

    public Personas prepareCreate() {
        selected = new Personas();
        initializeEmbeddableKey();
        return selected;
    }
    public void prepare(Instituciones institucion) {
        selected = new Personas();
        selected.setIdinstitucion(institucion);
        initializeEmbeddableKey();
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonasCreated"));
        reset();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonasUpdated"));
        reset();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Personas> getItems() {
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

    public Personas getPersonas(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Personas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Personas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Dependencias getSelectedDepedencia() {
        return selectedDepedencia;
    }

    public void setSelectedDepedencia(Dependencias selectedDepedencia) {
        this.selectedDepedencia = selectedDepedencia;
    }

    public Organismos getSelectedOrganismo() {
        return selectedOrganismo;
    }

    public void setSelectedOrganismo(Organismos selectedOrganismo) {
        this.selectedOrganismo = selectedOrganismo;
    }

    

    @FacesConverter(forClass = Personas.class)
    public static class PersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasController controller = (PersonasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasController");
            return controller.getPersonas(getKey(value));
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
            if (object instanceof Personas) {
                Personas o = (Personas) object;
                return getStringKey(o.getIdpersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Personas.class.getName()});
                return null;
            }
        }

    }

}
