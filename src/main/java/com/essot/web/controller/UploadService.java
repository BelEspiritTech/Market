package com.essot.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.essot.web.upload.DataUploadDelegate;

@Component
@Path("/upload")
@Produces( { "application/json", "application/xml" } )
@Transactional
public class UploadService {
	
	@Autowired
	private DataUploadDelegate uploadDelegate;
	
	private static final String SUCCESS_SERVICE = "service";
	private static final String FAILURE_SERVICE = "failure";
	
	@GET
	public Response startUpload(){
		String response = FAILURE_SERVICE ;
		try{
			 uploadDelegate.updateData();
			 response = UploadService.SUCCESS_SERVICE;
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(response).build();
	}	
}
