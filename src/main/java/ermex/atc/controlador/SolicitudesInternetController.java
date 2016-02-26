package ermex.atc.controlador;

import ermex.atc.entidad.SolicitudesInternet;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Catalogoimagenes;
import ermex.atc.entidad.Gestores;
import ermex.atc.entidad.Imagnesolicitudes;
import ermex.atc.sesion.ImagnesSolicitudFacade;
import ermex.atc.sesion.SolicitudesInternetFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private List<Imagnesolicitudes> imgSolitud;
    @EJB
    private ImagnesSolicitudFacade imgSoliFacde;
    private List<Catalogoimagenes> itemsCimg;
    private Catalogoimagenes selectCa;
    private Imagnesolicitudes selectImgSol;
    private final List<Imagnesolicitudes> deleteImgSol= new ArrayList<>();
    private String tipo="";
    private String nivel="";
    private String resolucion="";
    private String updateAceptar="Acpectar";
    private int tiposConsulta=1;
    private String tituloTabla="Lista de Solicitudes Pendientes";
     
    public SolicitudesInternetController() {
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        radioS=periodosBase();
        tiposConsulta=1;
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
          nubosidad.put("SIN NUBES","SIN NUBES");
          nubosidad.put("MEMOR A 10%","MEMOR A 10%");
          nubosidad.put("MEMOR A 20%","MEMOR A 20%");
          nubosidad.put("MEMOR A 75%","MEMOR A 75%");
          nubosidad.put("MAYOR A 76%","MAYOR A 76%");

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

    public Catalogoimagenes getSelectCa() {
        return selectCa;
    }

    public void setSelectCa(Catalogoimagenes selectCa) {
        this.selectCa = selectCa;
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

    public ImagnesSolicitudFacade getImgSoliFacde() {
        return imgSoliFacde;
    }

    public void setImgSoliFacde(ImagnesSolicitudFacade imgSoliFacde) {
        this.imgSoliFacde = imgSoliFacde;
    }

    public List<Imagnesolicitudes> getImgSolitud() {
        return imgSolitud;
    }

    public Imagnesolicitudes getSelectImgSol() {
        return selectImgSol;
    }

    public void setSelectImgSol(Imagnesolicitudes selectImgSol) {
        this.selectImgSol = selectImgSol;
    }

    public String getUpdateAceptar() {
        return updateAceptar;
    }
    
    
   //Metodo para actualizar la lista de las imagenes solicitadas anteriormente
    public void modiImgSolicitud()
    {
        int id= selectImgSol.getIdmagenesolicitud();
        if (imgSolitud.size()> 0) {
          for (int i = 0; i < imgSolitud.size(); i++) {
              if (imgSolitud.get(i).getIdmagenesolicitud()==id) {
                  deleteImgSol.add(imgSolitud.get(i));
                  imgSolitud.remove(i);
                  i=imgSolitud.size();
              }
            }
          variables();
        }
        if (imgSolitud.size()< 1 && imgSolitud.size()< 1) {
            updateAceptar=null;
        }
    }
    public void itemsCancelados()
    {
        items=ejbFacade.findByCancelados();
    }
    public void itemsTerminados()
    {
        items=ejbFacade.findByTerminados();
    }
//metodo para obtener los valores de las variables modo, tipo, resolucion
    public void variables()
    {
        tipo=" ";
        nivel=" ";
        resolucion=" ";
        selected.setModo(null);
        selected.setNivel(null);
        selected.setResolucion(null);
        if (imgSolitud!=null) {    
            if (imgSolitud.size() > 0 ) {
                for (int i = 0; i < imgSolitud.size(); i++) {
                 nivel=nivel + imgSolitud.get(i).getIdentificador().getNivel()+ " ";
                 tipo=tipo+ imgSolitud.get(i).getIdentificador().getTipo()+" ";
                 resolucion=resolucion+imgSolitud.get(i).getIdentificador().getResolucion()+" ";
            }   
            }            
        }
        if (itemsCimg!=null) {
            if (itemsCimg.size() > 0) {
                for (int i = 0; i < itemsCimg.size(); i++) {
                 nivel=nivel + itemsCimg.get(i).getNivel()+ " ";
                 tipo=tipo+ itemsCimg.get(i).getTipo()+" ";
                 resolucion=resolucion+itemsCimg.get(i).getResolucion()+" ";
            }                 
            }
        }
        selected.setModo(tipo);
        selected.setNivel(nivel);
        selected.setResolucion(resolucion);
    }
    //metodo para eliminar de las lista las nuevas imagenes seleccionadas
    public void deleRowtList()
    {
         if (itemsCimg.size()> 0 && selectCa.getIdentificador()!=null) {
             String id=selectCa.getIdentificador();
          for (int i = 0; i < itemsCimg.size(); i++) {
              if (itemsCimg.get(i).getIdentificador().compareTo(id)==0){
                  itemsCimg.remove(i);
                  i=itemsCimg.size();
              }
            }
          variables();
        }
         //la variable updateAceptar nos permite validar que se eliga por lo menos una imagen a la solicitud, cuando se edta
         if (itemsCimg.size() < 1 && imgSolitud.size()< 1) {
            updateAceptar=null;
        }
    }
   //metodo creado por el programador para iniciar los valores de las variables
    public  void iniciarValores()
    {
        SolicitudesInternet solici= new SolicitudesInternet();
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        itemsCimg=null;
        
        tipo="";
        resolucion="";
        nivel="";
        updateAceptar="aceptar";
        if (selected!=null) {
            imgSolitud=getEjbFacade().updateRegistro(selected.getSolicitud());
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

    public int getTiposConsulta() {
        return tiposConsulta;
    }

    public void setTiposConsulta(int tiposConsulta) {
        this.tiposConsulta = tiposConsulta;
    }

    public String getTituloTabla() {
        return tituloTabla;
    }
    

public void validarPeriodo()
{
    switch(noPeriodo1)
    {
        case "uno":
                resetPeriodos(1);
                noPeriodo2=null;
                noPeriodo3=null;
            break;
        case "dos":
                noPeriodo2="dos";
                noPeriodo3=null;
                resetPeriodos(2);
            break;
        case "tres":
            noPeriodo3="tres";
            noPeriodo2="dos";
            resetPeriodos(3);
            break;
    }
}
    
    //metodo creado por el procramador para limbiar los periodos al crear una nueva solicitud
 public void setNoPeriodo1(String periodoS) {
     this.noPeriodo1=periodoS;
 }
    //metodo generado por el programador para obtener informacion del gestor.
public void nombreOrganismo()
{
    String nombre=" ";
    String depenecia="Sin dependencia";
    String organismo=" Sin organismo";
    try {
         nombre =selectGestores.getIdpersona().getNombre()+ " " +selectGestores.getIdpersona().getApellidop() + " "+selectGestores.getIdpersona().getApellidom();
         organismo= selectGestores.getIdpersona().getIdinstitucion().getIdorganismo().getNombre();
         depenecia=selectGestores.getIdpersona().getIdinstitucion().getIdorganismo().getIddependencia().getNombre();   
    } catch (Exception e) {
    }
    selected.setNombre(nombre);
    selected.setOrganismo(organismo);
    selected.setDependencia(depenecia);
    
}
//metodo para generar el modo y nivel de las imagens solicitadas
public void ModoNivel(List<Catalogoimagenes> tipoM)
{
    tipo="";
    nivel="";
    resolucion="";
    FacesMessage mensaje=null;
    selected.setModo(null);
    selected.setNivel(null);
    selected.setResolucion(null);
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
        itemsCimg=tipoM;
        variables(); 
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
        switch(datos)
        {
            case 1:
                if (radioS==2) {
                    selected.setPeriodo2I(null);
                    selected.setPeriodo2F(null);
                }else
                {
                    if (radioS==3) {
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

    public List<Catalogoimagenes> getItemsCimg() {
        return itemsCimg;
    }

    public void setItemsCimg(List<Catalogoimagenes> itemsCimg) {
        this.itemsCimg = itemsCimg;
    }

    public String getMinPeriodo() {
        return fechaMinima;
    }
    //los siguientes tres metodos es para validar los periodos 

    public void minDatePerio1F() throws ParseException {
        if (selected.getPeriodo1I() != null || periodo1IR != selected.getPeriodo1I()) {
          //  fechaMinima = selected.getPeriodo1I();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                //obtenerFecha(selected.getPeriodo1I());
                selected.setPeriodo1F(obtenerFecha(selected.getPeriodo1I()));
        }        
            periodo1IR = selected.getPeriodo1I();
    }
    public void minDatePeriodo2F() throws ParseException
    {
        if (selected.getPeriodo2I() != null || periodo2IR != selected.getPeriodo2I()) {
           // fechaMinima = selected.getPeriodo2I();
                obtenerFecha(selected.getPeriodo2I());
                selected.setPeriodo2F(obtenerFecha(selected.getPeriodo2I()));
        } 
           periodo2IR = selected.getPeriodo2I();
    }
    public void minDatePeriodo3F() throws ParseException
    {
        if (selected.getPeriodo3I() != null || periodo3IR != selected.getPeriodo3I()) {
            //fechaMinima = selected.getPeriodo3I();
                obtenerFecha(selected.getPeriodo3I()); 
                selected.setPeriodo3F(obtenerFecha(selected.getPeriodo3I()));
        } 
             periodo3IR = selected.getPeriodo3I();
    }
    //metodo para obtener la fecha minima de los periodos 
    public Date obtenerFecha(Date fecha) throws ParseException
     {
         int mes = 0;
         int dia = 0;
         int years = 0;
         String fecha1;
         SimpleDateFormat fd;
         Date date = null;
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
        date = fd.parse(fechaMinima);
        return date;
 
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
        selectGestores=null;
        selected.setSolicitud(converTime.format(timeLocal));
        itemsCimg=null;
        imgSolitud=null;
        this.noPeriodo1="uno";
        this.noPeriodo2=null;
        this.noPeriodo3=null;
        
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.            
        }
        if (JsfUtil.isValidationFailed()==false) {
            crearImgSol();
        }
       
    }
    public void crearImgSol() {
        imgSoliFacde = new ImagnesSolicitudFacade();
        selectImgSol = new Imagnesolicitudes();
        if (itemsCimg != null && selected != null) {
            if (itemsCimg.size()>0) {
                for (int i = 0; i < itemsCimg.size(); i++) {
                selectImgSol.setSolicitud(selected);
                selectImgSol.setIdentificador(itemsCimg.get(i));
                ejbFacade.creatImgsoliv(selectImgSol);

            }
            }           
        }
        itemsCimg = null;
    }
    public void eliminarImgSol()
    {
        if (deleteImgSol!=null) {
            imgSoliFacde = new ImagnesSolicitudFacade();
            if (deleteImgSol.size()> 0) {
                for (int i = 0; i <deleteImgSol.size(); i++) {
                    ejbFacade.deletaImgSoli(deleteImgSol.get(i));
                }
            }
            imgSolitud=null;
        }
    }
    public void actualizarItems()
    {
        System.out.println(tiposConsulta);
        if (tiposConsulta==1) {
           items=ejbFacade.findByActivos(1);   
           tituloTabla="Lista de Solicitudes Pendientes";
        }else
        {
            if (tiposConsulta==2) {
                items=ejbFacade.findByActivos(2);   
                tituloTabla="Lista de Solicitudes Terminadas";
            }else
            {
                if (tiposConsulta==3) {
                    items=ejbFacade.findByActivos(3);   
                    tituloTabla="Lista de Solicitudes Canceladas";
                }
                else
                {
                    if (tiposConsulta==4) {
                        items=ejbFacade.findByActivos(4);   
                        tituloTabla="Lista de Solicitudes Asignadas";
                    }
                }
            }
        }
    }
    public void update() {
        FacesMessage mensaje=null;
        if (selected.getStatus()==2) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesInternetUpdated"));
            crearImgSol();
            eliminarImgSol();
            mensaje= new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Actualizada","Se ha modifica la solicitud" + selected.getSolicitud());
        }else
        {
           // items=itemsRespaldo;
             mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solicitud no Actualizada","La solicitud no se puede actualizar" + selected.getSolicitud());
             cancelarEdit();
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
            items = getFacade().findByActivos(1);
        }
        return items;
    }
    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    
                    if (persistAction==PersistAction.CREATE) {
                        selected.setGestor(selectGestores.getGestor());
                        getFacade().create(selected);
                        selectGestores=null;
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
    //resetea los valores como al inicio de editar
    public void cancelarEdit()
    {
        selected = ejbFacade.find(selected.getSolicitud());
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(selected)) {
                items.set(i, selected);
                i=items.size();
            }
        }
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
