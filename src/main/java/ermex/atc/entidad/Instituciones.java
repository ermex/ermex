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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "instituciones", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Instituciones.findAll", query = "SELECT i FROM Instituciones i ORDER BY i.idinstitucion"),
    @NamedQuery(name = "Instituciones.findByIdinstitucion", query = "SELECT i FROM Instituciones i WHERE i.idinstitucion = :idinstitucion"),
    @NamedQuery(name = "Instituciones.findByNombre", query = "SELECT i FROM Instituciones i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Instituciones.findByOrganismos", query = "SELECT i FROM Instituciones i where i.idorganismo.idorganismo = :idorganismo"),
    @NamedQuery(name = "Instituciones.findByTipo", query = "SELECT i FROM Instituciones i WHERE i.tipo = :tipo")})
public class Instituciones implements Serializable {

    @OneToMany(mappedBy = "idinstitucion")
    private List<Cambiopersona> cambiopersonaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinstitucion")
    private Long idinstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idorganismo", referencedColumnName = "idorganismo")
    @ManyToOne
    @NotNull
    private Organismos idorganismo;
    @OneToMany(mappedBy = "idinstitucion")
    private List<Personas> personasList;

    public Instituciones() {
    }

    public Instituciones(Long idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public Instituciones(Long idinstitucion, String nombre, String tipo) {
        this.idinstitucion = idinstitucion;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Long getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Long idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Organismos getIdorganismo() {
        return idorganismo;
    }

    public void setIdorganismo(Organismos idorganismo) {
        this.idorganismo = idorganismo;
    }

    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstitucion != null ? idinstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instituciones)) {
            return false;
        }
        Instituciones other = (Instituciones) object;
        if ((this.idinstitucion == null && other.idinstitucion != null) || (this.idinstitucion != null && !this.idinstitucion.equals(other.idinstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Instituciones[ idinstitucion=" + idinstitucion + " ]";
    }

    public List<Cambiopersona> getCambiopersonaList() {
        return cambiopersonaList;
    }

    public void setCambiopersonaList(List<Cambiopersona> cambiopersonaList) {
        this.cambiopersonaList = cambiopersonaList;
    }
    
}
