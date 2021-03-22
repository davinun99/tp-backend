package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bolsa_puntos")
public class BolsaPuntos {
    @Id
    @Column(name = "id_bolsa")
    @Basic(optional = false)
    private Long id;

    @Column(name = "fecha_asignacion")
    @Basic(optional = false)
    private Date fechaAsignacion;

    @Column(name = "fecha_caducidad")
    @Basic(optional = false)
    private Date fechaCaducidad;

    @Column(name = "puntajes_asignado")
    @Basic(optional = false)
    private  Integer puntajesAsignado;

    @Column(name = "puntajes_utilizado")
    @Basic(optional = false)
    private  Integer puntajeUtilizado;

    @Column(name = "saldo_puntos")
    @Basic(optional = false)
    private  Integer saldoPuntos;

    @Column(name = "monto")
    @Basic(optional = false)
    private Integer montoOperacion;

    @Column(name="id_cliente")
    @Basic(optional = false)
    private  Long idCliente;


    public  BolsaPuntos(){

    }

    public BolsaPuntos(Long id, Date fechaAsignacion, Date fechaCaducidad, Integer puntajesAsignado, Integer puntajeUtilizado, Integer saldoPuntos, Integer montoOperacion, Long idCliente) {
        this.id = id;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaCaducidad = fechaCaducidad;
        this.puntajesAsignado = puntajesAsignado;
        this.puntajeUtilizado = puntajeUtilizado;
        this.saldoPuntos = saldoPuntos;
        this.montoOperacion = montoOperacion;
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getPuntajesAsignado() {
        return puntajesAsignado;
    }

    public void setPuntajesAsignado(Integer puntajesAsignado) {
        this.puntajesAsignado = puntajesAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }
}
