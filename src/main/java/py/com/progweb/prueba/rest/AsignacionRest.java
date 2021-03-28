package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.AsignacionDAO;
import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("asignacion")
@Consumes("application/json")
@Produces("application/json")

public class AsignacionRest {
    @Inject
    private AsignacionDAO asignacionDao;

    @POST
    @Path("/")
    public Response agregar(AsignacionPuntos asignacionPuntos){
        this.asignacionDao.add(asignacionPuntos);
        return Response.ok().build();
    }
    @GET
    @Path("/regla/{monto}")
    public Response getReglaByMonto( @PathParam("monto") Integer monto ){
        String respuesta = " {\"monto\": " +  asignacionDao.getReglaByMonto(monto) + " }";
        return Response.ok(respuesta).build() ;
    }
    @DELETE
    @Path("eliminar/{id_asignacion}")
    public  Response deleteAsignacionRest(@PathParam("id_asignacion") Integer id_asignacion){
        asignacionDao.deleteAsignacion(id_asignacion);
        return Response.ok("Regla Asignacion Eliminado Correctamente").build();
    }
    @PUT
    @Path("actualizar")
    public Response updateAsignacionRest(AsignacionPuntos asignacionPuntos){
        asignacionDao.updateVencimiento(asignacionPuntos);
        return Response.ok("Regal de Asignacion Actualizado Correctamente").build();
    }
}
