package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductXENCode;
import com.essot.web.backend.entity.concrete.ProductXFeature;
import com.essot.web.backend.entity.concrete.ProductXTechSpec;
import com.essot.web.backend.entity.concrete.RelatedSKUs;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.controller.data.ProductDetails;
import com.essot.web.controller.data.ProductEnCodes;
import com.essot.web.controller.data.ProductFeatures;
import com.essot.web.controller.data.ProductTechSpecs;
import com.essot.web.controller.data.RelatedProductDetails;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.EssotComparatorEnum;
import com.essot.web.util.EssotComparatorFactory;
import com.essot.web.util.EssotDAOEnum;

public class ProductDelegate extends EssotDelegate {

	/**
	 * 
	 */
	public void persistEntity(IEssotEntity entity) {
		daoFactory.getDAO(EssotDAOEnum.PRODUCT).persistEntity(entity);
	}

	/**
	 * 
	 */
	public IEssotEntity findEntityById(Integer id) {
		return daoFactory.getDAO(EssotDAOEnum.PRODUCT).findEntityById(id);
	}

	/**
	 * 
	 */
	public void updateEntity(IEssotEntity entity) {
		daoFactory.getDAO(EssotDAOEnum.PRODUCT).updateEntity(entity);
	}

	/**
	 * 
	 */
	public void deleteEntity(IEssotEntity entity) {
		daoFactory.getDAO(EssotDAOEnum.PRODUCT).deleteEntity(entity);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ProductCategoryDetails> getDisplayProducts(){
		List<ProductCategoryDetails> diplayProducts = new ArrayList<ProductCategoryDetails>();
		List<IEssotEntity>  products = daoFactory.getDAO(EssotDAOEnum.PRODUCT).readAllData();
		if(products != null && !products.isEmpty()){
			for(IEssotEntity product : products){
				
				if(("Y").equals(((Product)product).getActiveFlag()) && ((Product)product).getPriority() != 0  ){
					ProductCategoryDetails productDetails = new ProductCategoryDetails();
					
					productDetails.setDescription(((Product)product).getDescription());
					productDetails.setName(((Product)product).getName());
					productDetails.setSkuName(((Product)product).getSkuName());
					productDetails.setPrice(((Product)product).getB2cNowPrice());
					productDetails.setLongDescription(((Product)product).getLongDescription());
					productDetails.setPriority(((Product)product).getPriority());
					
					diplayProducts.add(productDetails);
				}
			}
		}
		
		Collections.sort(diplayProducts, EssotComparatorFactory.getInstance(EssotComparatorEnum.HOME_PRODUCTS));
		return diplayProducts;
	}
	
	/**
	 * 
	 * @param skuName
	 * @return
	 */
	public ProductDetails getDetailsForProduct(String skuName){
		
		ProductDetails details = new ProductDetails();
		Collection<Object> productSKUNames = new ArrayList<Object>();
		
		productSKUNames.add(skuName);
		
		List<IEssotEntity>  products =  daoFactory.getDAO(EssotDAOEnum.PRODUCT).getFilteredListOnPrimarKey(productSKUNames);
		
		if(products != null && !products.isEmpty()){
			for(IEssotEntity product : products){
				ProductCategoryDetails productDetails = new ProductCategoryDetails();
				
				productDetails.setDescription(((Product)product).getDescription());
				productDetails.setName(((Product)product).getName());
				productDetails.setSkuName(((Product)product).getSkuName());
				productDetails.setPrice(((Product)product).getB2cNowPrice());
				productDetails.setLongDescription(((Product)product).getLongDescription());
				
				List<IEssotEntity> features =  daoFactory.getDAO(EssotDAOEnum.PRODUCT_X_FEATURE).getFilteredListOnPrimarKey(productSKUNames);
				
				if(features != null && !features.isEmpty()){
					for(IEssotEntity feature : features){
						ProductFeatures productFeature = new ProductFeatures();
						productFeature.setFeatureKey(((ProductXFeature)feature).getFeatureKey());
						productFeature.setFeatureValue(((ProductXFeature)feature).getValue());
						
						details.addFeature(productFeature);
					}
				}
				
				List<IEssotEntity> enCodes =  daoFactory.getDAO(EssotDAOEnum.PRODUCT_X_EN_CODE).getFilteredListOnPrimarKey(productSKUNames);
				
				if(enCodes != null && !enCodes.isEmpty()){
					for(IEssotEntity enCode : enCodes){
						ProductEnCodes productEncode = new ProductEnCodes();
						productEncode.setEnCode(((ProductXENCode)enCode).getEnCode());
						productEncode.setProdColor(((ProductXENCode)enCode).getProdColor());
						
						details.addEnCode(productEncode);
					}
					
					details.setDefaultEnCode(((ProductXENCode)enCodes.get(0)).getEnCode());					
				}

				
				List<IEssotEntity> specs =  daoFactory.getDAO(EssotDAOEnum.PRODUCT_X_TECH_SPEC).getFilteredListOnPrimarKey(productSKUNames);
				
				if(specs != null && !specs.isEmpty()){
					for(IEssotEntity spec : specs){
						ProductTechSpecs productSpecs = new ProductTechSpecs();
						productSpecs.setTechKey(((ProductXTechSpec)spec).getTechKey());
						productSpecs.setTechValue(((ProductXTechSpec)spec).getValue());
						
						details.addSpec(productSpecs);
					}
				}
				
				details.setProductDetails(productDetails);
			}
			
			List<IEssotEntity> releatedSKUs = daoFactory.getDAO(EssotDAOEnum.RELATED_SKU).getFilteredListOnPrimarKey(productSKUNames);
			
			if(releatedSKUs != null & !releatedSKUs.isEmpty()){
				Collection<Object> relSKUs = new ArrayList<Object>();
				for(IEssotEntity relSKU : releatedSKUs){
					relSKUs.add(((RelatedSKUs)relSKU).getRelatedsku());
				}
				details.setRelatedskus(getRelatedProdDetails(relSKUs));
			}
					
		}
		return details;
	}
	
	/**
	 * 
	 * @param sku
	 * @return
	 */
	public String getProductName(String sku){
		String name = "";
		IEssotEntity  products =  daoFactory.getDAO(EssotDAOEnum.PRODUCT).findEntityById(sku);
		if(products != null){
		
			Product product = (Product)products;
			name = product.getName();
		}
		
		return name;
	}
	
	/**
	 * 
	 * @param relSKUs
	 * @return
	 */
	public List<RelatedProductDetails> getRelatedProdDetails(Collection<Object> relSKUs){
		
		List<IEssotEntity>  products =  daoFactory.getDAO(EssotDAOEnum.PRODUCT).getFilteredListOnPrimarKey(relSKUs);
		List<RelatedProductDetails> relProdList = new ArrayList<RelatedProductDetails>();
		if(products != null && !products.isEmpty()){
			for(IEssotEntity prod : products){
				RelatedProductDetails relProdDetails = new RelatedProductDetails();
				relProdDetails.setRelSKU(((Product)prod).getSkuName());
				relProdDetails.setRelProdName(((Product)prod).getName());
				relProdDetails.setRelProdShortDesc(((Product)prod).getDescription());
				relProdDetails.setRelProdPrice(((Product)prod).getB2cNowPrice());
				relProdList.add(relProdDetails);
			}
		}
		
		return relProdList;
	}
}
