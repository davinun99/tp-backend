package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private  EntityManager em;
    // funcio para agregar un nuevo cliente
    public  void add(Cliente cliente){
        em.persist(cliente);
    }
    //funcion para listar todos los clientes
    public List<Cliente> getAll(){
        Query query = this.em.createQuery("select c from Cliente c");
        return  (List<Cliente>)  query.getResultList();
    }

    public Cliente get(Long id_cliente){
        Cliente cliente= this.em.find(Cliente.class,id_cliente);
        return cliente;
    }

    public Cliente getClienteByName(String nombre) {
        Query q= this.em.createQuery("select c from Cliente c where c.nombre=nombre");
        return  (Cliente) q.getSingleResult();
    }
}
