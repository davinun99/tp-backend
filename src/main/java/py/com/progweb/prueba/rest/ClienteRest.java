package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.ejb.PersonaDataAccessObject;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Persona;

import javax.inject.Inject;
import javax.management.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")

public class ClienteRest {
    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/")
    public Response listarClientes(){
     return  Response.ok(this.clienteDAO.getAll()).build();
    }


    @GET
    @Path("/id/{id_cliente}")
    public Response getCliente(@PathParam("id_cliente") Long id_cliente){
        return Response.ok(clienteDAO.get(id_cliente)).build();
    }
    @GET
    @Path("/nombre/{nombre_cliente}")
    public Response getClienteByName(@PathParam("nombre_cliente") String nombre ){
        Cliente cliente= clienteDAO.getClienteByName(nombre);
        return  Response.ok(cliente).build();
    }
    @POST
    @Path("/")
    public  Response agregar(Cliente cliente){
        this.clienteDAO.add(cliente);
        return Response.ok().build();
    }
}

