package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Checkeo;
import com.SecurVision.ObjectModel.Persona;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        return Response.status(403).build();
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
        m.usuarioManager.createPersonax(jsonString);


        return Response.status(201).entity(jsonString).build();
    }


}
