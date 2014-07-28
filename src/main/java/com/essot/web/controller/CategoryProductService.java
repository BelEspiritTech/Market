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

import com.essot.web.controller.data.GetProductCategoryResponse;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.delegate.concrete.ProductCategoryXProductDelegate;

@Component
@Path("/productCategory")
@Produces( { "application/json", "application/xml" } )
@Transactional
public class CategoryProductService {
	
	@Autowired
	private EssotDelegate productCategoryDelegate;
	
	@GET
	@Path( "/{id}" )
	public Response getProductCategory(@PathParam( "id" ) String categoryID){
		GetProductCategoryResponse returnObj = null;
		try{
			returnObj = ((ProductCategoryXProductDelegate)productCategoryDelegate).getAllProductsForCategory(categoryID);
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}
	
}
