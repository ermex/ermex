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
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("solicitudesInternetController")
@SessionScoped
public  class SolicitudesInternetController implements Serializable {

    @EJB
    private ermex.atc.sesion.SolicitudesInternetFacade ejbFacade;
    private List<SolicitudesInternet> items = null;
    private SolicitudesInternet selected;
    private SolicitudesInternet selectedRespaldo;
    private Gestores selectGestores;
    private String  noPeriodo1;
    private String noPeriodo2;
    private String noPeriodo3;
    private int radioS;
     
     
    public SolicitudesInternetController() {
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase();
    }
    public  void iniciarValores()
    {
        System.out.println("Iniciando valores");
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase();
        selectedRespaldo=selected;
    }

    public String getNoPeriodo2() {
        return noPeriodo2;
    }

    public void setNoPeriodo2(String noPeriodo2) {
        this.noPeriodo2 = noPeriodo2;
    }

    public String getNoPeriodo3() {
        return noPeriodo3;
    }

    public void setNoPeriodo3(String noPeriodo3) {
        this.noPeriodo3 = noPeriodo3;
    }
    public String getNoPeriodo1() {
        return noPeriodo1;
    }

    public void setNoPeriodo1(String periodoS) {
        int radioSelec;
       if (periodoS.compareTo("dos")==0) {
            noPeriodo2="dos";
            noPeriodo1="uno";
            noPeriodo3=null;
            radioSelec=2;
        }else
        {
            if (periodoS.compareTo("tres")==0) {
                noPeriodo3="tres";
                noPeriodo1="uno";
                noPeriodo2="dos";
                radioSelec=3;
            }else
            {
               this.noPeriodo1 = periodoS; 
                noPeriodo2=null;
                noPeriodo3=null;
                radioSelec=1;
            }
        }
        resetPeriodos(radioSelec);
             
      
    }
    
    public Gestores getSelectGestores() {
        return selectGestores;
    }
    private void  resetPeriodos(int datos)
    {
        System.out.println("Valor de la variable radioS " + radioS);
        if (radioS==3 && datos==1 || datos==1 && radioS==2 
                ) {
            selected.setPeriodo3I(null);
            selected.setPeriodo3F(null);
            selected.setPeriodo2I(null);
            selected.setPeriodo2F(null);
        }else
        {
            if (radioS==3 && datos==2) {
                selected.setPeriodo3I(null);
                selected.setPeriodo3F(null);
            }
            else
            {
                if (datos==1) {
                    selected.setPeriodo3I(null);
                    selected.setPeriodo3F(null);
                    selected.setPeriodo2I(null);
                    selected.setPeriodo2F(null);
                }
            }
        }
        radioS=datos;
        
    }

    public void setSelectGestores(Gestores selectGestores) {
        this.selectGestores = selectGestores;
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
        if (selected.getStatus()==2) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetUpdated"));
             mensaje= new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Actualizada","Se ha modifica la solicitud" + selected.getSolicitud());
        }else
        {
             mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solicitud no Actualizada","La solicitud no se puede actualizar" + selected.getSolicitud());
        }
        
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
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
                    selected.setGestor(selectGestores.getGestor());
                    selectGestores=null;
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

    private int periodosBase() {
        int contadorPeriodo=1;
            if (selected !=null) 
            {
                if (null!=selected.getPeriodo3I()) {
                    contadorPeriodo=3;
                    noPeriodo3="tres";
                    noPeriodo2="dos";
                }else
                {
                    if (null!=selected.getPeriodo2I()) {
                        contadorPeriodo=2;
                        noPeriodo2="dos";
                    }
                }   
            }
            System.out.println("Numero  de perio de la solictud " + contadorPeriodo);
        return contadorPeriodo;
    }
    public void cancelarEdit()
    {
        System.out.println("Valor de respaldo "+ selectedRespaldo.getPeriodo2F());
        System.out.println("valor de respaldo "+selectedRespaldo.getPeriodo2I());
        System.out.println("valor de select" + selected.getPeriodo2F());
        System.out.println("valor de select" + selected.getPeriodo2I());
        selected=selectedRespaldo;
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
