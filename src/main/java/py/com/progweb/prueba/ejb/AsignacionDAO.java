package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.AsignacionPuntos;

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
        Query q= this.em.createQuery("select monto / c.monto from AsignacionPuntos c where monto between limite_inferior and limite_superior");
        return  (Integer) q.getSingleResult();
    }
}
