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

import com.essot.web.controller.data.ProductDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.delegate.concrete.SearchDelegate;

@Component
@Path("/search")
@Produces( { "application/json", "application/xml" } )
@Transactional
public class SearchService {
	
	@Autowired
	private EssotDelegate searchDelegate;
	
	@GET
	@Path( "/{param}" )
	public Response doSearch(@PathParam( "param" ) String searchText){
		Object returnObj = null;
		try{
			ProductDetails delegateResponse = ((SearchDelegate)searchDelegate).getSearchResults(searchText);
			 if(delegateResponse != null && delegateResponse.getProductDetails() != null){
				// returnObj = new SearchResponse();
				// returnObj.setDetails(delegateResponse);
			 }
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}	
}	

