package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ClienteDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private  EntityManager em;

    public  void add(Cliente cliente){
        em.persist(cliente);
    }
    public List<Cliente> getAll(){
        Query query = this.em.createQuery("select c from Cliente c");
        return  (List<Cliente>)  query.getResultList();
    }
}
