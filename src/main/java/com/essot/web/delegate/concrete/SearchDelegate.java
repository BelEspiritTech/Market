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
	
	public ProductDetails getSearchResults(String searchText){
		
		ProductDetails details = new ProductDetails();
		Collection<Object> searchBy = new ArrayList<Object>();
		
		searchBy.add(searchText);
		
		List<IEssotEntity>  products =  productDAO.searchOnIndexes(searchBy);
		
		
				
		if(products != null && !products.isEmpty()){
			for(IEssotEntity product : products){
				ProductCategoryDetails productDetails = new ProductCategoryDetails();
				
				productDetails.setDescription(((Product)product).getDescription());
				productDetails.setName(((Product)product).getName());
				productDetails.setSkuName(((Product)product).getSkuName());
				productDetails.setPrice(((Product)product).getB2cNowPrice());
				productDetails.setLongDescription(((Product)product).getLongDescription());
				
				//details.a
			}
		}
		return details;
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
