package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private EntityManager em;
    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    AsignacionDAO asignacionDAO;

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    public  void add(BolsaPuntos bolsa){
       bolsa.setSaldoPuntos(100); //
       bolsa.setPuntajesAsignado(100); //calular
       bolsa.setFechaAsignacion(new Date());
       bolsa.setFechaCaducidad(new Date()); // calcular
       bolsa.setPuntajeUtilizado(0);
       Cliente cliente= clienteDAO.get(bolsa.getCliente().getIdCliente());
       if (cliente!=null){
           bolsa.setCliente(cliente);
       }
       this.em.persist(bolsa);
    }
    /*
    public  void add(BolsaPuntos bolsa){

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


        if (cliente != null) { // en caso que recibamos un cliente
            //primero persisitimos el cliente
            clienteDAO.add(cliente);
            this.em.persist(bolsa);
        }
    }
    */
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
    public List<BolsaPuntos>  getByClienteId(Long id_cliente){
        Cliente cliente = clienteDAO.get(id_cliente); //obtengo el cleinte de mi BD
        Query q= em.createQuery("select b from BolsaPuntos b where  b.cliente= :cliente");
        return (List<BolsaPuntos>) q.setParameter("cliente",cliente).getResultList();
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

    public List<Cliente> getByExpireDays(int dias) {
        Date currentDate= new  Date(); //obtengo el dia actual
        List<Cliente> cliente= new ArrayList<>();
        Query q = this.em.createQuery("select b from BolsaPuntos b where b.saldoPuntos>0 and b.fechaCaducidad>= :currentDate ");
        List<BolsaPuntos> bolsaPuntos= q.setParameter("currentDate",currentDate).getResultList(); // obtengo todas las bolsa de puntos
        for( BolsaPuntos bolsa: bolsaPuntos){
            int diasDiferencia=(int) ((bolsa.getFechaCaducidad().getTime()-currentDate.getTime())/86400000); // obtengo los dias que me queda para esta bolsa Puntos
            if (diasDiferencia==dias){
                if ( !cliente.contains(bolsa.getCliente())) cliente.add(bolsa.getCliente()); //si no esta en mi lista Agrego
            }
        }
        return cliente;
    }
}
