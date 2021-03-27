package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ConceptoDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("conceptoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoRest {
    @Inject
    private ConceptoDAO conceptoDAO;
    @POST
    @Path("/")
    public Response agregar(ConceptoPuntos conceptoPuntos){
        this.conceptoDAO.add(conceptoPuntos);
        return Response.ok().build();
    }
    @GET
    @Path("/{idConcepto}")
    public Response getConceptoById(@PathParam("idConcepto") Integer idConcepto){
        return Response.ok(conceptoDAO.getById(idConcepto)).build();
    }
}
