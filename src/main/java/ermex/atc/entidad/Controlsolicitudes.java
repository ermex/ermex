/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "controlsolicitudes", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Controlsolicitudes.findAll", query = "SELECT c FROM Controlsolicitudes c"),
    @NamedQuery(name = "Controlsolicitudes.findByIdcontrolsolicitud", query = "SELECT c FROM Controlsolicitudes c WHERE c.idcontrolsolicitud = :idcontrolsolicitud"),
    @NamedQuery(name = "Controlsolicitudes.findByGestor", query = "SELECT c FROM Controlsolicitudes c WHERE c.gestor = :gestor"),
    @NamedQuery(name = "Controlsolicitudes.findByFechaasignacion", query = "SELECT c FROM Controlsolicitudes c WHERE c.fechaasignacion = :fechaasignacion"),
    @NamedQuery(name = "Controlsolicitudes.findByTema", query = "SELECT c FROM Controlsolicitudes c WHERE c.tema = :tema"),
    @NamedQuery(name = "Controlsolicitudes.findBySolicitud", query = "SELECT c FROM Controlsolicitudes c WHERE c.solicitud = :solicitud"),
    @NamedQuery(name = "Controlsolicitudes.findByObservaciones", query = "SELECT c FROM Controlsolicitudes c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Controlsolicitudes.findByStatus", query = "SELECT c FROM Controlsolicitudes c WHERE c.status = :status"),
    @NamedQuery(name = "Controlsolicitudes.findByFechatermino", query = "SELECT c FROM Controlsolicitudes c WHERE c.fechatermino = :fechatermino"),
    @NamedQuery(name = "Controlsolicitudes.findByFechacancelacion", query = "SELECT c FROM Controlsolicitudes c WHERE c.fechacancelacion = :fechacancelacion"),
    @NamedQuery(name = "Controlsolicitudes.findByTotalimagenes", query = "SELECT c FROM Controlsolicitudes c WHERE c.totalimagenes = :totalimagenes")})
public class Controlsolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcontrolsolicitud")
    private Integer idcontrolsolicitud;
    @Column(name = "fechaasignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaasignacion;
    @Size(max = 2147483647)
    @Column(name = "tema")
    private String tema;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Column(name = "fechatermino")
    @Temporal(TemporalType.DATE)
    private Date fechatermino;
    @Column(name = "fechacancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechacancelacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalimagenes")
    private int totalimagenes;
    @OneToMany(mappedBy = "idcontrolsolicitud")
    private List<Notas> notasList;
    @JoinColumn(name = "solicitud", referencedColumnName = "solicitud")
    @OneToOne
    private SolicitudesInternet solicitud;
    @JoinColumn(name = "idpersonalatencion", referencedColumnName = "usuario")
    @ManyToOne
    private Personalatencionusuarios idpersonalatencion;
    @JoinColumn(name = "proyecto", referencedColumnName = "numero")
    @ManyToOne
    private NomProyectos proyecto;
    @JoinColumn(name = "gestor", referencedColumnName = "gestor")
    @ManyToOne
    private Gestores gestor;

    public Controlsolicitudes() {
    }

    public Controlsolicitudes(Integer idcontrolsolicitud) {
        this.idcontrolsolicitud = idcontrolsolicitud;
    }

    public Controlsolicitudes(Integer idcontrolsolicitud, int status, int totalimagenes) {
        this.idcontrolsolicitud = idcontrolsolicitud;
        this.status = status;
        this.totalimagenes = totalimagenes;
    }

    public Integer getIdcontrolsolicitud() {
        return idcontrolsolicitud;
    }

    public void setIdcontrolsolicitud(Integer idcontrolsolicitud) {
        this.idcontrolsolicitud = idcontrolsolicitud;
    }

    public Gestores getGestor() {
        return gestor;
    }

    public void setGestor(Gestores gestor) {
        this.gestor = gestor;
    }


    public Date getFechaasignacion() {
        return fechaasignacion;
    }

    public void setFechaasignacion(Date fechaasignacion) {
        this.fechaasignacion = fechaasignacion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(Date fechatermino) {
        this.fechatermino = fechatermino;
    }

    public Date getFechacancelacion() {
        return fechacancelacion;
    }

    public void setFechacancelacion(Date fechacancelacion) {
        this.fechacancelacion = fechacancelacion;
    }

    public int getTotalimagenes() {
        return totalimagenes;
    }

    public void setTotalimagenes(int totalimagenes) {
        this.totalimagenes = totalimagenes;
    }

    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    public SolicitudesInternet getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudesInternet solicitud) {
        this.solicitud = solicitud;
    }

    public Personalatencionusuarios getIdpersonalatencion() {
        return idpersonalatencion;
    }

    public void setIdpersonalatencion(Personalatencionusuarios idpersonalatencion) {
        this.idpersonalatencion = idpersonalatencion;
    }

    public NomProyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(NomProyectos proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrolsolicitud != null ? idcontrolsolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controlsolicitudes)) {
            return false;
        }
        Controlsolicitudes other = (Controlsolicitudes) object;
        if ((this.idcontrolsolicitud == null && other.idcontrolsolicitud != null) || (this.idcontrolsolicitud != null && !this.idcontrolsolicitud.equals(other.idcontrolsolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idcontrolsolicitud.toString();
    }
    
}
