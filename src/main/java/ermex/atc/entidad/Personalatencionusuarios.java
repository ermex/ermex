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

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "personalatencionusuarios", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Personalatencionusuarios.findAll", query = "SELECT p FROM Personalatencionusuarios p"),
    @NamedQuery(name = "Personalatencionusuarios.findByNombre", query = "SELECT p FROM Personalatencionusuarios p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personalatencionusuarios.findByApellidopa", query = "SELECT p FROM Personalatencionusuarios p WHERE p.apellidopa = :apellidopa"),
    @NamedQuery(name = "Personalatencionusuarios.findByApellidoma", query = "SELECT p FROM Personalatencionusuarios p WHERE p.apellidoma = :apellidoma"),
    @NamedQuery(name = "Personalatencionusuarios.findByTelefono", query = "SELECT p FROM Personalatencionusuarios p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personalatencionusuarios.findByCorreo", query = "SELECT p FROM Personalatencionusuarios p WHERE p.correo = :correo"),
    @NamedQuery(name = "Personalatencionusuarios.findByPuesto", query = "SELECT p FROM Personalatencionusuarios p WHERE p.puesto = :puesto"),
    @NamedQuery(name = "Personalatencionusuarios.findByUsuario", query = "SELECT p FROM Personalatencionusuarios p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "Personalatencionusuarios.findByPwd", query = "SELECT p FROM Personalatencionusuarios p WHERE p.pwd = :pwd"),
    @NamedQuery(name = "Personalatencionusuarios.findByStatus", query = "SELECT p FROM Personalatencionusuarios p WHERE p.status = :status")})
public class Personalatencionusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
    @Column(name = "apellidopa")
    private String apellidopa;
    @Size(max = 30)
    @Column(name = "apellidoma")
    private String apellidoma;
    @Size(max = 12)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 30)
    @Column(name = "correo")
    private String correo;
    @Size(max = 50)
    @Column(name = "puesto")
    private String puesto;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 10)
    @Column(name = "pwd")
    private String pwd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(mappedBy = "idpersonalatencion")
    private List<Controlsolicitudes> controlsolicitudesList;

    public Personalatencionusuarios() {
    }

    public Personalatencionusuarios(String usuario) {
        this.usuario = usuario;
    }

    public Personalatencionusuarios(String usuario, int status) {
        this.usuario = usuario;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopa() {
        return apellidopa;
    }

    public void setApellidopa(String apellidopa) {
        this.apellidopa = apellidopa;
    }

    public String getApellidoma() {
        return apellidoma;
    }

    public void setApellidoma(String apellidoma) {
        this.apellidoma = apellidoma;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personalatencionusuarios)) {
            return false;
        }
        Personalatencionusuarios other = (Personalatencionusuarios) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Personalatencionusuarios[ usuario=" + usuario + " ]";
    }
    
}
