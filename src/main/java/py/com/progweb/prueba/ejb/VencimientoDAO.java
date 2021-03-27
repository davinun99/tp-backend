package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Stateless
public class VencimientoDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;

    public void add(VencimientoPuntos vencimientoPuntos){
        em.persist(vencimientoPuntos);
    }
    public Integer getDuracion(String fecha){
        java.util.Date fechaDate= java.sql.Date.valueOf(fecha);
        Query q = this.em.createQuery("select v.duracion from VencimientoPuntos v where :fechaDate between v.fechaInicio and v.fechaFin");
        return  (Integer) q.setParameter("fechaDate", fechaDate).getSingleResult();
    }
}
