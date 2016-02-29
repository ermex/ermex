package ermex.atc.controlador;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ermex.atc.clases.generarHeaderPDF;
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
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import javax.servlet.ServletContext;

@Named("notasController")
@SessionScoped
public class NotasController implements Serializable {

    @EJB
    private ermex.atc.sesion.NotasFacade ejbFacade;
    private List<Notas> items = null;
    //Declaracion de variables para llenar la tabla de imagenes entregradas en paginaNota.xhtml
    private List<Object> itemsObject = null;
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
        if (selected != null) {
            itemsObject = getFacade().consultarImagen(selected.getIdnota());

        }

        return itemsObject;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
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
        
    }
    @SuppressWarnings("empty-statement")
    public void imagenesEntregadasNota() throws IOException
    {
        
        
   List<Object> imagnes=itemsObject;
        int filas = imagnes.size();
        Document document = new Document(PageSize.LETTER, 30, 30, 40, 90);
        
        try{
            
             
           // Se genera un nuevo documento en la ruta especificada dentro de FileOutputStream
            PdfWriter write=PdfWriter.getInstance(document, new FileOutputStream("C:\\Documents and Settings\\ermex\\My Documents\\ProgramasNotas\\documentosPrueba\\relacion_imagens_"+ selected.getNonota()+".pdf"));
            
            generarHeaderPDF header = new generarHeaderPDF();
            write.setPageEvent(header);
            document.open(); 
            document.add(new Paragraph("Relacion de imagens"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("No. Nota: 0599"));
            document.add(new Paragraph("Usuario: usuario ermex0057"));
            document.add(new Paragraph("Solicitud: 20151104-112603-515"));  
              document.add(new Paragraph(" "));
           //Numero de columnas de la tabla 
            PdfPTable table = new PdfPTable(5); 
            //Size de cada una de las columas 
            table.setWidths(new int[]{ 3, 2, 2, 2,10});
            //Size de la tabal en porcentaje 
            table.setWidthPercentage(100); 
            
            //Alineacion de la tabla en el docuemto 
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            //Se crea la variable Fon para el contenido de la tabla, encabezado y contenido 
            Font fuente = new Font();
            Font headtable = new Font();
            //se asignan propiedades a la variable fuente como size de letra, color y familia 
            fuente.setSize(8);
            fuente.setColor(Color.BLACK);
            fuente.setFamily("Arial");
            //se asignan propiedades a la variable headtable como size de letra, color y estilo              
            headtable.setColor(Color.BLACK);
            headtable.setSize(9);
            headtable.setStyle(Font.BOLD);
            //se crea celdas las cuales seran el encabezado de la tabla, con nombre de la tabla y el tipo de fuente 
            PdfPCell satelite = new PdfPCell(new Phrase("Satélite SPOT",headtable));
            //fromato de la celda con alineacion de texto
            satelite.setHorizontalAlignment(1);
            PdfPCell jk = new PdfPCell(new Phrase("K/J",headtable));
            jk.setHorizontalAlignment(1);
            PdfPCell fecha= new PdfPCell(new Phrase("Fecha de Toma",headtable));
            fecha.setHorizontalAlignment(1);
            PdfPCell nivelproce = new PdfPCell(new Phrase("Nivel de Proc.",headtable));
            nivelproce.setHorizontalAlignment(1);
            PdfPCell identificador= new PdfPCell(new Phrase("Referencia",headtable));
            identificador.setHorizontalAlignment(1); 
            //se agregan las celdas a la tabla, los cuales seran el encabezado 
            table.addCell(satelite);
            table.addCell(jk);
            table.addCell(fecha);
            table.addCell(nivelproce);
            table.addCell(identificador);
            System.out.println("Valores de las imagenes");
            imgEntreNo img= new imgEntreNo();
            List<imgEntreNo> nota = new ArrayList<>();
            Iterator<Object> itrTemp = itemsObject.iterator();
            for (int i = 0; i <imagnes.size(); i++) {
                Object[] variables=(Object[]) imagnes.get(i);
               // nota.add(new imgEntreNo((int)variables[0], (String)variables[1],(String)variables[1],(String)variables[3], (String)variables[4]));
                //img=(imgEntreNo) imagnes.get(i);
                satelite.setPhrase(new Phrase(variables[0].toString(), fuente));
                table.addCell(satelite);
                jk.setPhrase(new Phrase(variables[1].toString(), fuente));
                table.addCell(jk);
                fecha.setPhrase( new Phrase(variables[2].toString(), fuente));
                table.addCell(fecha);
                nivelproce.setPhrase(new Phrase(variables[3].toString(), fuente));
                table.addCell(nivelproce);
                identificador.setPhrase(new Phrase(variables[4].toString(), fuente));
                table.addCell(identificador);                
            }
            //se crea la tabla con los datos del arrayList con el size de filas.
            //cada iteracion representa una fil, con 5 columnas como se definio arriba.
//            for (int i = 0; i < filas ; i++) {
//                //asignamos valor a la celda tomando el primer valor del ArrayList, con el el formato de la variable fuente 
//                satelite.setPhrase(new Phrase(imagnes.get(0).toString(), fuente));
//                //agregamos la celda a la tabla.
//                table.addCell(satelite);
//                //se remueve el primer valor del ArrayList
//                imagnes.remove(0);
//                jk.setPhrase(new Phrase(imagnes.get(0).toString(), fuente));
//                table.addCell(jk);
//                imagnes.remove(0);
//                fecha.setPhrase( new Phrase(imagnes.get(0).toString(), fuente));
//                table.addCell(fecha);
//                imagnes.remove(0);
//                
//                nivelproce.setPhrase(new Phrase(imagnes.get(0).toString(), fuente));
//                table.addCell(nivelproce);;
//                imagnes.remove(0);
//                identificador.setPhrase(new Phrase(imagnes.get(0).toString(), fuente));
//                table.addCell(identificador);
//                imagnes.remove(0); 
//                System.out.println("Numero de imagnes"+i);
//            }     
            
            //se agreda la tabla al documento.
            document.add(table);
               //cerramos el codumento 
            document.close();
            
          //  prueba1();
            
        }catch( DocumentException | IOException e)
        {
            System.err.println("Ocurrio un error al crear el archivo");
           // System.exit(-1);
            System.out.println(e.getMessage());
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
                //nombreDesigandor= designador.getCargo()+ " " + designador.getNombre()+ " " +designador.getApellidop()+" " + designador.getApellidom();
                //nombreGestor=gestor.getCargo() + " " + gestor.getNombre() + " " + gestor.getApellidop() + " " + gestor.getApellidom();
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
        Calendar date = Calendar.getInstance();
        Object obejto= ejbFacade.obtenerNonuto();
        String fecha=String.valueOf(date.get(Calendar.YEAR));
        String idnota;        
        idnota=fecha+obejto;
        selected.setIdnota(idnota);
        selected.setNonota(obejto.hashCode());
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
