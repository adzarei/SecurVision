package com.SecurVision.test;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by adrian on 29/10/2016.
 */
public class KeyLemonGetModels {

    public static void main(String args[]) {
        String modelId = "185236ab-0eb6-4ddc-b643-3fcca90e51d1";
        final Client client = ClientBuilder.newClient();
        client.register(new ClientConfig().register(LoggingFilter.class ));
       // WebTarget target = client.target("https://klws.keylemon.com/api").path("model");//.path(modelId);
        WebTarget target = client.target("https://klws.keylemon.com/api/model/?user=Pinupv6&key=I2FVxMY6TVzBEtEjJ0qe40R7HaAo2fzJVJTg5Mjk3kShvRq742oPNb&modality=face");//.path(modelId);


        Form form = new Form();
        form.param("user","Pinupv6");
        form.param("key","I2FVxMY6TVzBEtEjJ0qe40R7HaAo2fzJVJTg5Mjk3kShvRq742oPNb");
        //form.param("urls","https://upload.wikimedia.org/wikipedia/commons/7/79/BarackObama2005portrait_edit2.jpg");
        form.param("modality","face");
        System.out.println(target.getUri());
        final Response response = target.request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println(response.getStatus()+" : "+response.getStatusInfo());
        System.out.println(response.readEntity(String.class).toString());
    }
}
