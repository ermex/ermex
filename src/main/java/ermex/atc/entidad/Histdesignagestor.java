/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
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

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "histdesignagestor", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Histdesignagestor.findAll", query = "SELECT h FROM Histdesignagestor h"),
    @NamedQuery(name = "Histdesignagestor.findByIdhdg", query = "SELECT h FROM Histdesignagestor h WHERE h.idhdg = :idhdg"),
    @NamedQuery(name = "Histdesignagestor.findByFechaInicio", query = "SELECT h FROM Histdesignagestor h WHERE h.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Histdesignagestor.findByFechaFin", query = "SELECT h FROM Histdesignagestor h WHERE h.fechaFin = :fechaFin"),
    @NamedQuery(name = "Histdesignagestor.findByStatus", query = "SELECT h FROM Histdesignagestor h WHERE h.status = :status")})
public class Histdesignagestor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhdg")
    private Long idhdg;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "iddesigna", referencedColumnName = "idpersona")
    @ManyToOne
    private Personas iddesigna;
    @JoinColumn(name = "gestor", referencedColumnName = "gestor")
    @ManyToOne
    private Gestores gestor;

    public Histdesignagestor() {
    }

    public Histdesignagestor(Long idhdg) {
        this.idhdg = idhdg;
    }

    public Long getIdhdg() {
        return idhdg;
    }

    public void setIdhdg(Long idhdg) {
        this.idhdg = idhdg;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Personas getIddesigna() {
        return iddesigna;
    }

    public void setIddesigna(Personas iddesigna) {
        this.iddesigna = iddesigna;
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
        hash += (idhdg != null ? idhdg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Histdesignagestor)) {
            return false;
        }
        Histdesignagestor other = (Histdesignagestor) object;
        if ((this.idhdg == null && other.idhdg != null) || (this.idhdg != null && !this.idhdg.equals(other.idhdg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Histdesignagestor[ idhdg=" + idhdg + " ]";
    }
    
}
