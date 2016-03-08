/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "gestores",  schema = "public")
@NamedQueries({
    @NamedQuery(name = "Gestores.findAll", query = "SELECT g FROM Gestores g order by g.gestor"),
    @NamedQuery(name = "Gestores.findByGestor", query = "SELECT g FROM Gestores g WHERE g.gestor = :gestor"),
    @NamedQuery(name = "Gestores.findByOficio", query = "SELECT g FROM Gestores g WHERE g.oficio = :oficio"),
    @NamedQuery(name = "Gestores.findByFechaOficio", query = "SELECT g FROM Gestores g WHERE g.fechaOficio = :fechaOficio"),
    @NamedQuery(name = "Gestores.findByPwdftp", query = "SELECT g FROM Gestores g WHERE g.pwdftp = :pwdftp"),
    @NamedQuery(name = "Gestores.findByPwdwms", query = "SELECT g FROM Gestores g WHERE g.pwdwms = :pwdwms"),
    @NamedQuery(name = "Gestores.findByFechaEnviouser", query = "SELECT g FROM Gestores g WHERE g.fechaEnviouser = :fechaEnviouser"),
    @NamedQuery(name = "Gestores.findByFechaInicio", query = "SELECT g FROM Gestores g WHERE g.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Gestores.findByFechaFin", query = "SELECT g FROM Gestores g WHERE g.fechaFin = :fechaFin"),
    @NamedQuery(name = "Gestores.findByStatus", query = "SELECT g FROM Gestores g WHERE g.status = :status order by g.gestor"),
    @NamedQuery(name = "Gestores.findByIdorganismo", query = "SELECT g FROM Gestores g WHERE g.idorganismo = :idorganismo"),
    @NamedQuery(name = "Gestores.findByDescripcion1", query = "SELECT g FROM Gestores g WHERE g.descripcion1 = :descripcion1"),
    @NamedQuery(name = "Gestores.findByDescripcion2", query = "SELECT g FROM Gestores g WHERE g.descripcion2 = :descripcion2"),
    @NamedQuery(name = "Gestores.findByEstado", query = "SELECT g FROM Gestores g WHERE g.estado = :estado"),
    @NamedQuery(name = "Gestores.findByNoStatus", query = "SELECT g FROM Gestores g WHERE g.status != :status order by g.gestor"),
    @NamedQuery(name = "Gestores.findByMunicipio", query = "SELECT g FROM Gestores g WHERE g.municipio = :municipio"),
    @NamedQuery(name = "Gestores.findByDesignacion", query = "SELECT g FROM Gestores g WHERE g.designacion = :designacion")})
public class Gestores implements Serializable {

    @Lob
    @Column(name = "designacion")
    private byte[] designacion;
    @Size(max = 2147483647)
    @Column(name = "comentarios")
    private String comentarios;
    @OneToMany(mappedBy = "gestor")
    private List<Histdesignagestor> histdesignagestorList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "gestor")
    @Pattern(regexp="(^ermex[0-9]{4}$)|^sola0024$")
    private String gestor;
    @Size(max = 2147483647)
    @Column(name = "oficio")
    private String oficio;
    @Column(name = "fecha_oficio")
    @Temporal(TemporalType.DATE)
    private Date fechaOficio;
    @Pattern(regexp="(^[\\w]{9}$)|^\\s*$")
    @Size(max = 2147483647)
    @Column(name = "pwdftp")
    private String pwdftp;
    @Pattern(regexp="(^[\\w]{9}$)|^\\s*$")
    @Size(max = 2147483647)
    @Column(name = "pwdwms")
    private String pwdwms;
    @Column(name = "fecha_enviouser")
    @Temporal(TemporalType.DATE)
    private Date fechaEnviouser;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 15)
    @Column(name = "status")
    private String status;
    @Column(name = "idorganismo")
    private BigInteger idorganismo;
    @Size(max = 20)
    @Column(name = "descripcion1")
    private String descripcion1;
    @Size(max = 20)
    @Column(name = "descripcion2")
    private String descripcion2;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "municipio")
    private Short municipio;
    @OneToMany(mappedBy = "gestor")
    private List<Procesoratificacion> procesoratificacionList;
    @OneToMany(mappedBy = "gestor")
    private List<Controlsolicitudes> controlsolicitudesList;
    @NotNull
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne
    private Personas idpersona;
    @NotNull
    @JoinColumn(name = "designador", referencedColumnName = "idpersona")
    @ManyToOne
    private Personas designador;

    public Gestores() {
    }

    public Gestores(String gestor) {
        this.gestor = gestor;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        
        this.gestor = gestor;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Date getFechaOficio() {
        return fechaOficio;
    }

    public void setFechaOficio(Date fechaOficio) {
        this.fechaOficio = fechaOficio;
    }

    public String getPwdftp() {
        return pwdftp;
    }

    public void setPwdftp(String pwdftp) {
        this.pwdftp = pwdftp;
    }

    public String getPwdwms() {
        return pwdwms;
    }

    public void setPwdwms(String pwdwms) {
        this.pwdwms = pwdwms;
    }

    public Date getFechaEnviouser() {
        return fechaEnviouser;
    }

    public void setFechaEnviouser(Date fechaEnviouser) {
        this.fechaEnviouser = fechaEnviouser;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getIdorganismo() {
        return idorganismo;
    }

    public void setIdorganismo(BigInteger idorganismo) {
        this.idorganismo = idorganismo;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Short getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Short municipio) {
        this.municipio = municipio;
    }


    public List<Procesoratificacion> getProcesoratificacionList() {
        return procesoratificacionList;
    }

    public void setProcesoratificacionList(List<Procesoratificacion> procesoratificacionList) {
        this.procesoratificacionList = procesoratificacionList;
    }

    public Personas getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Personas idpersona) {
        this.idpersona = idpersona;
    }

    public Personas getDesignador() {
        return designador;
    }

    public void setDesignador(Personas designador) {
        this.designador = designador;
    }

    public List<Controlsolicitudes> getControlsolicitudesList() {
        return controlsolicitudesList;
    }

    public void setControlsolicitudesList(List<Controlsolicitudes> controlsolicitudesList) {
        this.controlsolicitudesList = controlsolicitudesList;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gestor != null ? gestor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gestores)) {
            return false;
        }
        Gestores other = (Gestores) object;
        if ((this.gestor == null && other.gestor != null) || (this.gestor != null && !this.gestor.equals(other.gestor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return gestor;
    }


    public List<Histdesignagestor> getHistdesignagestorList() {
        return histdesignagestorList;
    }

    public void setHistdesignagestorList(List<Histdesignagestor> histdesignagestorList) {
        this.histdesignagestorList = histdesignagestorList;
    }

    public byte[] getDesignacion() {
        return designacion;
    }

    public void setDesignacion(byte[] designacion) {
        this.designacion = designacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
}
