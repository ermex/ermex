/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "catalogoimagenes", catalog = "gestor", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogoimagenes.findAll", query = "SELECT c FROM Catalogoimagenes c"),
    @NamedQuery(name = "Catalogoimagenes.findBySatelite", query = "SELECT c FROM Catalogoimagenes c WHERE c.satelite = :satelite"),
    @NamedQuery(name = "Catalogoimagenes.findByTipo", query = "SELECT c FROM Catalogoimagenes c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Catalogoimagenes.findByNivel", query = "SELECT c FROM Catalogoimagenes c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Catalogoimagenes.findByStatus", query = "SELECT c FROM Catalogoimagenes c WHERE c.status = :status"),
    @NamedQuery(name = "Catalogoimagenes.findByIdentificador", query = "SELECT c FROM Catalogoimagenes c WHERE c.identificador = :identificador"),
    @NamedQuery(name = "Catalogoimagenes.findByResolucion", query = "SELECT c FROM Catalogoimagenes c WHERE c.resolucion = :resolucion")})
public class Catalogoimagenes implements Serializable {


    private static final long serialVersionUID = 1L;
    @Column(name = "sateli")
    private String satelite;
    @Size(max = 5)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 3)
    @Column(name = "nivel")
    private String nivel;
    @Size(max = 15)
    @Column(name = "status")
    private String status="activo";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "identificador")
    private String identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "resolucion")
    private Double resolucion;
    @OneToMany(mappedBy = "identificador")
    private List<Imagnesolicitudes> imagnesolicitudesList;

    public Catalogoimagenes() {
    }

    public Catalogoimagenes(String identificador) {
        this.identificador = identificador;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Double getResolucion() {
        return resolucion;
    }

    public void setResolucion(Double resolucion) {
        this.resolucion = resolucion;
    }

    @XmlTransient
    public List<Imagnesolicitudes> getImagnesolicitudesList() {
        return imagnesolicitudesList;
    }

    public void setImagnesolicitudesList(List<Imagnesolicitudes> imagnesolicitudesList) {
        this.imagnesolicitudesList = imagnesolicitudesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogoimagenes)) {
            return false;
        }
        Catalogoimagenes other = (Catalogoimagenes) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Catalogoimagenes[ identificador=" + identificador + " ]";
    }

    public String getSatelite() {
        return satelite;
    }

    public void setSatelite(String satelite) {
        this.satelite = satelite;
    }
    
}
