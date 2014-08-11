package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.controller.data.GetProductCategoryResponse;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.MenuUtil;

public class ProductCategoryXProductDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productCategoryXProductDAO;
	
	@Autowired
	IEssotDAO productDAO;
	
	public void persistEntity(IEssotEntity entity) {
		productCategoryXProductDAO.persistEntity(entity);
	}

	public IEssotEntity findEntityById(Integer id) {
		return productCategoryXProductDAO.findEntityById(id);
	}

	public void updateEntity(IEssotEntity entity) {
		productCategoryXProductDAO.updateEntity(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		productCategoryXProductDAO.deleteEntity(entity);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public GetProductCategoryResponse getAllProductsForCategory(String key){
		
		GetProductCategoryResponse response = new GetProductCategoryResponse();
		List<ProductCategoryDetails> categoryDetails = new ArrayList<ProductCategoryDetails>();
		Collection<Object> categoryKeys   = new ArrayList<Object>();
		Collection<Object> relatedSKUNames = new ArrayList<Object>();
		
		List<String> bannerList = new ArrayList<String>();
	
		categoryKeys = MenuUtil.getSubMenus(new Integer(key));
	
		List<IEssotEntity>  categoryProductList =  productCategoryXProductDAO.getFilteredListOnPrimarKey(categoryKeys);
		
		if(categoryProductList != null && !categoryProductList.isEmpty()){
			for(IEssotEntity categoryProduct : categoryProductList){
				if("Y".equalsIgnoreCase(((ProductCategoryXProduct)categoryProduct).getActiveFlag())){
					relatedSKUNames.add(((ProductCategoryXProduct)categoryProduct).getSkuName());
				}
			}
		}
		
		List<IEssotEntity>  productList =  productDAO.getFilteredListOnPrimarKey(relatedSKUNames);
		if(productList != null && !productList.isEmpty()){
			for(IEssotEntity product : productList){
				if("Y".equalsIgnoreCase(((Product)product).getActiveFlag())){
					ProductCategoryDetails details = new ProductCategoryDetails();
					
					details.setDescription(((Product)product).getDescription());
					details.setName(((Product)product).getName());
					details.setSkuName(((Product)product).getSkuName());
					details.setPrice(((Product)product).getB2cNowPrice());
					
					if(((Product)product).getPriority() != null && ((Product)product).getPriority() > 0 && ((Product)product).getPriority() <= 3){
						bannerList.add(((Product)product).getSkuName());
					}
					
					categoryDetails.add(details);
				}
			}
				
		}
		
		response.setBannerList(bannerList);
		response.setProducts(categoryDetails);
		
		return response;
	}
}
