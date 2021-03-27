package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class UsoPuntosDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;
    public void addCabecera(UsoPuntosCabecera usoPuntosCabecera){
        em.persist(usoPuntosCabecera);
    }
    public void addDetalle(UsoPuntosDetalle usoPuntosDetalle){
        em.persist(usoPuntosDetalle);
    }
    public List<UsoPuntosCabecera> getByConcepto(Integer idConcepto){
        ConceptoPuntos concepto = this.em.find(ConceptoPuntos.class, idConcepto);
        Query q = em.createQuery("select u from UsoPuntosCabecera u where u.conceptoPuntos = :concepto");
        return (List<UsoPuntosCabecera>)q.setParameter("concepto", concepto).getResultList();
    }
    public List<UsoPuntosCabecera> getByFecha(String fechaStr){
        java.util.Date fecha= java.sql.Date.valueOf(fechaStr);
        Query q= em.createQuery("select u from UsoPuntosCabecera u where u.fecha= :fecha");
        return (List<UsoPuntosCabecera>) q.setParameter("fecha",fecha).getResultList();
    }
    public List<UsoPuntosCabecera> getByCliente(Long idCLiente){
        Cliente cliente = this.em.find(Cliente.class, idCLiente);
        Query q = em.createQuery("select u from UsoPuntosCabecera u where u.cliente = :cliente");
        return (List<UsoPuntosCabecera>)q.setParameter("cliente", cliente).getResultList();
    }
    public void utilizarPuntos(Long idCliente, Integer idConceptoUso){
        Cliente cliente= this.em.find(Cliente.class, idCliente);
        ConceptoPuntos conceptoPuntos = this.em.find(ConceptoPuntos.class, idConceptoUso);
        Integer puntosRequeridos = conceptoPuntos.getPuntosRequeridos();
        UsoPuntosCabecera nuevaCabecera = new UsoPuntosCabecera();//preparo la cabecera con la informacion necesaria
        nuevaCabecera.setCliente(cliente);
        nuevaCabecera.setPuntajeUtilizado(puntosRequeridos);
        nuevaCabecera.setConceptoPuntos(conceptoPuntos);
        nuevaCabecera.setFecha(new Date());
        this.addCabecera(nuevaCabecera); //guardo la cabecera

        if( bolsaPuntosDAO.getTotalDePuntosByCliente(idCliente) >= puntosRequeridos ){
            //Si la cantidad de puntos es la necesaria continuo
            List<BolsaPuntos> bolsaPuntosList = bolsaPuntosDAO.getByClienteId(idCliente);
            //consigo todas las listas de puntos del cliente
            for (BolsaPuntos bolsa: bolsaPuntosList) {
                UsoPuntosDetalle nuevoDetalle = new UsoPuntosDetalle();
                if( puntosRequeridos > bolsa.getSaldoPuntos() ){//si los puntos son mas que la bolsa, vacio la bolsa
                    nuevoDetalle.setPuntajeUtilizado(bolsa.getSaldoPuntos());//cargo el detalle
                    puntosRequeridos -= bolsa.getSaldoPuntos();
                    bolsaPuntosDAO.usarPuntos( bolsa, bolsa.getSaldoPuntos() );
                }else{//si los puntos son menores que la bolsa actual
                    nuevoDetalle.setPuntajeUtilizado( puntosRequeridos );
                    bolsaPuntosDAO.usarPuntos( bolsa, puntosRequeridos );
                    puntosRequeridos = 0;
                }
                nuevoDetalle.setIdBolsa(bolsa);
                nuevoDetalle.setCabecera(nuevaCabecera);
                this.addDetalle(nuevoDetalle);// guardo el detalle
                if (puntosRequeridos == 0){
                    break;
                }
            }
        }
        //TODO: mandar Mail
    }

}
