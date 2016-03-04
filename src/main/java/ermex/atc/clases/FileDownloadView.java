/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author beto
 */
@Named
public class FileDownloadView {
     
    private final StreamedContent file;
     
    public FileDownloadView() throws FileNotFoundException {        
//        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance() .getExternalContext().getContext()).getResourceAsStream("/home/beto/juan/notas/20130422-170139-148.docx");
//        file = new DefaultStreamedContent(stream, "aplication/docx", "20130422-170139-148.docx");
            file = new DefaultStreamedContent(new FileInputStream(new File("\\home\\beto\\juan\\notas\\20130422-170139-148.docx")));
    }
 
    public StreamedContent getFile() {
        System.out.println(file.getName());
        return file;
    }
}
