package ermex.atc.controlador;

import ermex.atc.entidad.Notas;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Controlsolicitudes;
import ermex.atc.entidad.Gestores;
import ermex.atc.entidad.Instituciones;
import ermex.atc.entidad.Organismos;
import ermex.atc.entidad.Personas;
import ermex.atc.entidad.imgEntreNo;
import ermex.atc.sesion.NotasFacade;

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

@Named("notasController")
@SessionScoped
public class NotasController implements Serializable {

    @EJB
    private ermex.atc.sesion.NotasFacade ejbFacade;
    private List<Notas> items = null;
    //Declaracion de variables para llenar la tabla de imagenes entregradas en paginaNota.xhtml
    private List<Object> itemsObject=null;
    private Notas selected;    
    private Personas designador;
    private Personas gestor;
    private Instituciones institucion;
    private Organismos organismo;
    private String solicitud;
    private String usuarioGestor;
   
    
    public NotasController() {
    }

    public Notas getSelected() {
        return selected;
    }

    public void setSelected(Notas selected) {
        this.selected = selected;
    }

    public NotasFacade getEjbFacade() {
        return ejbFacade;
    }

    public Personas getDesignador() {
        return designador;
    }

    public Personas getGestor() {
        return gestor;
    }

    public String getUsuarioGestor() {
        return usuarioGestor;
    }

    public Instituciones getInstitucion() {
        return institucion;
    }

    public Organismos getOrganismo() {
        return organismo;
    }

    public String getSolicitud() {
        return solicitud;
    }

    //metodo para obtener las imagenes entregadas a la nota
    public List<Object> getItemsObject() {
        if (selected!=null) {           
            itemsObject=getFacade().consultarImagen(selected.getIdnota());
            
        }
        
        return itemsObject;
    }
    
    //metodo para obtener informacion de la nota.
    public void informacionNota()
    {
        try {
         if (selected!= null) {
            solicitud=selected.getIdcontrolsolicitud().getSolicitud().toString();
            designador=selected.getIdcontrolsolicitud().getGestor().getDesignador();
            gestor=selected.getIdcontrolsolicitud().getGestor().getIdpersona();
            institucion=selected.getIdcontrolsolicitud().getGestor().getIdpersona().getIdinstitucion();
            organismo= selected.getIdcontrolsolicitud().getGestor().getIdpersona().getIdinstitucion().getIdorganismo();
            usuarioGestor=selected.getIdcontrolsolicitud().getGestor().toString();
            //nombreDesigandor= designador.getCargo()+ " " + designador.getNombre()+ " " +designador.getApellidop()+" " + designador.getApellidom();
            //nombreGestor=gestor.getCargo() + " " + gestor.getNombre() + " " + gestor.getApellidop() + " " + gestor.getApellidom();
        }     
            
        } catch (Exception ex) {
             JsfUtil.addErrorMessage(ex,"No se ecnontro informacion en la base de datos");
        }
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotasFacade getFacade() {
        return ejbFacade;
    }

    public Notas prepareCreate() {
        selected = new Notas();
        initializeEmbeddableKey();
        return selected;
    }
    public Notas crearNotaCS(Controlsolicitudes idCs)
    {
        selected=prepareCreate();
        selected.setIdcontrolsolicitud(idCs);
        return selected;
        
    }
public void generarIdNota()
{
    
}
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NotasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NotasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NotasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Notas> getItems() {
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

    public Notas getNotas(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Notas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Notas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Notas.class)
    public static class NotasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotasController controller = (NotasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notasController");
            return controller.getNotas(getKey(value));
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
            if (object instanceof Notas) {
                Notas o = (Notas) object;
                return getStringKey(o.getIdnota());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notas.class.getName()});
                return null;
            }
        }

    }

}
