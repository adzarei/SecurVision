package com.SecurVision.webservice;

import com.SecurVision.EntityManager.Managers;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by adrian on 24/10/2016.
 */
@Path("/autenticacion")
public class AutenticacionService {
    private Managers m = Managers.getInstance();

    @GET
    @Path("/description")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getDescription() {
        String responseEntity ="";
        return Response.status(200).entity(responseEntity).build();
    }

    @POST
    @Path("/imagen/{zid}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
    public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                    @PathParam("zid") String zid)throws Exception{
        if(fileMetaData==null)
            return Response.status(403).entity("false").build();
        try {
            boolean b = m.autenticacionManager.recognize(fileInputStream,zid);
            fileInputStream.close();
            return Response.status(201).entity(b).build();

        }catch (Exception e){
            e.printStackTrace();
            fileInputStream.close();
            return Response.status(403).entity("false").build();
        }
    }

    @POST
    @Path("/imagen/new")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadImagen(String jsonStr){
        Boolean res;

        try {
            res = m.autenticacionManager.uploadImagen(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(403).entity(e).build();
        }


        return Response.status(201).entity(res.toString()).build();
    }

    @GET
    @Path("QR/{dni}/{zid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response autenticacionQR(@PathParam("dni") String dni, @PathParam("zid") String zid){
        Boolean res;
        try {
            res = m.autenticacionManager.autenticacionQR(dni, zid);
        }catch (Exception e){
            return Response.status(403).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(res.toString()).build();
    }
}
