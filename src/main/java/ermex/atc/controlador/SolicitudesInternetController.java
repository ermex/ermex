package ermex.atc.controlador;

import ermex.atc.entidad.SolicitudesInternet;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.Gestores;
import ermex.atc.sesion.SolicitudesInternetFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
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
import org.primefaces.model.DualListModel;

@Named("solicitudesInternetController")
@SessionScoped
public  class SolicitudesInternetController implements Serializable {

    @EJB
    private ermex.atc.sesion.SolicitudesInternetFacade ejbFacade;
    private List<SolicitudesInternet> items = null;
    private List<SolicitudesInternet> itemsRespaldo = null;
    private SolicitudesInternet selected;
    private CatalogoimagenesController controlerC;
    
    //Variables creadas por el programador 
    
    private Gestores selectGestores;
    private Object selctObject;
    private String  noPeriodo1;
    private String noPeriodo2;
    private String noPeriodo3;
    private String fechaMinima;
    private int radioS;
    private SolicitudesInternet selectedRespaldo;
    private Date periodo1IR;
    private Date periodo2IR;
    private Date periodo3IR;    
    private DualListModel<Catalogoimagenes> tipos;  
    private HashMap<String, String> temas=null;
    private HashMap<String, String> nubosidad=null;
    private String nuevoT="";
    
     
    public SolicitudesInternetController() {
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase();
        if (temas==null) {
        temas= new HashMap<>();
        temas.put("AGRICULTURA","AGRICULTURA");
        temas.put("RECURSOS NATURALES","RECURSOS NATURALES");
        temas.put("ANÁLISIS METEOROLÓGICO","ANÁLISIS METEOROLÓGICO");
        temas.put("DESASTRES NATURALES","DESASTRES NATURALES");
        temas.put("USO DE SUELO","USO DE SUELO");
        temas.put("SEGURIDAD NACIONAL","SEGURIDAD NACIONAL");
        temas.put("INUNDACIÓN","INUNDACIÓN");
        temas.put("GANADERÍA","GANADERÍA");
        temas.put("CAPACITACIÓN","CAPACITACIÓN");
        temas.put("CARTOGRAFÍA","CARTOGRAFÍA");
        }
        if (nubosidad==null) {
            nubosidad= new LinkedHashMap<>();
          nubosidad.put("SIN-NUBES","A");
          nubosidad.put("MEMOR-10%","B");
          nubosidad.put("MEMOR-20%","C");
          nubosidad.put("MEMOR-75%","D");
          nubosidad.put("MAYOR-76%","E");

        }
    }
    public SolicitudesInternetFacade getEjbFacade() {
        return ejbFacade;
    }

    public HashMap<String, String> getTemas() {
        return temas;
    }

    public void setEjbFacade(SolicitudesInternetFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    public SolicitudesInternet getSelectedRespaldo() {
        return selectedRespaldo;
    }

    public DualListModel<Catalogoimagenes> getTipos() {
        return tipos;
    }

    public HashMap<String, String> getNubosidad() {
        return nubosidad;
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
    //metodo para iniciar valores de los temas

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

    public String getNuevoT() {
        return nuevoT;
    }

    public void setNuevoT(String nuevoT) {
        this.nuevoT = nuevoT;
    }

public void validarPeriodo()
{
    switch(noPeriodo1)
    {
        case "uno":
            System.out.println("Estamos en el caso uno");
                resetPeriodos(1);
                noPeriodo2=null;
                noPeriodo3=null;
            break;
        case "dos":
            System.out.println("Estamos en el caso de dos ");
                noPeriodo2="dos";
                noPeriodo3=null;
                resetPeriodos(2);
            break;
        case "tres":
            System.out.println("Esamos en el caso 3");
            noPeriodo3="tres";
            resetPeriodos(3);
            break;
    }
}
    
    //metodo creado por el procramador para limbiar los periodos al crear una nueva solicitud
 public void setNoPeriodo1(String periodoS) {
     this.noPeriodo1=periodoS;
//        int radioSelec=0;
//        
//        if (periodoS!=null) {
//               if (periodoS.compareTo("dos")==0)
//               {
//                   noPeriodo2="dos";
//                   noPeriodo1="uno";
//                   noPeriodo3=null;
//                   radioSelec=2;
//               }else
//               {
//                   if (periodoS.compareTo("tres")==0) {
//                       noPeriodo3="tres";
//                       noPeriodo1="uno";
//                       noPeriodo2="dos";
//                       radioSelec=3;
//                   }else
//                   {
//                      this.noPeriodo1 = periodoS; 
//                       noPeriodo2=null;
//                       noPeriodo3=null;
//                       radioSelec=1;
//                   }
//               }
//        }
//      
//        resetPeriodos(radioSelec);
//        System.out.println("Valore de radio select " +  radioSelec);
    }
    //metodo generado por el programador para obtener informacion del gestor.
public void nombreOrganismo()
{
    String nombre;
    String depenecia;
    String organismo;
    nombre =selectGestores.getIdpersona().getNombre()+ " " +selectGestores.getIdpersona().getApellidop() + " "+selectGestores.getIdpersona().getApellidom();
    organismo= selectGestores.getIdpersona().getIdinstitucion().getIdorganismo().getNombre();
    depenecia=selectGestores.getIdpersona().getIdinstitucion().getIdorganismo().getIddependencia().getNombre();
    selected.setNombre(nombre);
    selected.setOrganismo(organismo);
    selected.setDependencia(depenecia);
    
}
//metodo para generar el modo y nivel de las imagens solicitadas
public void ModoNivel(List<Catalogoimagenes> tipoM)
{
    FacesMessage mensaje=null;
    List<Catalogoimagenes>temp=tipoM;
    String tipo="";
    String nivel="";
    String resolucion="";
    selected.setModo(null);
    selected.setNivel(null);
    selected.setResolucion(null);
    boolean b=false;
    if (tipoM!=null && tipoM.size()>0) {
        System.out.println(tipoM.size());
        for (int i = 0; i < tipoM.size(); i++) {
            tipo=tipo+tipoM.get(i).getTipo() + " ";
            nivel=nivel+tipoM.get(i).getNivel() + " "; 
            resolucion=resolucion+tipoM.get(i).getResolucion().toString();          
        }
        selected.setModo(tipo);
        selected.setNivel(nivel);
        selected.setResolucion(resolucion);
        
    }else
    {
         mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecciones el tipo de imagen"," ");
    }    
}    
    public Gestores getSelectGestores() {
        return selectGestores;
    }
    //los periodos se pasan a nulos 
    private void  resetPeriodos(int datos)
    {
        System.out.println("Valor de radioS " + radioS);
        System.out.println("Valor de datos "+ datos);
        switch(datos)
        {
            case 1:
                if (radioS==2) {
                    selected.setPeriodo2I(null);
                    selected.setPeriodo2F(null);
                    System.out.println("reset valores en 1-2");
                }else
                {
                    if (radioS==3) {
                        System.out.println("reset valores en 1-3");
                    selected.setPeriodo2I(null);
                    selected.setPeriodo2F(null);
                    selected.setPeriodo3I(null);
                    selected.setPeriodo3F(null);                        
                    }else{
                        if (radioS==1) {
                            
                        }
                    }
                       
                }
                break;
            case 2:
                if (radioS==3) {
                    System.out.println("reset valores en 2-3");
                    selected.setPeriodo3I(null);
                    selected.setPeriodo3F(null);
                } 
                else
                {
                    if (radioS==2) {
                        
                    }
                }
                break;
            default:                
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
            if (radioS!=1) {
                obtenerFecha(selected.getPeriodo1I());    
            }           
        }        
            periodo1IR = selected.getPeriodo1I();
    }
    public void minDatePeriodo2F()
    {
        if (selected.getPeriodo2I() != null || periodo2IR != selected.getPeriodo2I()) {
           // fechaMinima = selected.getPeriodo2I();
             if (radioS!=2) {
                obtenerFecha(selected.getPeriodo2I());    
            }
        } 
           periodo2IR = selected.getPeriodo2I();
    }
    public void minDatePeriodo3F()
    {
        if (selected.getPeriodo3I() != null || periodo3IR != selected.getPeriodo3I()) {
            //fechaMinima = selected.getPeriodo3I();
           if (radioS!=3) {
                obtenerFecha(selected.getPeriodo3I());    
            }
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

    public void agregarTema()
    {
        if (!"".equals(nuevoT)) {
            temas.put(nuevoT, nuevoT);
            nuevoT="";
        }
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
            System.out.println("Se  agrego correctamente");
            
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
