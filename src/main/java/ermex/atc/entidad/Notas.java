/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "notas", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n"),
    @NamedQuery(name = "Notas.findByIdnota", query = "SELECT n FROM Notas n WHERE n.idnota = :idnota"),
    @NamedQuery(name = "Notas.findByNonota", query = "SELECT n FROM Notas n WHERE n.nonota = :nonota"),
    @NamedQuery(name = "Notas.findByNombre", query = "SELECT n FROM Notas n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "Notas.findByNoimagen", query = "SELECT n FROM Notas n WHERE n.noimagen = :noimagen"),
    @NamedQuery(name = "Notas.findByNooficio", query = "SELECT n FROM Notas n WHERE n.nooficio = :nooficio"),
    @NamedQuery(name = "Notas.findByFechacreacion", query = "SELECT n FROM Notas n WHERE n.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Notas.findByObservaciones", query = "SELECT n FROM Notas n WHERE n.observaciones = :observaciones"),
    @NamedQuery(name = "Notas.findByStatus", query = "SELECT n FROM Notas n WHERE n.status = :status"),
    @NamedQuery(name = "Notas.findByFechaoficio", query = "SELECT n FROM Notas n WHERE n.fechaoficio = :fechaoficio"),
    @NamedQuery(name = "Notas.findByFechaacuse", query = "SELECT n FROM Notas n WHERE n.fechaacuse = :fechaacuse"),
    @NamedQuery(name = "Notas.findByReposiciones", query = "SELECT n FROM Notas n WHERE n.reposiciones = :reposiciones"),
    @NamedQuery(name = "Notas.findByDispotivioentrega", query = "SELECT n FROM Notas n WHERE n.dispotivioentrega = :dispotivioentrega"),
    @NamedQuery(name = "Notas.findByIdorganismo", query = "SELECT n FROM Notas n WHERE n.idorganismo = :idorganismo"),
    @NamedQuery(name = "Notas.findByPwdnota", query = "SELECT n FROM Notas n WHERE n.pwdnota = :pwdnota")})
public class Notas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "idnota")
    private String idnota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nonota")
    private int nonota;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "noimagen")
    private Integer noimagen;
    @Size(max = 25)
    @Column(name = "nooficio")
    private String nooficio;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Column(name = "fechaoficio")
    @Temporal(TemporalType.DATE)
    private Date fechaoficio;
    @Column(name = "fechaacuse")
    @Temporal(TemporalType.DATE)
    private Date fechaacuse;
    @Column(name = "reposiciones")
    private Integer reposiciones;
    @Size(max = 2147483647)
    @Column(name = "dispotivioentrega")
    private String dispotivioentrega;
    @Column(name = "idorganismo")
    private BigInteger idorganismo;
    @Size(max = 9)
    @Column(name = "pwdnota")
    private String pwdnota;
    @JoinColumn(name = "idcontrolsolicitud", referencedColumnName = "idcontrolsolicitud")
    @ManyToOne
    private Controlsolicitudes idcontrolsolicitud;
    @OneToMany(mappedBy = "idnota")
    private List<CtlCopias> ctlCopiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnota")
    private List<Documentosnotas> documentosnotasList;

    public Notas() {
    }

    public Notas(String idnota) {
        this.idnota = idnota;
    }

    public Notas(String idnota, int nonota, int status) {
        this.idnota = idnota;
        this.nonota = nonota;
        this.status = status;
    }

    public String getIdnota() {
        return idnota;
    }

    public void setIdnota(String idnota) {
        this.idnota = idnota;
    }

    public int getNonota() {
        return nonota;
    }

    public void setNonota(int nonota) {
        this.nonota = nonota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNoimagen() {
        return noimagen;
    }

    public void setNoimagen(Integer noimagen) {
        this.noimagen = noimagen;
    }

    public String getNooficio() {
        return nooficio;
    }

    public void setNooficio(String nooficio) {
        this.nooficio = nooficio;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getFechaoficio() {
        return fechaoficio;
    }

    public void setFechaoficio(Date fechaoficio) {
        this.fechaoficio = fechaoficio;
    }

    public Date getFechaacuse() {
        return fechaacuse;
    }

    public void setFechaacuse(Date fechaacuse) {
        this.fechaacuse = fechaacuse;
    }

    public Integer getReposiciones() {
        return reposiciones;
    }

    public void setReposiciones(Integer reposiciones) {
        this.reposiciones = reposiciones;
    }

    public String getDispotivioentrega() {
        return dispotivioentrega;
    }

    public void setDispotivioentrega(String dispotivioentrega) {
        this.dispotivioentrega = dispotivioentrega;
    }

    public BigInteger getIdorganismo() {
        return idorganismo;
    }

    public void setIdorganismo(BigInteger idorganismo) {
        this.idorganismo = idorganismo;
    }

    public String getPwdnota() {
        return pwdnota;
    }

    public void setPwdnota(String pwdnota) {
        this.pwdnota = pwdnota;
    }

    public Controlsolicitudes getIdcontrolsolicitud() {
        return idcontrolsolicitud;
    }

    public void setIdcontrolsolicitud(Controlsolicitudes idcontrolsolicitud) {
        this.idcontrolsolicitud = idcontrolsolicitud;
    }

    public List<CtlCopias> getCtlCopiasList() {
        return ctlCopiasList;
    }

    public void setCtlCopiasList(List<CtlCopias> ctlCopiasList) {
        this.ctlCopiasList = ctlCopiasList;
    }

    public List<Documentosnotas> getDocumentosnotasList() {
        return documentosnotasList;
    }

    public void setDocumentosnotasList(List<Documentosnotas> documentosnotasList) {
        this.documentosnotasList = documentosnotasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnota != null ? idnota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.idnota == null && other.idnota != null) || (this.idnota != null && !this.idnota.equals(other.idnota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Notas[ idnota=" + idnota + " ]";
    }
    
}
