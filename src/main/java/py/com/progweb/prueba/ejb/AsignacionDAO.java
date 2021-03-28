package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class AsignacionDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private  EntityManager em;
    // funcion para agregar una nueva regla de asignacion
    public void add(AsignacionPuntos asignacionPuntos){
        em.persist(asignacionPuntos);
    }
    //funcion para listar todas las reglas
    public List<AsignacionPuntos> getAll(){
        Query query = this.em.createQuery("select c from AsignacionPuntos c");
        return  (List<AsignacionPuntos>)  query.getResultList();
    }


    public Integer getReglaByMonto( Integer monto ){
        Query q= this.em.createQuery("select :monto / c.monto from AsignacionPuntos c where :monto between c.limiteInferior and c.limiteSuperior");
        return  (Integer) q.setParameter("monto", monto).getSingleResult();
    }

    public void deleteAsignacion(Long id_asignacion) {
        AsignacionPuntos asignacionPuntos= this.em.find(AsignacionPuntos.class,id_asignacion);
        if (asignacionPuntos!=null) {
            this.em.remove(asignacionPuntos);
        }
    }

    public void updateVencimiento(AsignacionPuntos asignacionPuntos) {
        AsignacionPuntos asignacionPuntosOriginal= this.em.find(AsignacionPuntos.class,asignacionPuntos.getIdAsignacion());
        if (asignacionPuntosOriginal!=null){
            if(asignacionPuntos.getLimiteInferior()!=null){
                asignacionPuntosOriginal.setLimiteInferior(asignacionPuntos.getLimiteInferior());
            }
            if(asignacionPuntos.getLimiteSuperior()!=null){
                asignacionPuntosOriginal.setLimiteSuperior(asignacionPuntos.getLimiteSuperior());
            }
            if(asignacionPuntos.getMonto()!=null){
                asignacionPuntosOriginal.setMonto(asignacionPuntos.getMonto());
            }
        }
    }
}
