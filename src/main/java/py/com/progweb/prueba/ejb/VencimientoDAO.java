package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class VencimientoDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;

    public void add(VencimientoPuntos vencimientoPuntos){
        em.persist(vencimientoPuntos);
    }
    public List<VencimientoPuntos> getAll(){
        Query q = this.em.createQuery("select v from VencimientoPuntos v");
        return  (List<VencimientoPuntos>) q.getResultList();
    }
    public Integer getDuracion(String fecha){
        java.util.Date fechaDate= java.sql.Date.valueOf(fecha);
        Query q = this.em.createQuery("select v.duracion from VencimientoPuntos v where :fechaDate between v.fechaInicio and v.fechaFin");
        return  (Integer) q.setParameter("fechaDate", fechaDate).getSingleResult();
    }

    public void deleteVencimiento(Integer id_vencimiento) {
        VencimientoPuntos vencimientoPuntos= this.em.find(VencimientoPuntos.class,id_vencimiento);
        if (vencimientoPuntos!=null){
            this.em.remove(vencimientoPuntos);
        }
    }

    public void updateVencimiento(VencimientoPuntos vencimientoPuntos) {
        VencimientoPuntos vencimientoPuntosOriginal= this.em.find(VencimientoPuntos.class,vencimientoPuntos.getIdVencimiento());
        if (vencimientoPuntosOriginal!=null){
            if(vencimientoPuntos.getFechaInicio()!=null){
                vencimientoPuntosOriginal.setFechaInicio(vencimientoPuntos.getFechaInicio());
            }
            if(vencimientoPuntos.getFechaFin()!=null){
                vencimientoPuntosOriginal.setFechaFin(vencimientoPuntos.getFechaFin());
            }
            if(vencimientoPuntos.getDuracion()!=null){
                vencimientoPuntosOriginal.setDuracion(vencimientoPuntos.getDuracion());
            }
        }
    }
}
