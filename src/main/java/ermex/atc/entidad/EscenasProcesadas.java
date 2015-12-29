/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "escenas_procesadas", catalog = "gestor", schema = "public")
@NamedQueries({
    @NamedQuery(name = "EscenasProcesadas.findAll", query = "SELECT e FROM EscenasProcesadas e"),
    @NamedQuery(name = "EscenasProcesadas.findByK", query = "SELECT e FROM EscenasProcesadas e WHERE e.k = :k"),
    @NamedQuery(name = "EscenasProcesadas.findByJ", query = "SELECT e FROM EscenasProcesadas e WHERE e.j = :j"),
    @NamedQuery(name = "EscenasProcesadas.findByModo", query = "SELECT e FROM EscenasProcesadas e WHERE e.modo = :modo"),
    @NamedQuery(name = "EscenasProcesadas.findBySatelite", query = "SELECT e FROM EscenasProcesadas e WHERE e.satelite = :satelite"),
    @NamedQuery(name = "EscenasProcesadas.findByInstrumento", query = "SELECT e FROM EscenasProcesadas e WHERE e.instrumento = :instrumento"),
    @NamedQuery(name = "EscenasProcesadas.findByFechaK", query = "SELECT e FROM EscenasProcesadas e WHERE e.fechaK = :fechaK"),
    @NamedQuery(name = "EscenasProcesadas.findByFecha", query = "SELECT e FROM EscenasProcesadas e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EscenasProcesadas.findByIdentificador", query = "SELECT e FROM EscenasProcesadas e WHERE e.identificador = :identificador"),
    @NamedQuery(name = "EscenasProcesadas.findByIdrespa", query = "SELECT e FROM EscenasProcesadas e WHERE e.idrespa = :idrespa"),
    @NamedQuery(name = "EscenasProcesadas.findByFechap", query = "SELECT e FROM EscenasProcesadas e WHERE e.fechap = :fechap"),
    @NamedQuery(name = "EscenasProcesadas.findByShift", query = "SELECT e FROM EscenasProcesadas e WHERE e.shift = :shift"),
    @NamedQuery(name = "EscenasProcesadas.findByTipo", query = "SELECT e FROM EscenasProcesadas e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "EscenasProcesadas.findByNivel", query = "SELECT e FROM EscenasProcesadas e WHERE e.nivel = :nivel"),
    @NamedQuery(name = "EscenasProcesadas.findByFormato", query = "SELECT e FROM EscenasProcesadas e WHERE e.formato = :formato"),
    @NamedQuery(name = "EscenasProcesadas.findByBytes", query = "SELECT e FROM EscenasProcesadas e WHERE e.bytes = :bytes"),
    @NamedQuery(name = "EscenasProcesadas.findBySum1", query = "SELECT e FROM EscenasProcesadas e WHERE e.sum1 = :sum1"),
    @NamedQuery(name = "EscenasProcesadas.findBySum2", query = "SELECT e FROM EscenasProcesadas e WHERE e.sum2 = :sum2"),
    @NamedQuery(name = "EscenasProcesadas.findByFechaRegistro", query = "SELECT e FROM EscenasProcesadas e WHERE e.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "EscenasProcesadas.findByStatus", query = "SELECT e FROM EscenasProcesadas e WHERE e.status = :status"),
    @NamedQuery(name = "EscenasProcesadas.findByIdentificadorAnt", query = "SELECT e FROM EscenasProcesadas e WHERE e.identificadorAnt = :identificadorAnt"),
    @NamedQuery(name = "EscenasProcesadas.findByInterpolacion", query = "SELECT e FROM EscenasProcesadas e WHERE e.interpolacion = :interpolacion"),
    @NamedQuery(name = "EscenasProcesadas.findByRemuestreo", query = "SELECT e FROM EscenasProcesadas e WHERE e.remuestreo = :remuestreo"),
    @NamedQuery(name = "EscenasProcesadas.findByAnguloInc", query = "SELECT e FROM EscenasProcesadas e WHERE e.anguloInc = :anguloInc"),
    @NamedQuery(name = "EscenasProcesadas.findByBandas", query = "SELECT e FROM EscenasProcesadas e WHERE e.bandas = :bandas"),
    @NamedQuery(name = "EscenasProcesadas.findByBits", query = "SELECT e FROM EscenasProcesadas e WHERE e.bits = :bits"),
    @NamedQuery(name = "EscenasProcesadas.findByTamanio", query = "SELECT e FROM EscenasProcesadas e WHERE e.tamanio = :tamanio"),
    @NamedQuery(name = "EscenasProcesadas.findByCoordenadas", query = "SELECT e FROM EscenasProcesadas e WHERE e.coordenadas = :coordenadas"),
    @NamedQuery(name = "EscenasProcesadas.findByZonaUtm", query = "SELECT e FROM EscenasProcesadas e WHERE e.zonaUtm = :zonaUtm")})
public class EscenasProcesadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "k")
    private short k;
    @Basic(optional = false)
    @NotNull
    @Column(name = "j")
    private short j;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "modo")
    private String modo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "satelite")
    private short satelite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "instrumento")
    private short instrumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_k")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "identificador")
    private String identificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idrespa")
    private String idrespa;
    @Column(name = "fechap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechap;
    @Column(name = "shift")
    private Short shift;
    @Size(max = 3)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 3)
    @Column(name = "nivel")
    private String nivel;
    @Size(max = 10)
    @Column(name = "formato")
    private String formato;
    @Column(name = "bytes")
    private Long bytes;
    @Column(name = "sum1")
    private BigInteger sum1;
    @Column(name = "sum2")
    private BigInteger sum2;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Size(max = 3)
    @Column(name = "status")
    private String status;
    @Size(max = 50)
    @Column(name = "identificador_ant")
    private String identificadorAnt;
    @Size(max = 1)
    @Column(name = "interpolacion")
    private String interpolacion;
    @Size(max = 1)
    @Column(name = "remuestreo")
    private String remuestreo;
    @Column(name = "angulo_inc")
    private Integer anguloInc;
    @Column(name = "bandas")
    private Integer bandas;
    @Column(name = "bits")
    private Integer bits;
    @Column(name = "tamanio")
    private Integer tamanio;
    @Size(max = 1)
    @Column(name = "coordenadas")
    private String coordenadas;
    @Column(name = "zona_utm")
    private Integer zonaUtm;

    public EscenasProcesadas() {
    }

    public EscenasProcesadas(String identificador) {
        this.identificador = identificador;
    }

    public EscenasProcesadas(String identificador, short k, short j, String modo, short satelite, short instrumento, Date fechaK, Date fecha, String idrespa) {
        this.identificador = identificador;
        this.k = k;
        this.j = j;
        this.modo = modo;
        this.satelite = satelite;
        this.instrumento = instrumento;
        this.fechaK = fechaK;
        this.fecha = fecha;
        this.idrespa = idrespa;
    }

    public short getK() {
        return k;
    }

    public void setK(short k) {
        this.k = k;
    }

    public short getJ() {
        return j;
    }

    public void setJ(short j) {
        this.j = j;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public short getSatelite() {
        return satelite;
    }

    public void setSatelite(short satelite) {
        this.satelite = satelite;
    }

    public short getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(short instrumento) {
        this.instrumento = instrumento;
    }

    public Date getFechaK() {
        return fechaK;
    }

    public void setFechaK(Date fechaK) {
        this.fechaK = fechaK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIdrespa() {
        return idrespa;
    }

    public void setIdrespa(String idrespa) {
        this.idrespa = idrespa;
    }

    public Date getFechap() {
        return fechap;
    }

    public void setFechap(Date fechap) {
        this.fechap = fechap;
    }

    public Short getShift() {
        return shift;
    }

    public void setShift(Short shift) {
        this.shift = shift;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    public BigInteger getSum1() {
        return sum1;
    }

    public void setSum1(BigInteger sum1) {
        this.sum1 = sum1;
    }

    public BigInteger getSum2() {
        return sum2;
    }

    public void setSum2(BigInteger sum2) {
        this.sum2 = sum2;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentificadorAnt() {
        return identificadorAnt;
    }

    public void setIdentificadorAnt(String identificadorAnt) {
        this.identificadorAnt = identificadorAnt;
    }

    public String getInterpolacion() {
        return interpolacion;
    }

    public void setInterpolacion(String interpolacion) {
        this.interpolacion = interpolacion;
    }

    public String getRemuestreo() {
        return remuestreo;
    }

    public void setRemuestreo(String remuestreo) {
        this.remuestreo = remuestreo;
    }

    public Integer getAnguloInc() {
        return anguloInc;
    }

    public void setAnguloInc(Integer anguloInc) {
        this.anguloInc = anguloInc;
    }

    public Integer getBandas() {
        return bandas;
    }

    public void setBandas(Integer bandas) {
        this.bandas = bandas;
    }

    public Integer getBits() {
        return bits;
    }

    public void setBits(Integer bits) {
        this.bits = bits;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getZonaUtm() {
        return zonaUtm;
    }

    public void setZonaUtm(Integer zonaUtm) {
        this.zonaUtm = zonaUtm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EscenasProcesadas)) {
            return false;
        }
        EscenasProcesadas other = (EscenasProcesadas) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ermex.atc.entidad.EscenasProcesadas[ identificador=" + identificador + " ]";
    }
    
}
