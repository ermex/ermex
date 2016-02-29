/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.clases;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ermex
 */
public class generarHeaderPDF extends  PdfPageEventHelper {
    private Image imagen, imagenfondo, imgtable ;
    //Paragraph contenidoencabezado= new Paragraph();
    PdfTemplate template;
    PdfPTable encabezado = new PdfPTable(1);
    
    PdfPTable tablaImagenFondo = new PdfPTable(2);
    public generarHeaderPDF() throws IOException, BadElementException
    {
         //creamos instancias de los objetos PDFcell
        PdfPCell contenidoCellHead= new PdfPCell();
        PdfPCell espacios= new PdfPCell();
        Paragraph contendo = new Paragraph();

        
        //Declaramos variables 
        String empresa="Servicio de Información Agroalimentaria y Pesquera Dirección de Soluciones Geoespaciales";
        String leyenda="\"2015, Año del Generalísimo José María Morelos y Pavón\"";
        String fecha="México, D.F.;";

        
        //Declaracion de fuentes que se ocupan en el pie y encabezado de pagina 
         Font head = new Font();
         Font head1 = new Font();
         Font head2 = new Font();

         //se asignan valores a cada font 
         head.setSize(12);
         head.setColor(Color.LIGHT_GRAY);
         head.setStyle(Font.BOLD);
         
         head1.setSize(9);
         head1.setColor(Color.LIGHT_GRAY);
         head1.setStyle(Font.BOLD);
         head1.setStyle(Font.ITALIC);
       
         
         head2.setSize(12);
         head2.setColor(Color.LIGHT_GRAY);
         
         //propieades de las celdas de la tabal enzabezado 
         contenidoCellHead.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
         contenidoCellHead.setBorder(Rectangle.NO_BORDER);
         
         
         espacios.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
         espacios.setBorder(Rectangle.NO_BORDER);
         espacios.setPhrase(new Phrase(" "));

        try {
            
            
             encabezado.setTotalWidth(360f);                            
            imagen=Image.getInstance("C:\\Documents and Settings\\ermex\\My Documents\\ProgramasNotas\\documentosPrueba\\img\\logoSiap.png");
            imagenfondo=Image.getInstance("C:\\Documents and Settings\\ermex\\My Documents\\ProgramasNotas\\documentosPrueba\\img\\imgfondo.png");
            imagen.scaleAbsolute(150f, 80f);
            imagen.scalePercent(40, 40);
            
//            contenidoCellHead.setPhrase(new Paragraph(empresa, head));
//            encabezado.addCell(contenidoCellHead); 
//            encabezado.addCell(espacios);
//            contenidoCellHead.setPhrase(new Paragraph(leyenda, head1));
//            encabezado.addCell(contenidoCellHead);
//             encabezado.addCell(espacios);
//            contenidoCellHead.setPhrase(new Paragraph(fecha, head2));
//            encabezado.addCell(contenidoCellHead);
            
            
            espacios.addElement(imagenfondo);
            imagenfondo.setAbsolutePosition(100f, 150f);


        } catch (BadElementException ex) {
           // Logger.getLogger(MembreteHeaderiText.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("hola"+ex);
            System.out.println("Error al crear headerPDF" + ex.getMessage());
        }   
    }  
//   
    @Override
      public void onStartPage(PdfWriter writer, Document document) {

        try{            
           encabezado.writeSelectedRows(0, -1, 200f, 750f, writer.getDirectContent());
            encabezado.setWidthPercentage(100);
            document.add(imagen);
            document.add(imagenfondo);
           
          // document.add(new Paragraph(" "));
         }catch(Exception doc)
         {
             System.out.println(doc.getMessage());
         }        
     }

//   public void onEndPage(PdfWriter writer, Document document) {
//         String direcccionFoot="Av. Benjamín Franklin 146, Escandón, Miguel Hidalgo, México, D.F., C.P. 11800";
//         String contactoFoot="t. +52 (55) 3871.8500, www.siap.gob.mx";        
//         Font  foot= new Font();
//         foot.setSize(7);
//         foot.setColor(BaseColor.LIGHT_GRAY);
//         foot.setStyle(Element.ALIGN_CENTER);
//         ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Paragraph(String.format("Página  %01d ",writer.getPageNumber()),foot), 300, 80, 0);
//         ColumnText.showTextAligned(writer.getDirectContent(), 1, new Phrase(direcccionFoot,foot), 300,70,0);
//         ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(contactoFoot, foot), 300,60,0);    
//    }
//   
}
