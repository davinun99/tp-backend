package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.VencimientoDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("vencimiento")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoRest {
    @Inject
    private VencimientoDAO vencimientoDAO;
    @POST
    @Path("/")
    public Response agregar(VencimientoPuntos vencimientoPuntos){
        this.vencimientoDAO.add(vencimientoPuntos);
        return Response.ok().build();
    }
    @GET
    @Path("/duracion/{fecha}")
    public Response getDuracionByFecha(@PathParam("fecha") String fecha){
        String respuesta = "{\"duracion\":" + vencimientoDAO.getDuracion(fecha) + " }";
        return Response.ok(respuesta).build();
    }
}
