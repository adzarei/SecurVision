package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Checkeo;
import com.SecurVision.ObjectModel.Persona;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 25/10/2016.
 */
@Path("/persona")
public class PersonaService {
    private Managers m = Managers.getInstance();
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getPersonas(){
        try {
            GenericEntity<List<Persona>> entity = m.personaManager.getPersonas();
            return Response.status(200).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{dni}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getUsuariosByDni(@PathParam("dni") String dni){
        Persona persona;
        try {
            persona = m.personaManager.getPersonaByDni(dni);
        } catch (Exception e) {
            e.printStackTrace();
        return Response.status(403).entity(e).build();
        }
        return Response.status(200).entity(persona).build();
    }


    @GET
    @Path("/{dni}/checkeo")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getCheckeosByUsuario(@PathParam("dni") String dni) {
        try {
            GenericEntity<List<Checkeo>> entity = m.checkeoManager.getCheckeosByPersona(dni);
            return Response.status(200).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e).build();
        }
    }

    @GET
    @Path("/{dni}/imagen")
    @Produces({MediaType.MULTIPART_FORM_DATA})
    public Response getImagenByPersona(){


        return Response.status(200).entity(null).build();
    }

    @POST
    @Path("/new")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPersona(String jsonString){
        Boolean res;
        try {
            res = m.personaManager.createPersona(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }

        return Response.status(201).entity(res.toString()).build(); //boolean not available for MessageBodyWriter;
    }

    @DELETE
    @Path("/{dni}/delete")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.TEXT_PLAIN})
    public Response deletePersona(@PathParam("dni") String dni){
        Boolean res ;
        try {
            res = m.personaManager.deletePersona(dni);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(res.toString()).build();

    }

    @POST
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updatePersona(String json){
        try {
            Boolean res = m.personaManager.updatePersona(json);
            return Response.status(201).entity(res.toString()).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }
}
