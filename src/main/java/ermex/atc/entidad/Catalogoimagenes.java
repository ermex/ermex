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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "catalogoimagenes", catalog = "gestor", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogoimagenes.findAll", query = "SELECT c FROM Catalogoimagenes c"),
    @NamedQuery(name = "Catalogoimagenes.findByIdcatalogoimagen", query = "SELECT c FROM Catalogoimagenes c WHERE c.idcatalogoimg = :idcatalogoimg"),
    @NamedQuery(name = "Catalogoimagenes.findBySatelite", query = "SELECT c FROM Catalogoimagenes c WHERE c.satelite = :satelite"),
    @NamedQuery(name = "Catalogoimagenes.findByModo", query = "SELECT c FROM Catalogoimagenes c WHERE c.modo = :modo"),
    @NamedQuery(name = "Catalogoimagenes.findByNivel", query = "SELECT c FROM Catalogoimagenes c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Catalogoimagenes.findByCharacter", query = "SELECT c FROM Catalogoimagenes c WHERE c.character = :character")})
public class Catalogoimagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcatalogoimagen")
    private Integer idcatalogoimg;
    @Column(name = "satelite")
    private Short satelite;
    @Size(max = 5)
    @Column(name = "tipo")
    private String modo;
    @Size(max = 3)
    @Column(name = "nivel")
    private String nivel;
    @Size(max = 15)
    @Column(name = "status")
    private String character;

    public Catalogoimagenes() {
    }

    public Catalogoimagenes(Integer idcatalogoimg) {
        this.idcatalogoimg = idcatalogoimg;
    }

    public Integer getIdcatalogoimg() {
        return idcatalogoimg;
    }

    public void setIdcatalogoimg(Integer idcatalogoimg) {
        this.idcatalogoimg = idcatalogoimg;
    }

    public Short getSatelite() {
        return satelite;
    }

    public void setSatelite(Short satelite) {
        this.satelite = satelite;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatalogoimg != null ? idcatalogoimg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogoimagenes)) {
            return false;
        }
        Catalogoimagenes other = (Catalogoimagenes) object;
        if ((this.idcatalogoimg == null && other.idcatalogoimg != null) || (this.idcatalogoimg != null && !this.idcatalogoimg.equals(other.idcatalogoimg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Catalogoimagenes[ idcatalogoimg=" + idcatalogoimg + " ]";
    }
    
}
