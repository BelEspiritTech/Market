package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.controller.data.ProductDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.EssotDAOEnum;

public class SearchDelegate extends EssotDelegate {
	
	/**
	 * 
	 * @param searchText
	 * @return
	 */
	public List<ProductDetails> getSearchResults(String searchText){
		
		List<ProductDetails> searchResults = new ArrayList<ProductDetails>();

		Collection<Object> searchBy = new ArrayList<Object>();
		searchBy.add(searchText);
		List<IEssotEntity>  products =  daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).searchOnIndexes(searchBy);
				
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
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).persistEntity(entity);
	}

	@Override
	public IEssotEntity findEntityById(Integer id) {
		return daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).findEntityById(id);
	}

	@Override
	public void updateEntity(IEssotEntity entity) {
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).updateEntity(entity);		
	}

	@Override
	public void deleteEntity(IEssotEntity entity) {
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).deleteEntity(entity);		
	}		
}
