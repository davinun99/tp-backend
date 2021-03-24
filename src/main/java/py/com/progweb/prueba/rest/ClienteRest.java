package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.ejb.PersonaDataAccessObject;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Persona;

import javax.inject.Inject;
import javax.management.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")

public class ClienteRest {
    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/")
    public Response getAllRest(){
     return  Response.ok(this.clienteDAO.getAll()).build();
    }


    @GET
    @Path("/id/{id_cliente}")
    public Response getClienteRest(@PathParam("id_cliente") Long id_cliente){
        return Response.ok(clienteDAO.get(id_cliente)).build();
    }

    @GET
    @Path("/name/{nombre_cliente}")
    public Response getClienteByNameRest(@PathParam("nombre_cliente") String nombre ){
        List<Cliente> clientes= this.clienteDAO.getClienteByName(nombre.toLowerCase());
        System.out.println("Aca explota");
        return  Response.ok(clientes).build();
    }

    @GET
    @Path("/lastName/{apellido_cliente}")
    public Response getClienteByLastNameRest(@PathParam("apellido_cliente") String apellido ){
        List<Cliente> clientes= this.clienteDAO.getClienteByLastName(apellido.toLowerCase());
        return  Response.ok(clientes).build();
    }

    @GET
    @Path("/birth/{birth}")
    public Response getClienteByBirthRest(@PathParam("birth") String birth){
        List<Cliente> clientes= this.clienteDAO.getClienteByBirth(birth);
        return  Response.ok(clientes).build();
    }
    @POST
    @Path("/")
    public  Response agregar(Cliente cliente){
        this.clienteDAO.add(cliente);
        return Response.ok().build();
    }
}

