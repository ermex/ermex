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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "dependencias", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Dependencias.findAll", query = "SELECT d FROM Dependencias d"),
    @NamedQuery(name = "Dependencias.findByIddependencia", query = "SELECT d FROM Dependencias d WHERE d.iddependencia = :iddependencia"),
    @NamedQuery(name = "Dependencias.findBySiglas", query = "SELECT d FROM Dependencias d WHERE d.siglas = :siglas"),
    @NamedQuery(name = "Dependencias.findByNombre", query = "SELECT d FROM Dependencias d WHERE d.nombre = :nombre")})
public class Dependencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddependencia")
    private Long iddependencia;
    @Size(max = 30)
    @Column(name = "siglas")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "iddependencia")
    private List<Organismos> organismosList;

    public Dependencias() {
    }

    public Dependencias(Long iddependencia) {
        this.iddependencia = iddependencia;
    }

    public Dependencias(Long iddependencia, String nombre) {
        this.iddependencia = iddependencia;
        this.nombre = nombre;
    }

    public Long getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(Long iddependencia) {
        this.iddependencia = iddependencia;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Organismos> getOrganismosList() {
        return organismosList;
    }

    public void setOrganismosList(List<Organismos> organismosList) {
        this.organismosList = organismosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddependencia != null ? iddependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencias)) {
            return false;
        }
        Dependencias other = (Dependencias) object;
        if ((this.iddependencia == null && other.iddependencia != null) || (this.iddependencia != null && !this.iddependencia.equals(other.iddependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Dependencias[ iddependencia=" + iddependencia + " ]";
    }
    
}
