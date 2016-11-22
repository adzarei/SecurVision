package com.SecurVision.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;
import sun.plugin.javascript.JSObject;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class UploadFileTestKairos {
	public static void main(String[] args) throws IOException, InterruptedException {
        try {
            final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            //Thread.sleep(10000);
            //final WebTarget target = client.target("http://securvision.cloudapp.net/SecurVision/upload/pdf");
            final WebTarget target = client.target("http://localhost:8080/persona/21804947");

            final Response response = target.request().get();



            System.out.println(response.getStatus() + " : " + response.getStatusInfo());
            //System.out.println(response.readEntity(String.class).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
