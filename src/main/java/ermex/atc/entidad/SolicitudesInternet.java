/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "solicitudes_internet", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "SolicitudesInternet.findAll", query = "SELECT s FROM SolicitudesInternet s order by s.solicitud"),
    @NamedQuery(name = "SolicitudesInternet.findBySolicitud", query = "SELECT s FROM SolicitudesInternet s WHERE s.solicitud = :solicitud"),
    @NamedQuery(name = "SolicitudesInternet.findByGestor", query = "SELECT s FROM SolicitudesInternet s WHERE s.gestor = :gestor"),
    @NamedQuery(name = "SolicitudesInternet.findByNombre", query = "SELECT s FROM SolicitudesInternet s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SolicitudesInternet.findByDependencia", query = "SELECT s FROM SolicitudesInternet s WHERE s.dependencia = :dependencia"),
    @NamedQuery(name = "SolicitudesInternet.findByOrganismo", query = "SELECT s FROM SolicitudesInternet s WHERE s.organismo = :organismo"),
    @NamedQuery(name = "SolicitudesInternet.findByTema", query = "SELECT s FROM SolicitudesInternet s WHERE s.tema = :tema"),
    @NamedQuery(name = "SolicitudesInternet.findByNubosidad", query = "SELECT s FROM SolicitudesInternet s WHERE s.nubosidad = :nubosidad"),
    @NamedQuery(name = "SolicitudesInternet.findByModo", query = "SELECT s FROM SolicitudesInternet s WHERE s.modo = :modo"),
    @NamedQuery(name = "SolicitudesInternet.findByResolucion", query = "SELECT s FROM SolicitudesInternet s WHERE s.resolucion = :resolucion"),
    @NamedQuery(name = "SolicitudesInternet.findByNivel", query = "SELECT s FROM SolicitudesInternet s WHERE s.nivel = :nivel"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo1I", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo1I = :periodo1I"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo1F", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo1F = :periodo1F"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo2I", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo2I = :periodo2I"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo2F", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo2F = :periodo2F"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo3I", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo3I = :periodo3I"),
    @NamedQuery(name = "SolicitudesInternet.findByPeriodo3F", query = "SELECT s FROM SolicitudesInternet s WHERE s.periodo3F = :periodo3F"),
    @NamedQuery(name = "SolicitudesInternet.findByFechaCaptura", query = "SELECT s FROM SolicitudesInternet s WHERE s.fechaCaptura = :fechaCaptura"),
    @NamedQuery(name = "SolicitudesInternet.findByFechaVentanilla", query = "SELECT s FROM SolicitudesInternet s WHERE s.fechaVentanilla = :fechaVentanilla"),
    @NamedQuery(name = "SolicitudesInternet.findByJustificacion", query = "SELECT s FROM SolicitudesInternet s WHERE s.justificacion = :justificacion"),
    @NamedQuery(name = "SolicitudesInternet.findByStatus", query = "SELECT s FROM SolicitudesInternet s WHERE s.status = :status")})
public class SolicitudesInternet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "solicitud")
    private String solicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "gestor")
    private String gestor;
    @Size(max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 90)
    @Column(name = "dependencia")
    private String dependencia;
    @Size(max = 100)
    @Column(name = "organismo")
    private String organismo;
    @Size(max = 60)
    @Column(name = "tema")
    private String tema;
    @Size(max = 30)
    @Column(name = "nubosidad")
    private String nubosidad;
    @Size(max = 50)
    @Column(name = "modo")
    private String modo;
    @Size(max = 2147483647)
    @Column(name = "resolucion")
    private String resolucion;
    @Size(max = 50)
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "periodo1_i")
    @Temporal(TemporalType.DATE)
    private Date periodo1I;
    @Column(name = "periodo1_f")
    @Temporal(TemporalType.DATE)
    private Date periodo1F;
    @Column(name = "periodo2_i")
    @Temporal(TemporalType.DATE)
    private Date periodo2I;
    @Column(name = "periodo2_f")
    @Temporal(TemporalType.DATE)
    private Date periodo2F;
    @Column(name = "periodo3_i")
    @Temporal(TemporalType.DATE)
    private Date periodo3I;
    @Column(name = "periodo3_f")
    @Temporal(TemporalType.DATE)
    private Date periodo3F;
    @Column(name = "fecha_captura")
    @Temporal(TemporalType.DATE)
    private Date fechaCaptura;
    @Column(name = "fecha_ventanilla")
    @Temporal(TemporalType.DATE)
    private Date fechaVentanilla;
    @Size(max = 2147483647)
    @Column(name = "justificacion")
    private String justificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToOne(mappedBy = "solicitud")
    private Controlsolicitudes controlsolicitudes;

    public SolicitudesInternet() {
        this.fechaVentanilla = Calendar.getInstance().getTime();
        this.fechaCaptura=Calendar.getInstance().getTime();
        this.status=2;
        
       }

    public SolicitudesInternet(String solicitud) {
        this.fechaVentanilla = Calendar.getInstance().getTime();
        this.solicitud = solicitud;
    }

    public SolicitudesInternet(String solicitud, String gestor, int status) {
        this.fechaVentanilla = Calendar.getInstance().getTime();
        this.solicitud = solicitud;
        this.gestor = gestor;
        this.status = status;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNubosidad() {
        return nubosidad;
    }

    public void setNubosidad(String nubosidad) {
        this.nubosidad = nubosidad;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Date getPeriodo1I() {
        return periodo1I;
    }

    public void setPeriodo1I(Date periodo1I) {
        this.periodo1I = periodo1I;
    }

    public Date getPeriodo1F() {
        return periodo1F;
    }

    public void setPeriodo1F(Date periodo1F) {
        this.periodo1F = periodo1F;
    }

    public Date getPeriodo2I() {
        return periodo2I;
    }

    public void setPeriodo2I(Date periodo2I) {
        this.periodo2I = periodo2I;
    }

    public Date getPeriodo2F() {
        return periodo2F;
    }

    public void setPeriodo2F(Date periodo2F) {
        this.periodo2F = periodo2F;
    }

    public Date getPeriodo3I() {
        return periodo3I;
    }

    public void setPeriodo3I(Date periodo3I) {
        this.periodo3I = periodo3I;
    }

    public Date getPeriodo3F() {
        return periodo3F;
    }

    public void setPeriodo3F(Date periodo3F) {
        this.periodo3F = periodo3F;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaVentanilla() {
        return fechaVentanilla;
    }

    public void setFechaVentanilla(Date fechaVentanilla) {
        this.fechaVentanilla = fechaVentanilla;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Controlsolicitudes getControlsolicitudes() {
        return controlsolicitudes;
    }

    public void setControlsolicitudes(Controlsolicitudes controlsolicitudes) {
        this.controlsolicitudes = controlsolicitudes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitud != null ? solicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudesInternet)) {
            return false;
        }
        SolicitudesInternet other = (SolicitudesInternet) object;
        if ((this.solicitud == null && other.solicitud != null) || (this.solicitud != null && !this.solicitud.equals(other.solicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return solicitud ;
    }
    
}
