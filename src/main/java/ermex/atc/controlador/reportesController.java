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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ermex
 */
@Named(value = "reportesController")
@SessionScoped
public class reportesController implements Serializable {

    /**
     * Creates a new instance of reportesController
     */
    private List<Object> resultadoContoller;
    private Date Fechafinal;
    private Date Fechainicio;
    private reporteFacade repofk;
    private int opcion;
    private long total=0;
    private String encabezadoTabla;
    private PieChartModel grafica;
    private HorizontalBarChartModel horizontalGrafica;
    
    public reportesController() {
        encabezadoTabla="DEPENDENCIA";
        opcion=1;
    }
    
    public void reset()
    {
        opcion=1;
        encabezadoTabla="DEPENDENCIA";
        Fechainicio=null;
        Fechafinal=null;
        resultadoContoller=null;
        horizontalGrafica=null;
        grafica=null;
    }
    
        //metodo generado por el programador para cabiar en encabezado de la tabla de list de reportes
    private void asiganrEncabezado()
    {
        System.out.println("Valor del opcion en el case " + opcion);
        switch(opcion)
        {
            case 1 :
                encabezadoTabla="DEPENDENCIA";
                break;
            case 2 :
                encabezadoTabla="ORGANISMO";
                break;
            case 3 :
                encabezadoTabla="INSTITUCIÓN";
                break;
            case 4 :
                encabezadoTabla="GESTOR";
                break;
                
        }
    }  
    //metodo generado por el programador para obtener consulta de los reportes por periodos
    // ya sea por dependencia, organismo, institucion y gestor
    public List<Object> generarReporte()
    {   repofk= new reporteFacade();
        if (Fechainicio!=null && Fechafinal!=null & opcion!=0) {
            DateFormat  formatofeha= new SimpleDateFormat("yyyy-MM-dd");
            resultadoContoller=getFacade().reporteDepen(formatofeha.format(Fechainicio),formatofeha.format(Fechafinal), this.opcion);  
            if(!resultadoContoller.isEmpty()){
                procesoChar(resultadoContoller);
            }else{
                horizontalGrafica=null;
                grafica=null;
            }   
        }        
        return resultadoContoller;
    }
    
     public long getcalcularTotal(){
        total=0;
        if(resultadoContoller!=null){
        for (Object obj:resultadoContoller) 
        {
            Object[]obj1 =(Object[])obj;
            if(obj1[1]!=null){
            total=total+((BigDecimal)obj1[1]).longValue();}
        }
        }
        return total;
    }
     
    public List<Object> getResultadoContoller() {
        return resultadoContoller=generarReporte();
    }
    
    public PieChartModel inicioCharPie(){
        PieChartModel pie=new PieChartModel();
        pie.set("No hay",10); 
        pie.setTitle("GRAFICA");
        return pie;
    }
    
    public HorizontalBarChartModel inicioCharHorizontal(){
            HorizontalBarChartModel horizontal = new HorizontalBarChartModel();
 
            ChartSeries tipo = new ChartSeries();
            tipo.setLabel("Reporte");
            tipo.set("Ho hay", 200);

            horizontal.addSeries(tipo);
            horizontal.setTitle("GRAFICA");
            Axis xAxis = horizontal.getAxis(AxisType.X);
            xAxis.setLabel("No. Imagenes");
            xAxis.setMin(0);
            xAxis.setMax(200);

            Axis yAxis = horizontal.getAxis(AxisType.Y);
            yAxis.setLabel("Tipo");
            return horizontal;
    }
        
    public void procesoChar(List<Object> resultadoContoller){
            grafica=new PieChartModel();
            grafica.setTitle("GRAFICA");
            horizontalGrafica = new HorizontalBarChartModel(); 
            ChartSeries tipo = new ChartSeries();
            tipo.setLabel("Reporte");
            for (Object obj:resultadoContoller) 
            {
                Object[]obj1 =(Object[])obj;
                grafica.set((String)obj1[0],(BigDecimal)obj1[1]);
                tipo.set((String)obj1[0],(BigDecimal)obj1[1]);
            }
            horizontalGrafica.addSeries(tipo);
            horizontalGrafica.setTitle("GRAFICA");
            Axis xAxis = horizontalGrafica.getAxis(AxisType.X);
            xAxis.setLabel("No. Imagenes");
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

    public int getOpcion() {
        return opcion;
    }
    
    public void setOpcion(int opcion) {
        this.opcion = opcion;
        asiganrEncabezado();
    }
    
   private reporteFacade getFacade() {
        return repofk;
    }

    public reporteFacade getRepofk() {
        return repofk;
    }

    public String getEncabezadoTabla() {
        return encabezadoTabla;
    }
    
    public Date getFechalimite() {
        return Calendar.getInstance().getTime();
    }
}
