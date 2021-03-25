package py.com.progweb.prueba.model;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="vencimiento_puntos")
public class VencimientoPuntos{
    @Id
    @Column(name = "id_vencimiento")
    @Basic(optional = false)
    @GeneratedValue(generator = "vencimientoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="vencimientoSec", sequenceName = "vencimiento_puntos_id_vencimiento_seq", allocationSize = 0)
    private Integer idVencimiento;

    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;    

    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;    

    @Column(name = "duracion")
    @Basic(optional = false)
    private Integer duracion;
    
    @JoinColumn(name = "id_asignacion", referencedColumnName = "id_asignacion")
    @ManyToOne(optional=false)
    private AsignacionPuntos asignacionPuntos;

    public VencimientoPuntos(){

    }
    public void setAsignacionPuntos(AsignacionPuntos asignacionPuntos) {
        this.asignacionPuntos = asignacionPuntos;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setIdVencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }
    public AsignacionPuntos getAsignacionPuntos() {
        return asignacionPuntos;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public Integer getIdVencimiento() {
        return idVencimiento;
    }
    
}