package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.VencimientoDAO;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("vencimiento")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoRest {
    @Inject
    private VencimientoDAO vencimientoDAO;
    @POST
    @Path("/")
    public Response agregar(List<VencimientoPuntos> vencimientoPuntos){
        for (VencimientoPuntos vencimientoPunto:vencimientoPuntos){
            this.vencimientoDAO.add(vencimientoPunto);
        }
        return Response.ok().build();
    }
    @GET
    @Path("/duracion/{fecha}")
    public Response getDuracionByFecha(@PathParam("fecha") String fecha){
        String respuesta = "{\"duracion\":" + vencimientoDAO.getDuracion(fecha) + " }";
        return Response.ok(respuesta).build();
    }
    @DELETE
    @Path("eliminar/{id_vencimiento}")
    public  Response deleteVencimientoRest(@PathParam("id_vencimiento") Integer id_vencimiento){
        vencimientoDAO.deleteVencimiento(id_vencimiento);
        return Response.ok("Vencimiento Eliminado Correctamente").build();
    }
    @PUT
    @Path("acutalizar")
    public Response updateVencimientoRest(VencimientoPuntos vencimientoPuntos){
        vencimientoDAO.updateVencimiento(vencimientoPuntos);
        return Response.ok("Vencimiento Actualizado Correctamente").build();
    }
}
