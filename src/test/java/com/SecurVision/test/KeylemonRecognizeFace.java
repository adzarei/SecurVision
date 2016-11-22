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
public class KeylemonRecognizeFace {
    public static void main(String args[]) {
        final Client client = ClientBuilder.newClient();
        client.register(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("https://klws.keylemon.com/api").path("recognize");


        Form form = new Form();
        form.param("user","Pinupv6");
        form.param("key","I2FVxMY6TVzBEtEjJ0qe40R7HaAo2fzJVJTg5Mjk3kShvRq742oPNb");
        form.param("urls","http://media2.s-nbcnews.com/j/newscms/2016_23/1575461/160612-obama-mn-1420_1698930825ea6875b3d992f28ae587ad.nbcnews-ux-2880-1000.jpg");
        form.param("models","185236ab-0eb6-4ddc-b643-3fcca90e51d1");
        form.param("groups","69639aa7-06f4-4aed-b3e5-bc4451ecf404");
        form.param("identities","4da04193-207a-4837-a63c-aaae75087579");
        form.param("mean","true");
        final Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        Response.class);
        System.out.println(response.getStatus()+" : "+response.getStatusInfo());
        System.out.println(response.readEntity(String.class).toString());
    }
}
