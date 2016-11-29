package com.SecurVision.dataAccess;

import com.SecurVision.constants.Constants;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by adrian on 02/11/2016.
 */
public class AutenticationAccessor {


    public AutenticationAccessor(){}

    public String recognize(String base64Img){
        final Client client = ClientBuilder.newBuilder().register(String.class).build();
        String body = "{\"image\":\""+base64Img+"\"," +
                "\"gallery_name\":\""+Constants.GALLERY_NAME+"\"}";

        Response response = client.target("https://api.kairos.com/recognize")
                                        .request(MediaType.APPLICATION_JSON)
                                        .header("app_id", Constants.KUSER)
                                        .header("app_key",Constants.KKEY)
                                        .post(Entity.entity(body,MediaType.APPLICATION_JSON));

        System.out.println(response.getStatus()+" : "+response.getStatusInfo());
        String res = response.readEntity(String.class);
        System.out.println(res);

        response.close();
        client.close();
        return res;
    }

    public String uploadImagen(String dni, String imagen){
        final Client client = ClientBuilder.newBuilder().register(String.class).build();
        String body = "{" +
                "\"image\":\""          +imagen                  +"\"," +
                "\"subject_id\":\""     +dni                     +"\"," +
                "\"gallery_name\":\""   +Constants.GALLERY_NAME  +"\"" +
                "}";

        Response response = client.target("https://api.kairos.com/enroll")
                .request(MediaType.APPLICATION_JSON)
                .header("app_id", Constants.KUSER)
                .header("app_key",Constants.KKEY)
                .post(Entity.entity(body,MediaType.APPLICATION_JSON));

        return response.readEntity(String.class);
    }
}
