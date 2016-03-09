package ermex.atc.controlador;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import ermex.atc.entidad.Notas;
import ermex.atc.controlador.util.JsfUtil;
import ermex.atc.controlador.util.JsfUtil.PersistAction;
import ermex.atc.entidad.Controlsolicitudes;
import ermex.atc.entidad.Documentosnotas;
import ermex.atc.entidad.Instituciones;
import ermex.atc.entidad.Organismos;
import ermex.atc.entidad.Personas;
import ermex.atc.sesion.NotasFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.core.context.SecurityContextHolder;

@Named("notasController")
@SessionScoped
public class NotasController implements Serializable {

    @EJB
    private ermex.atc.sesion.NotasFacade ejbFacade;
    private List<Notas> items = null;
    private List<Notas> itemsRespaldo = null;
    //Declaracion de variables para llenar la tabla de imagenes entregradas en paginaNota.xhtml
    private List<Object> itemsObject = null;
    private Notas selected;
    private Personas designador;
    private Personas gestor;
    private Instituciones institucion;
    private Organismos organismo;
    private String solicitud;
    private String usuarioGestor;
    private StreamedContent download;
    private String tituloTabla = "Notas iniciadas";
    private boolean descargar = true;
    private int tipoConsulta = 1;
    private UploadedFile file;
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

    public StreamedContent getDownload() {
                return download;
    }

    public void setDownload(StreamedContent download) {
        this.download = download;
    }

    public String getTituloTabla() {
        return tituloTabla;
    }

    public void setTituloTabla(String tituloTabla) {
        this.tituloTabla = tituloTabla;
    }

    public boolean isDescargar() {
        return descargar;
    }

    public void setDescargar(boolean descargar) {
        this.descargar = descargar;
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    //metodo para obtener las imagenes entregadas a la nota
    public List<Object> getItemsObject() {
        if (selected != null) {
            itemsObject = getFacade().consultarImagen(selected.getIdnota());

        }

        return itemsObject;
    }

    //metodo para actualizar la nota dependiendo la accion
    public void acuseAndOficio(int evento) {
        Date date = new Date();
        DateFormat converTime2 = new SimpleDateFormat("MM/dd/yyyy");
        converTime2.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        if (selected != null) {
            try {
                Date fecha = converTime2.parse(converTime2.format(date));
                //acualiza la nota si ya se tiene el nuemor de oficio
                if (evento == 1) {
                    selected.setFechaoficio(fecha);
                    update();
                } else //actualiza la nota si el gestor ya hizo un acuse de resivido y la nota se da  por terminado
                if (evento == 2) {
                    selected.setFechaacuse(fecha);
                    selected.setStatus(3);
                    update();
                } else //actulazo la nota a candelado 
                if (evento == 3) {
                    selected.setStatus(4);
                    update();
                }
            } catch (ParseException ex) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException, InvalidFormatException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String urlImgLogo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "logoSiap.PNG";
        String urlImgFondo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "imgfondo.png";

        Image imagenfondo = Image.getInstance(urlImgFondo);
        Image imagenfondoLogo = Image.getInstance(urlImgLogo);
        imagenfondoLogo.scaleAbsolute(150f, 80f);
        imagenfondoLogo.scalePercent(40, 40);
        imagenfondo.setAbsolutePosition(100f, 150f);
        pdf.add(imagenfondoLogo);
        pdf.add(imagenfondo);
        //crearNota();
    }
//metodo para actualizar la nota iniciadad

    public void createNota() {
        if (selected != null) {
            //iniciamos itemsObject, el cual contien las imagenes entregadas a la nota
            getItemsObject();
            //iniciamos las valores de la nota
            informacionNota();
            try {
                //generamos la nota
                generarNotaWord();
                //actualizamos la nota en la base de datos 
                update();
            } catch (IOException | InvalidFormatException ex) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    //agrega los nuevos valores a las variables de la nota y estas puedan  ser vistos por el usuario 
    public void prepararCrearNota() throws ParseException {
        Date date = new Date();
        Object obj;
        DateFormat converTime = new SimpleDateFormat("yyyyMMdd");
        DateFormat converTime2 = new SimpleDateFormat("MM/dd/yyyy");
        converTime.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        converTime2.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        String fecha = converTime.format(date);
        Date fechacreacion = converTime2.parse(converTime2.format(date));
        if (selected != null) {
            obj = ejbFacade.obtenerNoImagen(selected.getIdnota());
            if (obj != null) {
                String valorObj = "" + obj;
                int noimg = Integer.parseInt(valorObj);
                if (noimg > 0) {
                    String nombreNota = fecha + " nota " + selected.getNonota() + " ("
                            + selected.getIdcontrolsolicitud().getGestor().getGestor() + "_"
                            + selected.getIdcontrolsolicitud().getGestor().getIdpersona().getIdinstitucion().getNombre()
                            + ")";
                    selected.setNombre(nombreNota);
                    selected.setFechacreacion(fechacreacion);
                    selected.setStatus(2);
                    selected.setNoimagen(noimg);

                    JsfUtil.addSuccessMessage("Total de imagenes encontradas " + noimg + " para la nota " + selected.getIdnota());
                } else {
                    JsfUtil.addErrorMessage("No se encontrarin imagenes para la nota" + selected.getIdnota());
                }
            }
        }
    }

    //recuperamos la informacion de la nota si esta no es crado
    public void cancelarCreate() {
        System.out.println("Estamos en cancelar");
        if (selected != null) {
            selected = ejbFacade.find(selected.getIdnota());
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).equals(selected)) {
                    items.set(i, selected);
                    i = items.size();
                }
            }
        }
    }

    //obtenmos la ruta del archivo 
    public String pathFile() {
         String path="C:/Documents and Settings/ermex/My Documents/notas/";
       String user=SecurityContextHolder.getContext().getAuthentication().getName();
      //  String path = "/home/apermex/" + user + File.separator + obtenerFecha()+ File.separator + selected.getIdnota();
        //Se crea un archivo para validar que exista el directorio donde se va guardar el archivo
        File directorio = new File(path);
        if (!directorio.exists()) {
            //si no existe se crean los directorios necesarios
            directorio.mkdirs();
        }
        return directorio.getAbsolutePath();
    }

    public void validarDescarga() {
        if (selected != null) {
            if (selected.getStatus() == 2 || selected.getStatus() == 3) {
                descargar = false;
            }
        }
    }
    public void upload(FileUploadEvent event) {
       
            file=event.getFile();
    }
    public void subirArchivo(int op, String ruta) throws IOException
    {
        DocumentosnotasController crear = new DocumentosnotasController();
        Documentosnotas docuemntosNotas = new Documentosnotas();
        if (op == 1) {
          //crear.prepareCreate();
          //crear.getSelected().setIdnota(selected);
          //crear.getSelected().setNotaword(ruta);
          //crear.createDocuento(selected, ruta);
        } else if (op == 2) {

        } else if (op == 3) {
            String path = pathFile() + file.getFileName();
            File copia = new File(path);
            if (!copia.exists()) {
                if (copia.createNewFile()) {
                    OutputStream out = new FileOutputStream(copia);
                    out.write(file.getContents());
                    out.flush();
                    out.close();

                }
            }
        }

    }
    public void generarNotaWord() throws IOException, InvalidFormatException {
        //String ruta="C:\\Documents and Settings\\ermex\\My Documents\\ProgramasNotas\\documentosPrueba\\plantillanota.docx";        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String ruta = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "plantilla" + File.separator + "plantillaN.docx";
        InputStream fs = new FileInputStream(ruta);
        XWPFDocument doc = new XWPFDocument(fs);
        boolean bandera = false;
        Date date = new Date();
        DateFormat converTime = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        converTime.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                if (r != null) {
                    String text = r.getText(0);
                    if (text != null) {
                        if (text.contains("$fecha")) {
                            text = text.replace("$fecha", converTime.format(date));
                            r.setText(text, 0);
                        }
                        if (text.contains("designado")) {
                            text = text.replace("designado", designador.getGrado() + " " + designador.getNombre() + " "
                                    + designador.getApellidop() + " " + designador.getApellidom());
                            r.setText(text, 0);
                        }
                        if (text.contains("$cargo")) {
                            text = text.replace("$cargo", designador.getCargo());
                            r.setText(text, 0);
                        }

                        if (text.contains("$solicitud")) {
                            text = text.replace("$solicitud", solicitud);
                            r.setText(text, 0);
                        }
                        if (text.contains("$gestor")) {
                            text = text.replace("$gestor", gestor.getGrado() + " " + gestor.getNombre() + " " + gestor.getApellidop()
                                    + " " + gestor.getApellidom());
                            r.setText(text, 0);
                        }
                        if (text.contains("$cuenta")) {
                            text = text.replace("$cuenta", selected.getIdcontrolsolicitud().getGestor().getGestor());
                            r.setText(text, 0);
                        }
                        if (text.contains("$empresa")) {
                            text = text.replace("$empresa", institucion.getNombre());
                            r.setText(text, 0);
                        }
                        if (text.contains("$numero")) {
                            text = text.replace("$numero", String.valueOf(selected.getNoimagen()));
                            r.setText(text, 0);
                        }
                        if (text.contains("$tema")) {
                            text = text.replace("$tema", selected.getIdcontrolsolicitud().getSolicitud().getTema());
                            r.setText(text, 0);
                        }
                        List<Object> imagnes = new ArrayList<>();
                        if (itemsObject != null) {
                            imagnes = itemsObject;

                            //XWPFTable table = doc.createTable(itemsObject.size()+1, 5);
                            XWPFTable table = new XWPFTable(doc.getTables().get(0).getCTTbl(), doc.getTables().get(0).getBody());
                            XWPFTableRow row;
                            for (int i = 0; i < imagnes.size(); i++) {
                                Object[] variables = (Object[]) imagnes.get(i);
                                row = table.createRow();
                                // nota.add(new imgEntreNo((int)variables[0], (String)variables[1],(String)variables[1],(String)variables[3], (String)variables[4]));
                                //img=(imgEntreNo) imagnes.get(i);

                                row.getCell(0).setText(variables[0].toString());
                                row.getCell(1).setText(variables[1].toString());
                                row.getCell(2).setText(variables[2].toString());
                                row.getCell(3).setText(variables[3].toString());
                                row.getCell(4).setText(variables[4].toString());
                                //table.addRow(row);
                                System.out.println("Numero de filas " + i);
                            }

                            doc.setTable(0, table);
                            itemsObject = null;
                        }
                    }
                }
            }
        }
        //doc.write(new FileOutputStream("C:/Documents and Settings/ermex/My Documents/ProgramasNotas/documentosPrueba/" + solicitud + ".docx"));
        doc.write(new FileOutputStream(pathFile()+File.separator + selected.getNombre() + ".docx"));
        
        subirArchivo(1,pathFile()+File.separator + selected.getNombre() + ".docx");
        System.out.println("Nota creada");
        System.out.println(pathFile()+File.separator + selected.getNombre() + ".docx");
        //descargar();
    }

    public void descargarDoc() {
        //download = new DefaultStreamedContent(new FileInputStream(
        //				new File(pathFile()+selected.getNombre()+".docx")),"application/docx",selected.getNombre()+".docx");

       

        try {
             String archivo = pathFile() + File.separator + selected.getNombre() + ".docx";
             System.out.println(archivo);
            File path = new File(archivo);
            download = new DefaultStreamedContent(new FileInputStream(path), "application/docx", selected.getNombre() + selected.getReposiciones() + ".docx");
            selected.setReposiciones(selected.getReposiciones() + 1);
            update();
            System.out.println("archivo descargado");
        } catch (FileNotFoundException ex) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String ruta = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "word-icon.png";
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al descargar", " ");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            try {
                download = new DefaultStreamedContent(new FileInputStream(ruta), "application/png", "Error_Descarga.png");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    //metodo para obtener informacion de la nota.
    public void informacionNota() {
        try {
            if (selected != null) {
                solicitud = selected.getIdcontrolsolicitud().getSolicitud().toString();
                designador = selected.getIdcontrolsolicitud().getGestor().getDesignador();
                gestor = selected.getIdcontrolsolicitud().getGestor().getIdpersona();
                institucion = selected.getIdcontrolsolicitud().getGestor().getIdpersona().getIdinstitucion();
                organismo = selected.getIdcontrolsolicitud().getGestor().getIdpersona().getIdinstitucion().getIdorganismo();
                usuarioGestor = selected.getIdcontrolsolicitud().getGestor().toString();
            }

        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "No se ecnontro informacion en la base de datos");
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
//metodo para iniciar la nota desde controlsolicitudes

    public Notas crearNotaCS(Controlsolicitudes idCs) {
        selected = prepareCreate();
        selected.setIdcontrolsolicitud(idCs);
        generarIdNota();
        initializeEmbeddableKey();
        return selected;

    }

    public String obtenerFecha() {
        Date date = new Date();
        DateFormat converTime = new SimpleDateFormat("yyyy");
        converTime.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        return converTime.format(date);
    }

    //generamos el idnota
    public void generarIdNota() {
        //obtenemos el valor de la secuencia
        String idnota;
        Calendar date = Calendar.getInstance();
        //obtienen el siguiente valor de secuencia
        Object obejto = ejbFacade.obtenerNonuto();
        String fecha = String.valueOf(date.get(Calendar.YEAR));
        //genera el id de nota
        int numeronota = Integer.parseInt("" + obejto);
        numeronota = numeronota + 10000;
        String idn = String.valueOf(numeronota);
        idnota = fecha + idn.substring(1, 5);
        selected.setIdnota(idnota);
        selected.setNonota(obejto.hashCode());
    }

    //metodo para obtener las notas deacuerdo al usuario y estado
    public void filtro(int tipo) {
        if (tipo != 0) {
            String user=SecurityContextHolder.getContext().getAuthentication().getName();
            items = ejbFacade.notasBayResponsableAndStatus(user, tipo);
        }
        descargar = true;
        if (tipo==1) {
            tituloTabla = "Notas iniciadas";
        } else if (tipo == 2) {
            tituloTabla = "Notas Creadas";
        } else if (tipo == 3) {
            tituloTabla = "Notas terminadas";

        } else {
            tituloTabla = "Notas canceladas";
        }

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
            String user=SecurityContextHolder.getContext().getAuthentication().getName();
            if (user!=null) {
                 items = getFacade().notasBayResponsableAndStatus(user, 1);
                descargar = true;
                tipoConsulta = 1;
                //itemsRespaldo=getFacade().notasBayResponsableAndStatus(sessionBean.getUserName(),1);
                tituloTabla = "Notas iniciadas";
                
            }          
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.CREATE) {
                        getFacade().create(selected);
                    } else {
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
