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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ratificaciones", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Ratificaciones.findAll", query = "SELECT r FROM Ratificaciones r order by r.idratificacion"),
    @NamedQuery(name = "Ratificaciones.findByIdratificacion", query = "SELECT r FROM Ratificaciones r WHERE r.idratificacion = :idratificacion"),
    @NamedQuery(name = "Ratificaciones.findByInicio", query = "SELECT r FROM Ratificaciones r WHERE r.inicio = :inicio"),
    @NamedQuery(name = "Ratificaciones.findByFin", query = "SELECT r FROM Ratificaciones r WHERE r.fin = :fin"),
    @NamedQuery(name = "Ratificaciones.findByResponsables", query = "SELECT r FROM Ratificaciones r WHERE r.responsables = :responsables"),
    @NamedQuery(name = "Ratificaciones.findByResponsablee", query = "SELECT r FROM Ratificaciones r WHERE r.responsablee = :responsablee"),
    @NamedQuery(name = "Ratificaciones.findByComentarios", query = "SELECT r FROM Ratificaciones r WHERE r.comentarios = :comentarios"),
    @NamedQuery(name = "Ratificaciones.findByStatus", query = "SELECT r FROM Ratificaciones r WHERE r.status = :status")})
public class Ratificaciones implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status=true;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idratificacion")
    private Long idratificacion;
    @Column(name = "inicio")
    @Temporal(TemporalType.DATE)
    private Date inicio;
    @Column(name = "fin")
    @Temporal(TemporalType.DATE)
    private Date fin;
    @Size(max = 2147483647)
    @Column(name = "responsables")
    private String responsables;
    @Size(max = 2147483647)
    @Column(name = "responsablee")
    private String responsablee;
    @Size(max = 2147483647)
    @Column(name = "comentarios")
    private String comentarios;
    @OneToMany(mappedBy = "idratificacion")
    private List<Procesoratificacion> procesoratificacionList;

    public Ratificaciones() {
    }

    public Ratificaciones(Long idratificacion) {
        this.idratificacion = idratificacion;
    }

    public Ratificaciones(Long idratificacion, Date anio) {
        this.idratificacion = idratificacion;
    }

    public Long getIdratificacion() {
        return idratificacion;
    }

    public void setIdratificacion(Long idratificacion) {
        this.idratificacion = idratificacion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getResponsables() {
        return responsables;
    }

    public void setResponsables(String responsables) {
        this.responsables = responsables;
    }

    public String getResponsablee() {
        return responsablee;
    }

    public void setResponsablee(String responsablee) {
        this.responsablee = responsablee;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


    public List<Procesoratificacion> getProcesoratificacionList() {
        return procesoratificacionList;
    }

    public void setProcesoratificacionList(List<Procesoratificacion> procesoratificacionList) {
        this.procesoratificacionList = procesoratificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idratificacion != null ? idratificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratificaciones)) {
            return false;
        }
        Ratificaciones other = (Ratificaciones) object;
        if ((this.idratificacion == null && other.idratificacion != null) || (this.idratificacion != null && !this.idratificacion.equals(other.idratificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Ratificaciones[ idratificacion=" + idratificacion + " ]";
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
