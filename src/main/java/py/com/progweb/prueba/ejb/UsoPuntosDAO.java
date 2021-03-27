package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsoPuntosDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;
    public void addCabecera(UsoPuntosCabecera usoPuntosCabecera){
        em.persist(usoPuntosCabecera);
    }
    public void addDetalle(UsoPuntosDetalle usoPuntosDetalle){
        em.persist(usoPuntosDetalle);
    }
    public UsoPuntosCabecera getByConcepto(){
        //TODO: implementar metodo
        return null;
    }
    public UsoPuntosCabecera getByFecha(){
        //TODO: implementar metodo
        return null;
    }
    public UsoPuntosCabecera getByCliente(){
        //TODO: implementar metodo
        return null;
    }
    public void utilizarPuntos(Long idCliente, Integer idConceptoUso){
        //TODO: Crear UsoPuntos, actualizar BolsaPuntos
        /*
        Debe utilizarse en un esquema FIFO (primero se utilizan las bolsas más antiguas). Tiene un
detalle debido a que para satisfacer una petición de puntos se puede utilizar más de una bolsa.

        : se recibe el identificador del cliente y el identificador del
concepto de uso y se descuenta dicho puntaje al cliente registrando el uso de puntos
(genera datos con la estructura del punto 6 y actualiza la del punto 5)
        * */

        //TODO: mandar Mail
    }

}
