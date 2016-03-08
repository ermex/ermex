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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "ctl_copias", schema = "public")
@NamedQueries({
    @NamedQuery(name = "CtlCopias.findAll", query = "SELECT c FROM CtlCopias c"),
    @NamedQuery(name = "CtlCopias.findByOriginal", query = "SELECT c FROM CtlCopias c WHERE c.original = :original"),
    @NamedQuery(name = "CtlCopias.findByCopia", query = "SELECT c FROM CtlCopias c WHERE c.copia = :copia"),
    @NamedQuery(name = "CtlCopias.findByOriginalAnt", query = "SELECT c FROM CtlCopias c WHERE c.originalAnt = :originalAnt"),
    @NamedQuery(name = "CtlCopias.findByIdctlcopia", query = "SELECT c FROM CtlCopias c WHERE c.idctlcopia = :idctlcopia"),
    @NamedQuery(name = "CtlCopias.findByStatus", query = "SELECT c FROM CtlCopias c WHERE c.status = :status")})
public class CtlCopias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "original")
    private String original;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65)
    @Column(name = "copia")
    private String copia;
    @Size(max = 45)
    @Column(name = "original_ant")
    private String originalAnt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idctlcopia")
    private Integer idctlcopia;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "idnota", referencedColumnName = "idnota")
    @ManyToOne
    private Notas idnota;

    public CtlCopias() {
    }

    public CtlCopias(Integer idctlcopia) {
        this.idctlcopia = idctlcopia;
    }

    public CtlCopias(Integer idctlcopia, String copia) {
        this.idctlcopia = idctlcopia;
        this.copia = copia;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getCopia() {
        return copia;
    }

    public void setCopia(String copia) {
        this.copia = copia;
    }

    public String getOriginalAnt() {
        return originalAnt;
    }

    public void setOriginalAnt(String originalAnt) {
        this.originalAnt = originalAnt;
    }

    public Integer getIdctlcopia() {
        return idctlcopia;
    }

    public void setIdctlcopia(Integer idctlcopia) {
        this.idctlcopia = idctlcopia;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Notas getIdnota() {
        return idnota;
    }

    public void setIdnota(Notas idnota) {
        this.idnota = idnota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idctlcopia != null ? idctlcopia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtlCopias)) {
            return false;
        }
        CtlCopias other = (CtlCopias) object;
        if ((this.idctlcopia == null && other.idctlcopia != null) || (this.idctlcopia != null && !this.idctlcopia.equals(other.idctlcopia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.CtlCopias[ idctlcopia=" + idctlcopia + " ]";
    }
    
}
