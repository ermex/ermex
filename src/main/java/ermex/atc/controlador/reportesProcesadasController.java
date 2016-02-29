/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import ermex.atc.sesion.reporteFacade;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.event.ActionEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ermex
 */
@Named(value = "reportesProcesadasController")
@SessionScoped
public class reportesProcesadasController implements Serializable {

    /**
     * Creates a new instance of reportesController
     */
    private List<Object> reporte;
    private Date Fechafinal;
    private Date Fechainicio;
    private reporteFacade reporteFacade;
    private long total=0;
    private PieChartModel grafica;
    private HorizontalBarChartModel horizontalGrafica;
    private String title="GRÁFICA DE IMÁGENES PROCESADAS";
    
    public reportesProcesadasController() {
    }
    public void save(ActionEvent event) {  
        System.out.println("");
    }
    public void reset()
    {
        Fechainicio=null;
        Fechafinal=null;
        reporte=null;
        horizontalGrafica=null;
        grafica=null;
    }
   
    //metodo generado por el programador para obtener consulta de los reportes por periodos
    public List<Object> generarReporte()
    {   reporteFacade= new reporteFacade();
        if (Fechainicio!=null && Fechafinal!=null) {
            DateFormat  formatofeha= new SimpleDateFormat("yyyy-MM-dd");
            reporte=getFacade().reporteProcesadas(formatofeha.format(Fechainicio),formatofeha.format(Fechafinal));                   
            if(!reporte.isEmpty()){
                procesoChar(reporte);
            }else{
                horizontalGrafica=null;
                grafica=null;
            }   
        }        
        return reporte;
    }
    
     public long getcalcularTotal(){
        total=0;
        if(reporte!=null){
        for (Object obj:reporte) 
        {
            Object[]obj1 =(Object[])obj;
            if(obj1[1]!=null){
            total=total+((long)obj1[1]);
            }
        }
        }
        return total;
    }
     
    public List<Object> getResultadoContoller() {
        return reporte=generarReporte();
    }
    
    public PieChartModel inicioCharPie(){
        PieChartModel pie=new PieChartModel();
        pie.set("No hay",10); 
        pie.setTitle(title);
        return pie;
    }
    
    public HorizontalBarChartModel inicioCharHorizontal(){
            HorizontalBarChartModel horizontal = new HorizontalBarChartModel();
 
            ChartSeries tipo = new ChartSeries();
            tipo.setLabel("Reporte");
            tipo.set("Ho hay", 200);

            horizontal.addSeries(tipo);
            horizontal.setTitle(title);
            Axis xAxis = horizontal.getAxis(AxisType.X);
            xAxis.setLabel("No. Imágenes");
            xAxis.setMin(0);
            xAxis.setMax(200);

            Axis yAxis = horizontal.getAxis(AxisType.Y);
            yAxis.setLabel("Tipo");
            return horizontal;
    }
        
    public void procesoChar(List<Object> resultadoContoller){
            grafica=new PieChartModel();
            grafica.setTitle(title);
            grafica.setShowDataLabels(true);
            grafica.setLegendPosition("w");
            horizontalGrafica = new HorizontalBarChartModel(); 
            ChartSeries tipo = new ChartSeries();
            tipo.setLabel("Reporte");
            for (Object obj:resultadoContoller) 
            {
                Object[]obj1 =(Object[])obj;
                grafica.set(((BigDecimal)obj1[0]).toString(),(Long)obj1[1]);
                tipo.set(((BigDecimal)obj1[0]).toString(),(Long)obj1[1]);
            }
            horizontalGrafica.addSeries(tipo);
            horizontalGrafica.setTitle(title);
            Axis xAxis = horizontalGrafica.getAxis(AxisType.X);
            xAxis.setLabel("No. Imágenes");
            xAxis.setMin(0);
            
            Axis yAxis = horizontalGrafica.getAxis(AxisType.Y);
            yAxis.setLabel("Tipo");
    }
    
    public PieChartModel getGrafica() {
        if(grafica==null){
            grafica=inicioCharPie();
        }
        return grafica;
    }
    public void setGrafica(PieChartModel grafica) {
        this.grafica = grafica;
    }
   
    public HorizontalBarChartModel getHorizontalGrafica() {
        if(horizontalGrafica==null)
        {
            horizontalGrafica = inicioCharHorizontal();
        }
        return horizontalGrafica;
    }
    
    public void setHorizontalGrafica(HorizontalBarChartModel horizontalGrafica) {
        this.horizontalGrafica = horizontalGrafica;
    }
    
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
     
    public Date getFechafinal() {
        return Fechafinal;
    }
    
    public void setFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }
    
    public Date getFechainicio() {
        return Fechainicio;
    }
    
    public void setFechainicio(Date Fechainicio) {
        this.Fechainicio = Fechainicio;
    }

    private reporteFacade getFacade() {
        return reporteFacade;
    }

    public reporteFacade getRepofk() {
        return reporteFacade;
    }
    
    public Date getFechalimite() {
        return Calendar.getInstance().getTime();
    }
}
