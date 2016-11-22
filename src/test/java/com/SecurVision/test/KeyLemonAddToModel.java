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
public class KeyLemonAddToModel {
    public static void main(String args[]) {
        String modelId = "6e286c14-6b24-4891-adc1-0f072417f633";
        final Client client = ClientBuilder.newClient();
        client.register(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("https://klws.keylemon.com/api").path("model").path(modelId);


        Form form = new Form();
        form.param("user","Pinupv6");
        form.param("key","I2FVxMY6TVzBEtEjJ0qe40R7HaAo2fzJVJTg5Mjk3kShvRq742oPNb");
        form.param("urls","http://i63.tinypic.com/f1fvc6.jpg");
        //form.param("name","test");

        final Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        Response.class);
        System.out.println(response.getStatus()+" : "+response.getStatusInfo());
        System.out.println(response.readEntity(String.class).toString());
    }
}
