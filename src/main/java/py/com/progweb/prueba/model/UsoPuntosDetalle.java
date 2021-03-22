package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "uso_puntos_detalle")
public class UsoPuntosDetalle {

    @Id
    @Column(name="id_detalle")
    @Basic(optional = false)
    private Long id;

    @Column(name="puntaje_utilizado")
    @Basic(optional = false)
    private  Integer puntajeUtilizado;

    @Column(name="id_cabecera")
    @Basic(optional = false)
    private Long idCabecera;

    @ManyToOne()
    @JoinColumn(name = "id_bolsa")
    @Basic(optional = false)
    private BolsaPuntos idBolsa;

    public UsoPuntosDetalle(){

    }
    public UsoPuntosDetalle(Long id, Integer puntajeUtilizado, Long idCabecera, BolsaPuntos idBolsa) {
        this.id = id;
        this.puntajeUtilizado = puntajeUtilizado;
        this.idCabecera = idCabecera;
        this.idBolsa = idBolsa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Long getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(Long idCabecera) {
        this.idCabecera = idCabecera;
    }

    public BolsaPuntos getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(BolsaPuntos idBolsa) {
        this.idBolsa = idBolsa;
    }


}
