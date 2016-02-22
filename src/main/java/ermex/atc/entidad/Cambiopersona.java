/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "cambiopersona", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Cambiopersona.findAll", query = "SELECT c FROM Cambiopersona c"),
    @NamedQuery(name = "Cambiopersona.findByIdcp", query = "SELECT c FROM Cambiopersona c WHERE c.idcp = :idcp"),
    @NamedQuery(name = "Cambiopersona.findByOperation", query = "SELECT c FROM Cambiopersona c WHERE c.operation = :operation"),
    @NamedQuery(name = "Cambiopersona.findByFecha", query = "SELECT c FROM Cambiopersona c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cambiopersona.findByTipo", query = "SELECT c FROM Cambiopersona c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Cambiopersona.findByCurp", query = "SELECT c FROM Cambiopersona c WHERE c.curp = :curp"),
    @NamedQuery(name = "Cambiopersona.findByNombre", query = "SELECT c FROM Cambiopersona c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cambiopersona.findByApellidop", query = "SELECT c FROM Cambiopersona c WHERE c.apellidop = :apellidop"),
    @NamedQuery(name = "Cambiopersona.findByApellidom", query = "SELECT c FROM Cambiopersona c WHERE c.apellidom = :apellidom"),
    @NamedQuery(name = "Cambiopersona.findByGrado", query = "SELECT c FROM Cambiopersona c WHERE c.grado = :grado"),
    @NamedQuery(name = "Cambiopersona.findByCargo", query = "SELECT c FROM Cambiopersona c WHERE c.cargo = :cargo"),
    @NamedQuery(name = "Cambiopersona.findByCorreo1", query = "SELECT c FROM Cambiopersona c WHERE c.correo1 = :correo1"),
    @NamedQuery(name = "Cambiopersona.findByCorreo2", query = "SELECT c FROM Cambiopersona c WHERE c.correo2 = :correo2"),
    @NamedQuery(name = "Cambiopersona.findByCorreo3", query = "SELECT c FROM Cambiopersona c WHERE c.correo3 = :correo3"),
    @NamedQuery(name = "Cambiopersona.findByTelefono", query = "SELECT c FROM Cambiopersona c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cambiopersona.findByDomicilioOficina", query = "SELECT c FROM Cambiopersona c WHERE c.domicilioOficina = :domicilioOficina"),
    @NamedQuery(name = "Cambiopersona.findByTelpersonal", query = "SELECT c FROM Cambiopersona c WHERE c.telpersonal = :telpersonal"),
    @NamedQuery(name = "Cambiopersona.findByDompersonal", query = "SELECT c FROM Cambiopersona c WHERE c.dompersonal = :dompersonal")})
public class Cambiopersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcp")
    private Long idcp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "operation")
    private String operation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "apellidop")
    private String apellidop;
    @Size(max = 2147483647)
    @Column(name = "apellidom")
    private String apellidom;
    @Size(max = 2147483647)
    @Column(name = "grado")
    private String grado;
    @Size(max = 2147483647)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 2147483647)
    @Column(name = "correo1")
    private String correo1;
    @Size(max = 2147483647)
    @Column(name = "correo2")
    private String correo2;
    @Size(max = 2147483647)
    @Column(name = "correo3")
    private String correo3;
    @Size(max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 2147483647)
    @Column(name = "domicilio_oficina")
    private String domicilioOficina;
    @Size(max = 2147483647)
    @Column(name = "telpersonal")
    private String telpersonal;
    @Size(max = 2147483647)
    @Column(name = "dompersonal")
    private String dompersonal;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne
    private Personas idpersona;
    @JoinColumn(name = "idinstitucion", referencedColumnName = "idinstitucion")
    @ManyToOne
    private Instituciones idinstitucion;

    public Cambiopersona() {
    }

    public Cambiopersona(Long idcp) {
        this.idcp = idcp;
    }

    public Cambiopersona(Long idcp, String operation, Date fecha) {
        this.idcp = idcp;
        this.operation = operation;
        this.fecha = fecha;
    }

    public Long getIdcp() {
        return idcp;
    }

    public void setIdcp(Long idcp) {
        this.idcp = idcp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo1() {
        return correo1;
    }

    public void setCorreo1(String correo1) {
        this.correo1 = correo1;
    }

    public String getCorreo2() {
        return correo2;
    }

    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }

    public String getCorreo3() {
        return correo3;
    }

    public void setCorreo3(String correo3) {
        this.correo3 = correo3;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilioOficina() {
        return domicilioOficina;
    }

    public void setDomicilioOficina(String domicilioOficina) {
        this.domicilioOficina = domicilioOficina;
    }

    public String getTelpersonal() {
        return telpersonal;
    }

    public void setTelpersonal(String telpersonal) {
        this.telpersonal = telpersonal;
    }

    public String getDompersonal() {
        return dompersonal;
    }

    public void setDompersonal(String dompersonal) {
        this.dompersonal = dompersonal;
    }

    public Personas getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Personas idpersona) {
        this.idpersona = idpersona;
    }

    public Instituciones getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Instituciones idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcp != null ? idcp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cambiopersona)) {
            return false;
        }
        Cambiopersona other = (Cambiopersona) object;
        if ((this.idcp == null && other.idcp != null) || (this.idcp != null && !this.idcp.equals(other.idcp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Cambiopersona[ idcp=" + idcp + " ]";
    }
    
}
