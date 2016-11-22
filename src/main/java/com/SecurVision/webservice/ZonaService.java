package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Persona;
import com.SecurVision.ObjectModel.Usuario;
import com.SecurVision.ObjectModel.Zona;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 25/10/2016.
 */
@Path("/zona")
public class ZonaService {
    private Managers m = Managers.getInstance();


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getZonas(){
        try {
            GenericEntity<List<Zona>> entity = m.zonaManager.getZonas();
            return Response.status(200).entity(entity).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e).build();

        }
    }

    @GET
    @Path("/{zid}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public  Response getZonaById(@PathParam("zid") int zid){
        return Response.status(403).entity(zid).build();
    }

    @GET
    @Path("/{zid}/persona")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public  Response getPersonasByZona(@PathParam("zid") int zid){
        try {
            GenericEntity<List<Persona>> entity = m.personaManager.getPersonaByZona(zid);
            return Response.status(200).entity(entity).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(403).entity(e).build();

        }
    }
    @GET
    @Path("/{zid}/usuario")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public  Response getPUsuarioByZona(@PathParam("zid") int zid){
        try {
            GenericEntity<List<Usuario>> entity = m.usuarioManager.getUsuarioByZona(zid);
            return Response.status(200).entity(entity).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e).build();

        }
    }

}
