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
@Table(name = "nom_proyectos", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "NomProyectos.findAll", query = "SELECT n FROM NomProyectos n"),
    @NamedQuery(name = "NomProyectos.findByNumero", query = "SELECT n FROM NomProyectos n WHERE n.numero = :numero"),
    @NamedQuery(name = "NomProyectos.findByNombre", query = "SELECT n FROM NomProyectos n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "NomProyectos.findByFechaInicial", query = "SELECT n FROM NomProyectos n WHERE n.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "NomProyectos.findByFechaFinal", query = "SELECT n FROM NomProyectos n WHERE n.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "NomProyectos.findByNubosidad", query = "SELECT n FROM NomProyectos n WHERE n.nubosidad = :nubosidad"),
    @NamedQuery(name = "NomProyectos.findByModos", query = "SELECT n FROM NomProyectos n WHERE n.modos = :modos"),
    @NamedQuery(name = "NomProyectos.findBySatelites", query = "SELECT n FROM NomProyectos n WHERE n.satelites = :satelites"),
    @NamedQuery(name = "NomProyectos.findByNivel", query = "SELECT n FROM NomProyectos n WHERE n.nivel = :nivel"),
    @NamedQuery(name = "NomProyectos.findBySolicitudes", query = "SELECT n FROM NomProyectos n WHERE n.solicitudes = :solicitudes"),
    @NamedQuery(name = "NomProyectos.findByNotas", query = "SELECT n FROM NomProyectos n WHERE n.notas = :notas"),
    @NamedQuery(name = "NomProyectos.findByProgramacion", query = "SELECT n FROM NomProyectos n WHERE n.programacion = :programacion")})
public class NomProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Size(max = 25)
    @Column(name = "nubosidad")
    private String nubosidad;
    @Size(max = 25)
    @Column(name = "modos")
    private String modos;
    @Size(max = 15)
    @Column(name = "satelites")
    private String satelites;
    @Size(max = 3)
    @Column(name = "nivel")
    private String nivel;
    @Size(max = 100)
    @Column(name = "solicitudes")
    private String solicitudes;
    @Size(max = 30)
    @Column(name = "notas")
    private String notas;
    @Size(max = 10)
    @Column(name = "programacion")
    private String programacion;
    @OneToMany(mappedBy = "proyecto")
    private List<Controlsolicitudes> controlsolicitudesList;

    public NomProyectos() {
    }

    public NomProyectos(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNubosidad() {
        return nubosidad;
    }

    public void setNubosidad(String nubosidad) {
        this.nubosidad = nubosidad;
    }

    public String getModos() {
        return modos;
    }

    public void setModos(String modos) {
        this.modos = modos;
    }

    public String getSatelites() {
        return satelites;
    }

    public void setSatelites(String satelites) {
        this.satelites = satelites;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(String solicitudes) {
        this.solicitudes = solicitudes;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
    }

    public List<Controlsolicitudes> getControlsolicitudesList() {
        return controlsolicitudesList;
    }

    public void setControlsolicitudesList(List<Controlsolicitudes> controlsolicitudesList) {
        this.controlsolicitudesList = controlsolicitudesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomProyectos)) {
            return false;
        }
        NomProyectos other = (NomProyectos) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero.toString();
    }
    
}
