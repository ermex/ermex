/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "imagnesolicitudes", catalog = "gestor", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagnesolicitudes.findAll", query = "SELECT i FROM Imagnesolicitudes i"),
    @NamedQuery(name = "Imagnesolicitudes.findSolicitud", query = "SELECT i FROM Imagnesolicitudes i where i.solicitud= :solicitud"),
    @NamedQuery(name = "Imagnesolicitudes.findByIdmagenesolicitud", query = "SELECT i FROM Imagnesolicitudes i WHERE i.idmagenesolicitud = :idmagenesolicitud")})
public class Imagnesolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmagenesolicitud")
    private Integer idmagenesolicitud;
    @JoinColumn(name = "solicitud", referencedColumnName = "solicitud")
    @ManyToOne
    private SolicitudesInternet solicitud;
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @ManyToOne 
    private Catalogoimagenes identificador;

    public Imagnesolicitudes() {
    }

    public Imagnesolicitudes(Integer idmagenesolicitud) {
        this.idmagenesolicitud = idmagenesolicitud;
    }

    public Integer getIdmagenesolicitud() {
        return idmagenesolicitud;
    }

    public void setIdmagenesolicitud(Integer idmagenesolicitud) {
        this.idmagenesolicitud = idmagenesolicitud;
    }

    public SolicitudesInternet getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudesInternet solicitud) {
        this.solicitud = solicitud;
    }

    public Catalogoimagenes getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Catalogoimagenes identificador) {
        this.identificador = identificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmagenesolicitud != null ? idmagenesolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagnesolicitudes)) {
            return false;
        }
        Imagnesolicitudes other = (Imagnesolicitudes) object;
        if ((this.idmagenesolicitud == null && other.idmagenesolicitud != null) || (this.idmagenesolicitud != null && !this.idmagenesolicitud.equals(other.idmagenesolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Imagnesolicitudes[ idmagenesolicitud=" + idmagenesolicitud + " ]";
    }
    
}
