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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author FABY
 */
@Entity
@Table(name = "personas", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p ORDER BY p.idpersona"),
    @NamedQuery(name = "Personas.findByIdpersona", query = "SELECT p FROM Personas p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "Personas.findByTipo", query = "SELECT p FROM Personas p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Personas.findByCurp", query = "SELECT p FROM Personas p WHERE p.curp = :curp"),
    @NamedQuery(name = "Personas.findByNombre", query = "SELECT p FROM Personas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personas.findByApellidop", query = "SELECT p FROM Personas p WHERE p.apellidop = :apellidop"),
    @NamedQuery(name = "Personas.findByApellidom", query = "SELECT p FROM Personas p WHERE p.apellidom = :apellidom"),
    @NamedQuery(name = "Personas.findByGrado", query = "SELECT p FROM Personas p WHERE p.grado = :grado"),
    @NamedQuery(name = "Personas.findByCargo", query = "SELECT p FROM Personas p WHERE p.cargo = :cargo"),
    @NamedQuery(name = "Personas.findByCorreo1", query = "SELECT p FROM Personas p WHERE p.correo1 = :correo1"),
    @NamedQuery(name = "Personas.findByCorreo2", query = "SELECT p FROM Personas p WHERE p.correo2 = :correo2"),
    @NamedQuery(name = "Personas.findByCorreo3", query = "SELECT p FROM Personas p WHERE p.correo3 = :correo3"),
    @NamedQuery(name = "Personas.findByTelefono", query = "SELECT p FROM Personas p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personas.findByDomicilioOficina", query = "SELECT p FROM Personas p WHERE p.domicilioOficina = :domicilioOficina"),
    @NamedQuery(name = "Personas.findByTelpersonal", query = "SELECT p FROM Personas p WHERE p.telpersonal = :telpersonal"),
    @NamedQuery(name = "Personas.findByDompersonal", query = "SELECT p FROM Personas p WHERE p.dompersonal = :dompersonal"),
    @NamedQuery(name = "Personas.findByIdoficialanv", query = "SELECT p FROM Personas p WHERE p.idoficialanv = :idoficialanv"),
    @NamedQuery(name = "Personas.findByIdoficialrev", query = "SELECT p FROM Personas p WHERE p.idoficialrev = :idoficialrev"),
    @NamedQuery(name = "Personas.findByCredoficialanv", query = "SELECT p FROM Personas p WHERE p.credoficialanv = :credoficialanv"),
    @NamedQuery(name = "Personas.findByCredoficialrev", query = "SELECT p FROM Personas p WHERE p.credoficialrev = :credoficialrev"),
    @NamedQuery(name = "Personas.findByImgcurp", query = "SELECT p FROM Personas p WHERE p.imgcurp = :imgcurp"),
    @NamedQuery(name = "Personas.findByFoto", query = "SELECT p FROM Personas p WHERE p.foto = :foto"),
    @NamedQuery(name = "Personas.findByHuellapulgar", query = "SELECT p FROM Personas p WHERE p.huellapulgar = :huellapulgar"),
    @NamedQuery(name = "Personas.findByHuellamanoizq", query = "SELECT p FROM Personas p WHERE p.huellamanoizq = :huellamanoizq"),
    @NamedQuery(name = "Personas.findByHuellamanoder", query = "SELECT p FROM Personas p WHERE p.huellamanoder = :huellamanoder"),
    @NamedQuery(name = "Personas.findByNombramiento", query = "SELECT p FROM Personas p WHERE p.nombramiento = :nombramiento")})
public class Personas implements Serializable {

    @Lob
    @Column(name = "idoficialanv")
    private byte[] idoficialanv;
    @Lob
    @Column(name = "idoficialrev")
    private byte[] idoficialrev;
    @Lob
    @Column(name = "credoficialanv")
    private byte[] credoficialanv;
    @Lob
    @Column(name = "credoficialrev")
    private byte[] credoficialrev;
    @Lob
    @Column(name = "imgcurp")
    private byte[] imgcurp;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Lob
    @Column(name = "huellapulgar")
    private byte[] huellapulgar;
    @Lob
    @Column(name = "huellamanoizq")
    private byte[] huellamanoizq;
    @Lob
    @Column(name = "huellamanoder")
    private byte[] huellamanoder;
    @Lob
    @Column(name = "nombramiento")
    private byte[] nombramiento;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersona")
    private Long idpersona;
    @Size(max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Pattern(regexp="(^[A-Z]{1}[AEIOUX]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$)|^\\s*$")
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    @Size(max = 2147483647)
    @Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð .-]+$")
    @Column(name = "nombre")
    private String nombre;
    @Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð .-]+$")
    @Size(max = 2147483647)
    @Column(name = "apellidop")
    private String apellidop;
    @Pattern(regexp="(^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð .-]+$)|^\\s*$")
    @Size(max = 2147483647)
    @Column(name = "apellidom")
    private String apellidom;
    @Size(max = 2147483647)
    @Column(name = "grado")
    private String grado;
    @Size(max = 2147483647)
    @Column(name = "cargo")
    private String cargo;
    @Pattern(regexp="^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Size(max = 2147483647)
    @Column(name = "correo1")
    private String correo1;
    @Pattern(regexp="(^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)|^\\s*$")
    @Size(max = 2147483647)
    @Column(name = "correo2")
    private String correo2;
    @Pattern(regexp="(^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)|^\\s*$")
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
    @JoinColumn(name = "idinstitucion", referencedColumnName = "idinstitucion")
    @ManyToOne
    private Instituciones idinstitucion;
    @OneToMany(mappedBy = "idpersona")
    private List<Gestores> gestoresList;
    @OneToMany(mappedBy = "designador")
    private List<Gestores> gestoresList1;

    public Personas() {
    }

    public Personas(Long idpersona) {
        this.idpersona = idpersona;
    }

    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
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


    public Instituciones getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Instituciones idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public List<Gestores> getGestoresList() {
        return gestoresList;
    }

    public void setGestoresList(List<Gestores> gestoresList) {
        this.gestoresList = gestoresList;
    }

    public List<Gestores> getGestoresList1() {
        return gestoresList1;
    }

    public void setGestoresList1(List<Gestores> gestoresList1) {
        this.gestoresList1 = gestoresList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.Personas[ idpersona=" + idpersona + " ]";
    }

    public byte[] getIdoficialanv() {
        return idoficialanv;
    }

    public void setIdoficialanv(byte[] idoficialanv) {
        this.idoficialanv = idoficialanv;
    }

    public byte[] getIdoficialrev() {
        return idoficialrev;
    }

    public void setIdoficialrev(byte[] idoficialrev) {
        this.idoficialrev = idoficialrev;
    }

    public byte[] getCredoficialanv() {
        return credoficialanv;
    }

    public void setCredoficialanv(byte[] credoficialanv) {
        this.credoficialanv = credoficialanv;
    }

    public byte[] getCredoficialrev() {
        return credoficialrev;
    }

    public void setCredoficialrev(byte[] credoficialrev) {
        this.credoficialrev = credoficialrev;
    }

    public byte[] getImgcurp() {
        return imgcurp;
    }

    public void setImgcurp(byte[] imgcurp) {
        this.imgcurp = imgcurp;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getHuellapulgar() {
        return huellapulgar;
    }

    public void setHuellapulgar(byte[] huellapulgar) {
        this.huellapulgar = huellapulgar;
    }

    public byte[] getHuellamanoizq() {
        return huellamanoizq;
    }

    public void setHuellamanoizq(byte[] huellamanoizq) {
        this.huellamanoizq = huellamanoizq;
    }

    public byte[] getHuellamanoder() {
        return huellamanoder;
    }

    public void setHuellamanoder(byte[] huellamanoder) {
        this.huellamanoder = huellamanoder;
    }

    public byte[] getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(byte[] nombramiento) {
        this.nombramiento = nombramiento;
    }
    
}
