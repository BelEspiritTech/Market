package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.controller.data.GetProductCategoryResponse;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.EssotDAOEnum;
import com.essot.web.util.MenuUtil;

public class ProductCategoryXProductDelegate extends EssotDelegate {

	/**
	 * 
	 */
	public void persistEntity(IEssotEntity entity) {
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).persistEntity(entity);
	}

	/**
	 * 
	 */
	public IEssotEntity findEntityById(Integer id) {
		return daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).findEntityById(id);
	}

	/**
	 * 
	 */
	public void updateEntity(IEssotEntity entity) {
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).updateEntity(entity);
	}

	/**
	 * 
	 */
	public void deleteEntity(IEssotEntity entity) {
		daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).deleteEntity(entity);
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
	
		List<IEssotEntity>  categoryProductList =  daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).getFilteredListOnPrimarKey(categoryKeys);
		
		if(categoryProductList != null && !categoryProductList.isEmpty()){
			for(IEssotEntity categoryProduct : categoryProductList){
				if("Y".equalsIgnoreCase(((ProductCategoryXProduct)categoryProduct).getActiveFlag())){
					relatedSKUNames.add(((ProductCategoryXProduct)categoryProduct).getSkuName());
				}
			}
		}
		
		List<IEssotEntity>  productList =  daoFactory.getDAOClassByDAOEnum(EssotDAOEnum.PRODUCT).getFilteredListOnPrimarKey(relatedSKUNames);
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
