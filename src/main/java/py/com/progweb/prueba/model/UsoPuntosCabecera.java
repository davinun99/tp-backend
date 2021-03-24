package py.com.progweb.prueba.model;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="agenda")
public class UsoPuntosCabecera {
    @Id
    @Column(name = "id_cabecera")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntosCabeceraSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="usoPuntosCabeceraSeq", sequenceName = "uso_puntos_cabecera_id_cabecera_seq", allocationSize = 0)
	private Integer idCliente;
    
    @Column(name = "id_concepto")
    @Basic(optional = false)
	private Integer idConcepto;
	
    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional=false)
    private Cliente cliente;

    @JoinColumn(name = "id_concepto", referencedColumnName = "id_concepto")
    @ManyToOne(optional=false)
    private ConceptoPuntos conceptoPuntos;

    public UsoPuntosCabecera(){

    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setConceptoPuntos(ConceptoPuntos conceptoPuntos) {
        this.conceptoPuntos = conceptoPuntos;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }
    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public ConceptoPuntos getConceptoPuntos() {
        return conceptoPuntos;
    }
    public Date getFecha() {
        return fecha;
    }
    public Integer getIdCliente() {
        return idCliente;
    }
    public Integer getIdConcepto() {
        return idConcepto;
    }
    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }
}