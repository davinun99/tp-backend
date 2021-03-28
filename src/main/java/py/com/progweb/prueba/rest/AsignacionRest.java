package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.AsignacionDAO;
import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.VencimientoPuntos;
import py.com.progweb.prueba.utils.CodigosDeEstado;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("asignacion")
@Consumes("application/json")
@Produces("application/json")

public class AsignacionRest {
    @Inject
    private AsignacionDAO asignacionDao;

    @POST
    @Path("/")
    public Response agregar(List<AsignacionPuntos> asignacionPuntos){
        for(AsignacionPuntos asignacionPunto:asignacionPuntos){
            this.asignacionDao.add(asignacionPunto);
        }
        return Response.ok().build();
    }
    @GET
    @Path("/")
    public Response getAll(){
        return Response.ok(asignacionDao.getAll()).build() ;
    }

    @GET
    @Path("/regla/{monto}")
    public Response getReglaByMonto( @PathParam("monto") Integer monto ){
        String respuesta = " {\"monto\": " +  asignacionDao.getReglaByMonto(monto) + " }";
        return Response.ok(respuesta).build() ;
    }
    @DELETE
    @Path("eliminar/{id_asignacion}")
    public Response deleteAsignacionRest(@PathParam("id_asignacion") Integer id_asignacion){
        Integer status = asignacionDao.deleteAsignacion(id_asignacion);
        Response response = Response.status(400).build();
        try{
            if (status == CodigosDeEstado.SUCCESS) {
                response = Response.ok("Regla Asignacion Eliminado Correctamente").build();
            } else if (status == CodigosDeEstado.NOT_FOUND) {
                response = Response.status(404).build();
            }
        }catch (Exception ex){
            response = Response.serverError().build();
        }
        return response;
    }
    @PUT
    @Path("actualizar")
    public Response updateAsignacionRest(AsignacionPuntos asignacionPuntos){
        Integer status = asignacionDao.updateVencimiento(asignacionPuntos);
        Response respuesta = Response.status(400).build();
        try {
            if (status == CodigosDeEstado.SUCCESS) {
                respuesta = Response.ok("Regal de Asignacion Actualizado Correctamente").build();
            } else if (status == CodigosDeEstado.NOT_FOUND) {
                respuesta = Response.status(404).build();
            }
        }catch (Exception ex){
            respuesta = Response.serverError().build();
        }
        return respuesta;
    }
}
