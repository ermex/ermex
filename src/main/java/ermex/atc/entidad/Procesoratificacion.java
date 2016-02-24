/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "procesoratificacion", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Procesoratificacion.findByRatificacion", query = "SELECT p FROM Procesoratificacion p where p.idratificacion.idratificacion= :idratificacion order by p.gestor"),
    @NamedQuery(name = "Procesoratificacion.findAll", query = "SELECT p FROM Procesoratificacion p order by p.idratificacion,p.gestor"),
    @NamedQuery(name = "Procesoratificacion.findByIdprocesor", query = "SELECT p FROM Procesoratificacion p WHERE p.idprocesor = :idprocesor"),
    @NamedQuery(name = "Procesoratificacion.findByFreceprati", query = "SELECT p FROM Procesoratificacion p WHERE p.freceprati = :freceprati"),
    @NamedQuery(name = "Procesoratificacion.findByCambios", query = "SELECT p FROM Procesoratificacion p WHERE p.cambios = :cambios"),
    @NamedQuery(name = "Procesoratificacion.findByStatus", query = "SELECT p FROM Procesoratificacion p WHERE p.status = :status"),
    @NamedQuery(name = "Procesoratificacion.findByComentarios", query = "SELECT p FROM Procesoratificacion p WHERE p.comentarios = :comentarios"),
    @NamedQuery(name = "Procesoratificacion.findByFechaEnvioclaves", query = "SELECT p FROM Procesoratificacion p WHERE p.fechaEnvioclaves = :fechaEnvioclaves"),
    @NamedQuery(name = "Procesoratificacion.findByFechaConficlaves", query = "SELECT p FROM Procesoratificacion p WHERE p.fechaConficlaves = :fechaConficlaves"),
    @NamedQuery(name = "Procesoratificacion.findByProceso", query = "SELECT p FROM Procesoratificacion p WHERE p.proceso = :proceso"),
    @NamedQuery(name = "Procesoratificacion.findByDocumentacion", query = "SELECT p FROM Procesoratificacion p WHERE p.documentacion = :documentacion")})
public class Procesoratificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocesor")
    private Long idprocesor;
    @Column(name = "freceprati")
    @Temporal(TemporalType.DATE)
    private Date freceprati;
    @Column(name = "cambios")
    private Boolean cambios;
    @Size(max = 10)
    @Column(name = "status")
    private String status="proceso";
    @Size(max = 2147483647)
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "fecha_envioclaves")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvioclaves;
    @Column(name = "fecha_conficlaves")
    @Temporal(TemporalType.DATE)
    private Date fechaConficlaves;
    @Column(name = "proceso")
    private BigInteger proceso;
    @Size(max = 2147483647)
    @Column(name = "documentacion")
    private String documentacion;
    @JoinColumn(name = "idratificacion", referencedColumnName = "idratificacion")
    @ManyToOne
    @NotNull
    private Ratificaciones idratificacion;
    @JoinColumn(name = "gestor", referencedColumnName = "gestor")
    @ManyToOne
    @NotNull
    private Gestores gestor;

    public Procesoratificacion() {
    }

    public Procesoratificacion(Long idprocesor) {
        this.idprocesor = idprocesor;
    }

    public Long getIdprocesor() {
        return idprocesor;
    }

    public void setIdprocesor(Long idprocesor) {
        this.idprocesor = idprocesor;
    }

    public Date getFreceprati() {
        return freceprati;
    }

    public void setFreceprati(Date freceprati) {
        this.freceprati = freceprati;
    }

    public Boolean getCambios() {
        return cambios;
    }

    public void setCambios(Boolean cambios) {
        this.cambios = cambios;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaEnvioclaves() {
        return fechaEnvioclaves;
    }

    public void setFechaEnvioclaves(Date fechaEnvioclaves) {
        this.fechaEnvioclaves = fechaEnvioclaves;
    }

    public Date getFechaConficlaves() {
        return fechaConficlaves;
    }

    public void setFechaConficlaves(Date fechaConficlaves) {
        this.fechaConficlaves = fechaConficlaves;
    }

    public BigInteger getProceso() {
        return proceso;
    }

    public void setProceso(BigInteger proceso) {
        this.proceso = proceso;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public Ratificaciones getIdratificacion() {
        return idratificacion;
    }

    public void setIdratificacion(Ratificaciones idratificacion) {
        this.idratificacion = idratificacion;
    }

    public Gestores getGestor() {
        return gestor;
    }

    public void setGestor(Gestores gestor) {
        this.gestor = gestor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocesor != null ? idprocesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesoratificacion)) {
            return false;
        }
        Procesoratificacion other = (Procesoratificacion) object;
        if ((this.idprocesor == null && other.idprocesor != null) || (this.idprocesor != null && !this.idprocesor.equals(other.idprocesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Procesoratificacion[ idprocesor=" + idprocesor + " ]";
    }
    
}
