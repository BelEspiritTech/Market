package com.essot.web.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.essot.web.controller.data.CategoryDetails;
import com.essot.web.controller.data.GetHomePageResponse;
import com.essot.web.controller.data.GetMenuResponse;
import com.essot.web.controller.data.GetProductCategoryResponse;
import com.essot.web.controller.data.Menu;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.delegate.concrete.ProductCategoryDelegate;
import com.essot.web.delegate.concrete.ProductDelegate;

@Component
@Path("/category")
@Produces( { "application/json", "application/xml" } )
@Transactional
public class CategoryService {
	
	@Autowired
	private EssotDelegate categoryDelegate;
	
	@Autowired
	private EssotDelegate productDelegate;
	
	@GET
	@Path( "/home" )
	public Response getAllCategories(){
		GetHomePageResponse returnObj = null;
		try{
			 List<CategoryDetails> delegateResponse = ((ProductCategoryDelegate)categoryDelegate).getDisplayCategories();
			 if(delegateResponse != null && delegateResponse.size() > 0){
				 returnObj = new GetHomePageResponse();
				 returnObj.setCategories(delegateResponse);
			 }
			 
			 List<ProductCategoryDetails> productResponse = ((ProductDelegate)productDelegate).getDisplayProducts();
			 if(productResponse != null && productResponse.size() > 0){
				 if(returnObj == null){
					 returnObj = new GetHomePageResponse();
				 }
				 
				 returnObj.setTopProducts(productResponse);
			 }		 
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}
	
	@GET
	@Path( "/menu" )
	public Response getMenus(){
		GetMenuResponse returnObj = null;
		try{
			 List<Menu> delegateResponse = ((ProductCategoryDelegate)categoryDelegate).getMenu();
			 if(delegateResponse != null && delegateResponse.size() > 0){
				 returnObj = new GetMenuResponse();
				 returnObj.setMenu(delegateResponse);
			 }
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}
	
	@GET
	@Path( "/clear" )
	public Response getClearMenus(){
		try{
			 ((ProductCategoryDelegate)categoryDelegate).clearCache();
			 
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok().build();
	}
	@GET
	@Path( "/list/{id}" )
	public Response getCategoryList(@PathParam( "id" ) String categoryID){
		GetProductCategoryResponse returnObj = null;
		try{
			returnObj = ((ProductCategoryDelegate)categoryDelegate).getAllCategoriesList(categoryID);
		}
		catch( Exception e ){
			throw new WebApplicationException( Response.Status.INTERNAL_SERVER_ERROR );
		}		 
		return Response.ok(returnObj).build();
	}
}
