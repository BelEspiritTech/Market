package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.controller.data.ProductDetails;
import com.essot.web.delegate.EssotDelegate;

public class SearchDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productDAO;
	
	public List<ProductDetails> getSearchResults(String searchText){
		
		List<ProductDetails> searchResults = new ArrayList<ProductDetails>();

		Collection<Object> searchBy = new ArrayList<Object>();
		searchBy.add(searchText);
		List<IEssotEntity>  products =  productDAO.searchOnIndexes(searchBy);
				
		if(products != null && !products.isEmpty()){
			for(IEssotEntity productEntity : products){
				
				ProductDetails productDetails = new ProductDetails();
				
				ProductCategoryDetails product = new ProductCategoryDetails();
				
				product.setDescription(((Product)productEntity).getDescription());
				product.setName(((Product)productEntity).getName());
				product.setSkuName(((Product)productEntity).getSkuName());
				product.setPrice(((Product)productEntity).getB2cNowPrice());
				product.setLongDescription(((Product)productEntity).getLongDescription());
				
				productDetails.setProductDetails(product);
				
				searchResults.add(productDetails);
			}
		}
		return searchResults;
	}

	@Override
	public void persistEntity(IEssotEntity entity) {
		productDAO.persistEntity(entity);
	}

	@Override
	public IEssotEntity findEntityById(Integer id) {
		return productDAO.findEntityById(id);
	}

	@Override
	public void updateEntity(IEssotEntity entity) {
		productDAO.updateEntity(entity);		
	}

	@Override
	public void deleteEntity(IEssotEntity entity) {
		productDAO.deleteEntity(entity);		
	}		
}
