package com.SecurVision.test;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;


public class UploadFileTest {
	public static void main(String[] args) throws IOException, InterruptedException{
		try{
	    final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
	    System.out.println();
	    
	    //final JFileChooser fc = new JFileChooser();
	    //File file = fc.getSelectedFile();
	    //final FileDataBodyPart filePart = new FileDataBodyPart("file", file);
	    final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("C:\\Users\\adrian\\aaaa.jpg"));
	    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
	    final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);
	    //Thread.sleep(10000);
	    //final WebTarget target = client.target("http://securvision.cloudapp.net/SecurVision/upload/pdf");
	    //final WebTarget target = client.target("http://162.220.55.166:8080/SecurVision/autenticacion/imagen";
		//final WebTarget target = client.target("http://localhost:8080/autenticacion/imagen");
			final WebTarget target = client.target("http://162.220.55.166:8080/autenticacion/imagen");

			final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
	     
	    System.out.println(response.getStatus()+" : "+response.getStatusInfo());
		System.out.println(response.readEntity(String.class).toString());
			/*
		if(response.readEntity(boolean.class))
			System.out.println("Funciona!!!!!");
	    //Use response object to verify upload success
	    */
	     
	    formDataMultiPart.close();
	    multipart.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
