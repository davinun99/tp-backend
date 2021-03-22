package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.ejb.PersonaDataAccessObject;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Persona;

import javax.inject.Inject;
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
    public Response listar(){
     return  Response.ok(this.clienteDAO.getAll()).build();
    }

    @POST
    @Path("/")
    public  Response agregar(Cliente cliente){
        this.clienteDAO.add(cliente);
        return Response.ok().build();
    }
}

