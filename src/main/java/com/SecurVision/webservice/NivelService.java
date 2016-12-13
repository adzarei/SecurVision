package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Nivel;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 13/12/2016.
 */
@Path("/nivel")
public class NivelService {
    private Managers m = Managers.getInstance();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getZonas(){
        try {
            GenericEntity<List<Nivel>> entity = m.nivelManager.getNiverles();
            return Response.status(200).entity(entity).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteNivel(@PathParam("id") String id){
        try{
            Boolean res = m.nivelManager.deleteNivel(id);
            return Response.status(200).entity(res.toString()).build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }


    @POST
    @Path("/new")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crerateNivel(String json){
        try {
            Boolean res = m.nivelManager.createNivel(json);
            return Response.status(201).entity(res.toString()).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }
}
