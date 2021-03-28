package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ConceptoDAO;
import py.com.progweb.prueba.ejb.UsoPuntosDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("usoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class UsoPuntosRest {
    @Inject
    private UsoPuntosDAO usoPuntosDAO;
    @POST
    @Path("/")
    public Response utilizarPuntos( List<UsoPuntosCabecera> listUsoPuntos){
        for (UsoPuntosCabecera usoPuntosCabecera : listUsoPuntos) {
            this.usoPuntosDAO.utilizarPuntos(usoPuntosCabecera);
        }
        return Response.ok().build();
    }
    @GET
    @Path("/concepto/{idConcepto}")
    public Response getUsoByConcepto(@PathParam("idConcepto") Integer idConcepto){
        return Response.ok(usoPuntosDAO.getByConcepto(idConcepto)).build();
    }
    @GET
    @Path("/fecha/{fecha}")
    public Response getUsoByFecha(@PathParam("fecha") String fecha){
        return Response.ok(usoPuntosDAO.getByFecha(fecha)).build();
    }
    @GET
    @Path("/cliente/{idCliente}")
    public Response getUsoByCliente(@PathParam("idCliente") Long idCliente){
        return Response.ok(usoPuntosDAO.getByCliente(idCliente)).build();
    }
}