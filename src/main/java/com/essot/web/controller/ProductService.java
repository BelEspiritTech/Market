package com.essot.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.essot.web.controller.data.GetProductDetailsResponse;
import com.essot.web.controller.data.ProductDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.delegate.concrete.ProductDelegate;

@Component
@Path("/product")
@Produces( { "application/json", "application/xml" } )
@Transactional
public class ProductService {
	
	@Autowired
	private EssotDelegate productDelegate;
	
	@GET
	@Path( "/detail/{skuName}" )
	public Response getProductCategory(@PathParam( "skuName" ) String skuName){
		GetProductDetailsResponse returnObj = null;
		try{
			 ProductDetails delegateResponse = ((ProductDelegate)productDelegate).getDetailsForProduct(skuName);
			 if(delegateResponse != null && delegateResponse.getProductDetails() != null){
				 returnObj = new GetProductDetailsResponse();
				 returnObj.setDetails(delegateResponse);
			 }
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}
	@GET
	@Path( "/pagetitle/{skuName}" )
	public Response getProductSKUName(@PathParam( "skuName" ) String skuName){
		String returnStr = null;
		try{
			 String name = ((ProductDelegate)productDelegate).getProductName(skuName);
			 if(name != null){
				 returnStr = name;
			 }
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnStr).build();
	}
}	

