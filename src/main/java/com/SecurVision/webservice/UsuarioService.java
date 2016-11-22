package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import com.SecurVision.ObjectModel.Usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by adrian on 25/10/2016.
 */
@Path("/usuario")
public class UsuarioService {
    private Managers m = Managers.getInstance();
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getUsuarios(){
        return Response.status(403).build();
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
}
