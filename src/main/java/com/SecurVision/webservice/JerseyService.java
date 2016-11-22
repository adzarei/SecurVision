package com.SecurVision.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/test")
public class JerseyService{
	public Response getDescription() {
		return null;
	}

	@GET
	@Path("/test")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTest(){

		return Response.status(200).entity("ACK").build();
	}
	
	@POST
	@Path("/upload")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{
	    String UPLOAD_PATH = "/home/pin/uploads/";
	    //String UPLOAD_PATH = System.getProperty("user.home")+"/pin/uploads/";
	    
	    int read = 0;
	    byte[] bytes = new byte[1024];
	    
	    if(fileMetaData==null)
	    	return Response.status(555).entity("fileMetaData = null").build();
	    
	    System.out.print(UPLOAD_PATH + fileMetaData.getFileName());
	    OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
	    while ((read = fileInputStream.read(bytes)) != -1)
	            out.write(bytes, 0, read);
	        
	    out.flush();	
	    out.close();
	    
	    return Response.status(200).entity("Data uploaded successfully !!").build();
	}
}
