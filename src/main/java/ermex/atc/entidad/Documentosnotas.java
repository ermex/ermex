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
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "documentosnotas", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Documentosnotas.findAll", query = "SELECT d FROM Documentosnotas d"),
    @NamedQuery(name = "Documentosnotas.findByIddocumentonota", query = "SELECT d FROM Documentosnotas d WHERE d.iddocumentonota = :iddocumentonota"),
    @NamedQuery(name = "Documentosnotas.findByNotaword", query = "SELECT d FROM Documentosnotas d WHERE d.notaword = :notaword"),
    @NamedQuery(name = "Documentosnotas.findByNotapdf", query = "SELECT d FROM Documentosnotas d WHERE d.notapdf = :notapdf"),
    @NamedQuery(name = "Documentosnotas.findByRelacionimg", query = "SELECT d FROM Documentosnotas d WHERE d.relacionimg = :relacionimg"),
    @NamedQuery(name = "Documentosnotas.findByAcuse", query = "SELECT d FROM Documentosnotas d WHERE d.acuse = :acuse")})
public class Documentosnotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddocumentonota")
    private Integer iddocumentonota;
    @Size(max = 2147483647)
    @Column(name = "notaword")
    private String notaword;
    @Size(max = 2147483647)
    @Column(name = "notapdf")
    private String notapdf;
    @Size(max = 2147483647)
    @Column(name = "relacionimg")
    private String relacionimg;
    @Size(max = 2147483647)
    @Column(name = "acuse")
    private String acuse;
    @JoinColumn(name = "idnota", referencedColumnName = "idnota")
    @ManyToOne(optional = false)
    private Notas idnota;

    public Documentosnotas() {
    }

    public Documentosnotas(Integer iddocumentonota) {
        this.iddocumentonota = iddocumentonota;
    }

    public Integer getIddocumentonota() {
        return iddocumentonota;
    }

    public void setIddocumentonota(Integer iddocumentonota) {
        this.iddocumentonota = iddocumentonota;
    }

    public String getNotaword() {
        return notaword;
    }

    public void setNotaword(String notaword) {
        this.notaword = notaword;
    }

    public String getNotapdf() {
        return notapdf;
    }

    public void setNotapdf(String notapdf) {
        this.notapdf = notapdf;
    }

    public String getRelacionimg() {
        return relacionimg;
    }

    public void setRelacionimg(String relacionimg) {
        this.relacionimg = relacionimg;
    }

    public String getAcuse() {
        return acuse;
    }

    public void setAcuse(String acuse) {
        this.acuse = acuse;
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
        hash += (iddocumentonota != null ? iddocumentonota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentosnotas)) {
            return false;
        }
        Documentosnotas other = (Documentosnotas) object;
        if ((this.iddocumentonota == null && other.iddocumentonota != null) || (this.iddocumentonota != null && !this.iddocumentonota.equals(other.iddocumentonota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iddocumentonota.toString();
    }
    
}
