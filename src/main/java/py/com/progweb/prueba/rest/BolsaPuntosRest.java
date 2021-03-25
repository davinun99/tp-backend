package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("bolsaPuntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {

    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;
    @GET
    @Path("/")
    public Response getAll(){
        return  Response.ok(bolsaPuntosDAO.getAll()).build();
    }

    @POST
    @Path("/")
    public  Response add(BolsaPuntos bolsaPuntos){
        bolsaPuntosDAO.add(bolsaPuntos);
        return Response.ok().build();
    }

    @GET
    @Path("/cliente/{id_cliente}")
    public Response getByClienteId(@PathParam("id_cliente") Long id_cliente){
        return Response.ok(bolsaPuntosDAO.getByClienteId(id_cliente)).build();
    }

    @GET
    @Path("/rango/limiteI/{limiteI}/limiteS/{limiteS}")
    public Response getByRange(@PathParam("limiteI") Integer limiteI, @PathParam("limiteS") Integer limteS){
        return  Response.ok(bolsaPuntosDAO.getByRange(limiteI,limteS)).build();
    }
}
