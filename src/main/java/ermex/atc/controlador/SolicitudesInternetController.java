package ermex.atc.controlador;

import ermex.atc.entidad.SolicitudesInternet;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.Gestores;
import ermex.atc.sesion.CatalogoimagenesFacade;
import ermex.atc.sesion.SolicitudesInternetFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;

@Named("solicitudesInternetController")
@SessionScoped
public  class SolicitudesInternetController implements Serializable {

    @EJB
    private ermex.atc.sesion.SolicitudesInternetFacade ejbFacade;
    private List<SolicitudesInternet> items = null;
    private List<SolicitudesInternet> itemsRespaldo = null;
    private SolicitudesInternet selected;
    
    //Variables creadas por el programador 
    
    private Gestores selectGestores;
    private Object selctObject;
    private String  noPeriodo1;
    private String noPeriodo2;
    private String noPeriodo3;
    private String fechaMinima;
    private int radioS;
    private List<Object> tiposImg=null;
    private SolicitudesInternet selectedRespaldo;
    @EJB
    private ermex.atc.sesion.CatalogoimagenesFacade facadeCatalogoImg;
    private Date periodo1IR;
    private Date periodo2IR;
    private Date periodo3IR;    
    private DualListModel<Catalogoimagenes> tipos;
    
     
    public SolicitudesInternetController() {
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase();
    }
    @PostConstruct
    public void init()
    {
        List<Catalogoimagenes> catalogoimgSource = getFacadeCatalogoImg().findAll();
        List<Catalogoimagenes> catalogoimgTarget  = new ArrayList<>();
        tipos= new DualListModel<>(catalogoimgSource, catalogoimgTarget);
    }
    public SolicitudesInternet getSelectedRespaldo() {
        return selectedRespaldo;
    }

    public DualListModel<Catalogoimagenes> getTipos() {
        return tipos;
    }

    public void setTipos(DualListModel<Catalogoimagenes> tipos) {
        this.tipos = tipos;
    }
    
    public void setSelectedRespaldo(SolicitudesInternet selectedRespaldo) {
        this.selectedRespaldo = selectedRespaldo;
    }
    

   //metodo creado por el programador para iniciar los valores de las variables
    public  void iniciarValores()
    {
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase(); 
        itemsRespaldo=getFacade().findAll();
        if (selected!=null) {
        selectedRespaldo=ejbFacade.find(selected.getSolicitud());
        }
    }

    public String getNoPeriodo2() {
        return noPeriodo2;
    }

    public Object getSelctObject() {
        return selctObject;
    }

    public void setSelctObject(Object selctObject) {
        this.selctObject = selctObject;
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

    public CatalogoimagenesFacade getFacadeCatalogoImg() {
        return facadeCatalogoImg;
    }
    
    //metodo creado por el procramador para limbiar los periodos al crear una nueva solicitud
    public void setNoPeriodo1(String periodoS) {
        int radioSelec=0;
        if (periodoS!=null) {
               if (periodoS.compareTo("dos")==0)
               {
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
        }
      
        resetPeriodos(radioSelec);
             
      
    }
public void ModoNivel(List<Catalogoimagenes> tipoM)

{
    String tipo="";
    String nivel="";
    if (tipoM!=null) {

        System.out.println("Size de tiposM");
        System.out.println(tipoM);
        for (int i = 0; i < tipoM.size(); i++) {
            tipo=tipo+tipoM.get(i).getTipo();
            nivel=nivel+tipoM.get(i).getNivel();
        }
        
    }else
    {
        System.out.println("los valores de tipoM son nulos");
    }
    System.out.println("valores de nivel y modo");
    System.out.println(tipo);
    System.out.println(nivel);
   selected.setModo(tipo);
   selected.setNivel(nivel);
    
}
    
    public Gestores getSelectGestores() {
        return selectGestores;
    }
    //los periodos se pasan a nulos 
    private void  resetPeriodos(int datos)
    {
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

    public String getMinPeriodo() {
        return fechaMinima;
    }
    //los siguientes tres metodos es para validar los periodos 

    public void minDatePerio1F() {
        if (selected.getPeriodo1I() != null || periodo1IR != selected.getPeriodo1I()) {
          //  fechaMinima = selected.getPeriodo1I();
            obtenerFecha(selected.getPeriodo1I());
        }        
            periodo1IR = selected.getPeriodo1I();
    }
    public void minDatePeriodo2F()
    {
        if (selected.getPeriodo2I() != null || periodo2IR != selected.getPeriodo2I()) {
           // fechaMinima = selected.getPeriodo2I();
            obtenerFecha(selected.getPeriodo2I());
        } 
           periodo2IR = selected.getPeriodo2I();
    }
    public void minDatePeriodo3F()
    {
        if (selected.getPeriodo3I() != null || periodo3IR != selected.getPeriodo3I()) {
            //fechaMinima = selected.getPeriodo3I();
             obtenerFecha(selected.getPeriodo3I());
        } 
             periodo3IR = selected.getPeriodo3I();
    }
    //metodo para obtener la fecha minima de los periodos 
    public void obtenerFecha(Date fecha)
     {
         int mes = 0;
         int dia = 0;
         int years = 0;
         String fecha1;
         SimpleDateFormat fd;
         fd = new SimpleDateFormat("MM/dd/yyyy");
         if (fecha != null) {
             fecha1 = fd.format(fecha);
             StringTokenizer st = new StringTokenizer(fecha1, "/");
             mes = Integer.parseInt(st.nextToken());
             dia = Integer.parseInt(st.nextToken());
             years = Integer.parseInt(st.nextToken());
             //sumamos el mes 
             if (dia > 10) {
                 if (mes < 12) {
                     mes = mes + 1;
                 } else {
                     mes = 1;
                     years = years + 1;
                 }
                 dia = dia - 10;
             } else  {
                 dia=28;
             }
             //restamos el dia, si esta es mayor a 3
         }
         fechaMinima = String.valueOf(mes) + "/" + String.valueOf(dia) + "/" + String.valueOf(years);
 
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
     //Obtiene la fecha de la zona GMT, para obtner la fecha actual.
        Date timeLocal= new Date();
        DateFormat converTime= new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        converTime.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        selected = new SolicitudesInternet();
        selected.setSolicitud(converTime.format(timeLocal));
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
            items=itemsRespaldo;
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
                    if (persistAction==PersistAction.CREATE) {
                        getFacade().create(selected);
                    }else
                    {
                    selectGestores=null;
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
        return contadorPeriodo;
    }
    public void cancelarEdit()
    {
        selected = selectedRespaldo;
        items=null;
        items=itemsRespaldo;
        selectedRespaldo=null;
        
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
