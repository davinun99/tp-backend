package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;
    @Inject
    private ClienteDAO clienteDAO;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    public  void add(BolsaPuntos bolsa){
        Cliente cliente= bolsa.getCliente();

        ;
        /*
        //primero calculmos la cantidad de puntos de acuerdo a la operacion
        //int cantPuntos = ap.getCantPuntos(bolsa.getMontoOperacion());
                //fechaActual
        bolsa.setFechaAsignacion();
        //fechaVencimiento (no se)
        bolsa.setFechaCaducidad();
        bolsa.setSaldoPuntos(cantPuntos);
        bolsa.setPuntajesAsignado(cantPuntos);
        bolsa.setPuntajeUtilizado(0);
        */

        if (cliente != null) { // en caso que recibamos un cliente
            //primero persisitimos el cliente
            clienteDAO.add(cliente);
            this.em.persist(bolsa);
        }
    }

    public void setBolsaPuntos(BolsaPuntos bolsa){
        Date actualDate= new Date();

        bolsa.setFechaAsignacion(actualDate);
        bolsa.setFechaCaducidad(actualDate);
    }

    public List<BolsaPuntos> getAll(){
        Query q= this.em.createQuery("select  b from BolsaPuntos b");
        return  (List<BolsaPuntos>) q.getResultList();
    }

    //Optenemos las bolsaPuntos por Cliente
    public  BolsaPuntos getByClienteId(Long id_cliente){
        Cliente cliente = clienteDAO.get(id_cliente); //obtengo el cleinte de mi BD
        Query q= em.createQuery("select b from BolsaPuntos b where  b.cliente=cliente");
        return (BolsaPuntos) q.getSingleResult();
    }

    //obtenemos todas las Bolsa de puntos cuyo SaldoPuntos estre entre el rango de LimiteInferior y limiteSuperior
    public List<BolsaPuntos> getByRange(Integer limiteInferior, Integer limiteSuperior){
        List<BolsaPuntos> bolsaPuntos= getAll();
        List<BolsaPuntos> resultSet= new ArrayList<>();

        for( BolsaPuntos bolsa: bolsaPuntos){
            if (bolsa.getSaldoPuntos()>=limiteInferior && bolsa.getSaldoPuntos()<=limiteSuperior){
                resultSet.add(bolsa);
            }
        }
        return resultSet;
    }

}
