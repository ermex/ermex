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
@Table(name = "organismos", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Organismos.findAll", query = "SELECT o FROM Organismos o"),
    @NamedQuery(name = "Organismos.findByIdorganismo", query = "SELECT o FROM Organismos o WHERE o.idorganismo = :idorganismo"),
    @NamedQuery(name = "Organismos.findByNombre", query = "SELECT o FROM Organismos o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Organismos.findByDependencia", query = "SELECT o FROM Organismos o where o.iddependencia.iddependencia = :iddependencia")})
public class Organismos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorganismo")
    private Long idorganismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idorganismo")
    private List<Instituciones> institucionesList;
    @JoinColumn(name = "iddependencia", referencedColumnName = "iddependencia")
    @ManyToOne
    private Dependencias iddependencia;

    public Organismos() {
    }

    public Organismos(Long idorganismo) {
        this.idorganismo = idorganismo;
    }

    public Organismos(Long idorganismo, String nombre) {
        this.idorganismo = idorganismo;
        this.nombre = nombre;
    }

    public Long getIdorganismo() {
        return idorganismo;
    }

    public void setIdorganismo(Long idorganismo) {
        this.idorganismo = idorganismo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Instituciones> getInstitucionesList() {
        return institucionesList;
    }

    public void setInstitucionesList(List<Instituciones> institucionesList) {
        this.institucionesList = institucionesList;
    }

    public Dependencias getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(Dependencias iddependencia) {
        this.iddependencia = iddependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorganismo != null ? idorganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organismos)) {
            return false;
        }
        Organismos other = (Organismos) object;
        if ((this.idorganismo == null && other.idorganismo != null) || (this.idorganismo != null && !this.idorganismo.equals(other.idorganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Organismos[ idorganismo=" + idorganismo + " ]";
    }
    
}
