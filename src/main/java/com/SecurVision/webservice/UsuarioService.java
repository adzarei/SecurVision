package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 25/10/2016.
 */
@Path("/usuario")
public class UsuarioService {
    private Managers m = Managers.getInstance();
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getUsuarios(){


        GenericEntity<List<Usuario>> entity;
        try {
            entity = m.usuarioManager.getUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(entity).build();
    }

    @GET
    @Path("/{dni}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getUsuariosByDni(@PathParam("dni") String dni){
        Usuario entity = m.usuarioManager.getUsuarioByDni(dni);
        return Response.status(200).entity(entity).build();
    }

    @GET
    @Path("/{dni}/checkeo")
    public Response getChecheosByUsuario(@PathParam("dni") String dni){
        return Response.status(403).entity(dni).build();
    }

    @GET
    @Path("/{nick}/{password}")
    public Response logIn(@PathParam("nick") String nick,@PathParam("password") String password ){
        String login;
        try {
            login = m.usuarioManager.login(nick, password).toString();
        }catch (Exception e){
            return Response.status(403).entity("false").build();
        }
        return Response.status(200).entity(login).build();
    }

    @POST
    @Path("/new")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUsuario(String json){
        Boolean res;
        try {
            res = m.usuarioManager.createUsuario(json);
        } catch (SQLException e) {
            return Response.status(403).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(res.toString()).build();
    }

    @DELETE
    @Path("/{dni}/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteUsusario(@PathParam("dni")String dni){
        Boolean res;
        try{
            res = m.usuarioManager.deleteUsuario(dni);
        }catch (Exception e){
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
            Boolean res = m.usuarioManager.updateUsuario(json);
            return Response.status(201).entity(res.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }
}
