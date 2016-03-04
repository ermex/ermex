package ermex.atc.controlador;

import ermex.atc.clases.sessionBean;
import ermex.atc.entidad.Controlsolicitudes;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Imagnesolicitudes;
import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.entidad.SolicitudesInternet;
import ermex.atc.sesion.ControlsolicitudesFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

@Named("controlsolicitudesController")
@SessionScoped
public class ControlsolicitudesController implements Serializable {

    @EJB
    private ermex.atc.sesion.ControlsolicitudesFacade ejbFacade;
    private List<Controlsolicitudes> items = null;
    private Controlsolicitudes selected;
    private final  String usuario;
    private Personalatencionusuarios responsable;
    private List<Imagnesolicitudes> imagensolicitud;
    private int evento=0;
    private int editar=4;
    private String titulo="Solicitudes Asignadas";
    
    public ControlsolicitudesController() {
        this.usuario=sessionBean.getUserName();
        //this.usuario="juan";
    }

    public Controlsolicitudes getSelected() {
        return selected;
    }

    public void setSelected(Controlsolicitudes selected) {
        this.selected = selected;
    }

    public String getTitulo() {
        return titulo;
    }
    

    public List<Imagnesolicitudes> getImagensolicitud() {
        if (selected!=null) {
            this.imagensolicitud=selected.getSolicitud().getImagnesolicitudesList();
        }
        return imagensolicitud;
    }

    protected void setEmbeddableKeys() {
    }

    public int getEditar() {
        return editar;
    }

    public void setEditar(int editar) {
        this.editar = editar;
    }

    protected void initializeEmbeddableKey() {
    }

    private ControlsolicitudesFacade getFacade() {
        return ejbFacade;
    }

    public String getUsuario() {
        return usuario;
    }

    public Personalatencionusuarios getResponsable() {
        return responsable;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public void setResponsable(Personalatencionusuarios responsable) {
        this.responsable = responsable;
    }
    public List<Imagnesolicitudes> imgSolicitud()
    {
        List <Imagnesolicitudes> list = null;
        if (selected!=null) {
            list=selected.getSolicitud().getImagnesolicitudesList();
        }
        return  list;
    }

    public Controlsolicitudes prepareCreate() {
        selected = new Controlsolicitudes();
        initializeEmbeddableKey();
        return selected;
    }
    //metodo para asignar responsable de atender la solicitud
public void asignarResponsable(SolicitudesInternet solicitud) throws ParseException
{
    if (solicitud!=null) {
   selected=solicitud.getControlsolicitudes();
        if (selected!=null) {
               if (responsable!=null) {
                   SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                   Date date= new Date();
                   selected.setIdpersonalatencion(responsable);
                   selected.setFechaasignacion(formatter.parse(obtenerFecha()));
                   update();
                   responsable=null;
                   items=getFacade().findByUsuariostatus(usuario, 4);
            }else
               {
                   System.out.println("responsable es nulo");
               }
        }else
        {
            System.out.println("No se encontro el resultado ");
        }
    }
}
 public void cancelarEdit(RowEditEvent event)
 {
     selected=ejbFacade.find(selected.getIdcontrolsolicitud());
     for (int i = 0; i < items.size(); i++) {
         if (items.get(i).equals(selected)) {
             items.set(i, selected);
             i=items.size();
         }
     }
 }

 public void cancelAndEditSolicitud() throws ParseException
 {
     SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
     if (selected != null) {
         if (evento==1) {
             System.out.println("Estamos terminando");
             selected.setFechatermino(formatter.parse(obtenerFecha()));
             selected.setStatus(2);
         } else if (evento == 2) {
             System.out.println("Estamos cancelando");
            selected.setFechacancelacion(formatter.parse(obtenerFecha()));
            selected.setStatus(3);
         }
         update();
     }
     
 }
 
 public void actualizarItems(int i)
 {
     if (usuario!=null) {
         items=ejbFacade.findByUsuariostatus(usuario, i);
         if (i==4) {
             titulo="Solicitudes Asignadas";
         }
         else
         {
             if (i==2) {
             titulo="Solicitudes Terminadas";    
             }
             else
             {
                 if (i==3) {
                     titulo="Solicitudes Canceladas";
                 }
             }
         }
     }
 }
public void resetResponsable()
{
    responsable=null;
}
public String obtenerFecha()
{
        Date date= new Date();
        DateFormat converTime= new SimpleDateFormat("MM-dd-yyyy");
        converTime.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        return converTime.format(date);
}
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ControlsolicitudesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ControlsolicitudesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ControlsolicitudesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Controlsolicitudes> getItems() {
        if (items == null) {
            items = getFacade().findByUsuariostatus(usuario,4);
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction!=PersistAction.UPDATE) {
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

    public Controlsolicitudes getControlsolicitudes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Controlsolicitudes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Controlsolicitudes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Controlsolicitudes.class)
    public static class ControlsolicitudesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ControlsolicitudesController controller = (ControlsolicitudesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "controlsolicitudesController");
            return controller.getControlsolicitudes(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Controlsolicitudes) {
                Controlsolicitudes o = (Controlsolicitudes) object;
                return getStringKey(o.getIdcontrolsolicitud());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Controlsolicitudes.class.getName()});
                return null;
            }
        }

    }
    //metodos del programador
    public void asignarSolictud(SolicitudesInternet sol)
    {
        
        selected=sol.getControlsolicitudes();
        if (this.selected!=null) {
    System.out.println("Control solicitudes no es nulo");
        }else
        System.out.println("Control solicitudes es nulo");
        
    }
        
}
