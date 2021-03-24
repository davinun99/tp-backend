package py.com.progweb.prueba.ejb;

import org.omg.CORBA.PUBLIC_MEMBER;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "laboratiorioPersistanceUnit")
    private  EntityManager em;
    // funcio para agregar un nuevo cliente
    public  void add(Cliente cliente){
        this.em.persist(cliente);
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

    public List<Cliente> getClienteByName(String nombre) {
        Query q= this.em.createQuery("select c from Cliente c where LOWER(c.nombre) = :nombre");
        System.out.println("Aca explota en el select");
        return (List<Cliente>) q.setParameter("nombre", nombre).getResultList();

    }

    public List<Cliente> getClienteByLastName(String apellido) {
        Query q= this.em.createQuery("select c from Cliente c where LOWER(c.apellido) = :apellido");
        return (List<Cliente>)q.setParameter("apellido", apellido).getResultList();
    }

    public  List<Cliente> getClienteByBirth(String birthString){
        //conviero String a Date
        //LocalDate birth=LocalDate.parse(birthString);
        /*
        Date birth=null;
        try {
            birth=new SimpleDateFormat("dd/MM/yyyy").parse(birthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        java.util.Date birth= Date.valueOf(birthString);
        Query q= this.em.createQuery("select c from Cliente c where c.fechaNacimiento= :birth ");
        return  (List<Cliente>) q.setParameter("birth",birth).getResultList();
    }

}
