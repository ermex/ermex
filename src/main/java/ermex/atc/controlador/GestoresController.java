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
import java.io.File;
import java.io.FileInputStream;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
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
    private Gestores selected;
    
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
    private String idpersonas;
    private String atributo;
    public GestoresController() {
        status = new HashMap<>();
        status.put("baja", "baja");
        status.put("pendiente", "pendiente");
        status.put("ratificacion", "ratificacion"); 
        status.put("activo", "activo"); 
    }
    
    //*************************** Metodos del programador
    public String onFlowProcess(FlowEvent event) {
        switch(event.getNewStep())
        {
            case "tabPersonas":
                if(this.selectedInstitucion==null){
                    FacesContext.getCurrentInstance().addMessage("successInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecciona la Institución",null));
                    return "tabInstitucion";
                }
                break;
            case "tabImagen":
                if(this.selected.getDesignador()==null || this.selected.getIdpersona()==null){
                    FacesContext.getCurrentInstance().addMessage("successInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecciona el gestor y designador",null));
                    return "tabPersonas";
                }
                break;    
        }
        return event.getNewStep();
    }
    public void subirDesignacion(FileUploadEvent event) {  
       Updesignacion = event.getFile();
       this.selected.setDesignacion(Updesignacion.getContents());
    }
    public void quitarDesignacion() { 
       this.selected.setDesignacion(null);
    }
    public void valueChangeMethod(ValueChangeEvent e) {
        this.selectedOrganismos=null;
        this.selectedInstitucion=null;
    }
    public void reset()
    {
        //Metodo que resetea todos los valores
        selected=new Gestores();
        selectedDependencia=null;
        selectedOrganismos=null;
        selectedInstitucion=null;
    }
    
    public List<Organismos> getItemOrganismosXDependencia() 
    {
        //Metodo que regresa una lista de todos los organismos de la dependencia seleccionada "selectedDependencia"
        
        if(selectedDependencia== null){
            return null;      
        }
        return ejbFacadeOrganismo.findorganismosdependencia(selectedDependencia.getIddependencia()); 
    }
    
    public List<Instituciones> getItemInstitucionXOrganismo() 
    {
        //Metodo que regresa una lista de todas las instituciones del organismo seleccionado "selectedOrganismo"
        
        if(selectedOrganismos== null){
            return null;     
        }
        return ejbFacadeInstitucion.findOrganismos(selectedOrganismos.getIdorganismo()); 
    }

    public List<Personas> getItemsfindGestorInstitucion(String tipo) 
    {
        //Metodo que regresa una lista de todas las personas de la institucion seleccionada "selectedInstitucion" y cierto tipo
        if(this.selectedInstitucion==null){
            return null;
        }
        return ejbFacadePersona.findTipoInstitucion(tipo,this.selectedInstitucion.getIdinstitucion());
    }
    
    public DefaultStreamedContent imagenError()throws IOException
    {
        //Regresa la imagen de error
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath("/resources/images/error-imagen.png");
        File errorImagen = new File(absoluteDiskPath);           
        return new DefaultStreamedContent(new FileInputStream(errorImagen));   
    }
    
    public StreamedContent getImgMini(byte[]img) throws IOException 
    {   
        //Regresa la imagen "img" para mostrarla (se usa para mostrarla en miniatura )
        if(img == null){
            return imagenError();
        }else{
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
        }
    }
    
    public StreamedContent getImgAtributo() throws IOException 
    {
        //Regresa la imagen de acuerdo a los atributos nomgestor idpersonas atributo
        //utilizado para mostrar las imagenes de las personas y de los gestores 
        Personas personaGestor=null;       
        DefaultStreamedContent imagenAtributo=null;
        switch(atributo){
            case "nomgestor":
                if(ejbFacade.findByGestor(nomgestor).getDesignacion()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(ejbFacade.findByGestor(nomgestor).getDesignacion()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "idoficialanv": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));                
                if(personaGestor.getIdoficialanv()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getIdoficialanv()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "idoficialrev":personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));
                if(personaGestor.getIdoficialrev()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getIdoficialrev()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "credoficialanv": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas)); 
                if(personaGestor.getCredoficialanv()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getCredoficialanv()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "credoficialrev": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));
                if(personaGestor.getCredoficialrev()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getCredoficialrev()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "imgcurp": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas)); 
                if(personaGestor.getImgcurp()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getImgcurp()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;    
            case "foto":  personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));
                if(personaGestor.getFoto()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getFoto()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "huellapulgar": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas)); 
                if(personaGestor.getHuellapulgar()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getHuellapulgar()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "huellamanoizq": personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas)); 
                if(personaGestor.getHuellamanoizq()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getHuellamanoizq()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "huellamanoder":  personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));
                if(personaGestor.getHuellamanoder()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getHuellamanoder()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            case "nombramiento":  personaGestor=ejbFacadePersona.findByPersonaGestor(Long.parseLong(idpersonas));
                if(personaGestor.getNombramiento()!=null)
                {
                    imagenAtributo= new DefaultStreamedContent(new ByteArrayInputStream(personaGestor.getNombramiento()));
                }else
                {      
                    imagenAtributo=imagenError();
                }
                break;
            default: imagenAtributo=imagenError();
                     break;    
        }
        return imagenAtributo;       
    }
    
    //***** Getter y Setter 
    public String getNomgestor() {
        return nomgestor;
    }

    public void setNomgestor(String nomgestor) {
        this.nomgestor = nomgestor;
    }

    public String getIdpersonas() {
        return idpersonas;
    }

    public void setIdpersonas(String idpersonas) {
        this.idpersonas = idpersonas;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
    
    public UploadedFile getUpdesignacion() {
        return Updesignacion;
    }

    public void setUpdesignacion(UploadedFile Updesignacion) {
        this.Updesignacion = Updesignacion;
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
    
     //*************************** Metodos por default
    public Gestores getSelected() {
        return selected;
    }
    
    // Modificacion del Programador
    public void setSelected(Gestores selected) {
        this.selected = selected;
        try
        {
            this.selectedDependencia=selected.getIdpersona().getIdinstitucion().getIdorganismo().getIddependencia();
            this.selectedOrganismos=selected.getIdpersona().getIdinstitucion().getIdorganismo();
            this.selectedInstitucion=selected.getIdpersona().getIdinstitucion();         
        }catch(Exception e){
//            this.selectedOrganismo=selected.getIdinstitucion().getIdorganismo();
//            this.selectedDepedencia=selected.getIdinstitucion().getIdorganismo().getIddependencia();
        }
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
        return getFacade().findByNoStatus("prueba","compra","antiguo");
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                }else if(persistAction == PersistAction.CREATE) 
                {
                    getFacade().create(selected);
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
                    if("Bean Validation constraint(s) violated while executing Automatic Bean Validation on callback event:'prePersist'. Please refer to embedded ConstraintViolations for details.".equals(cause.getLocalizedMessage())){
                        msg ="No se pudo crear el Gestor ya que falta algunos datos requeridos:Favor de revisar las restricciones";
                    }
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
