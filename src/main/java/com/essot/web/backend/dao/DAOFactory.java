package com.essot.web.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.concrete.RelatedSKUDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.backend.entity.concrete.ProductXENCode;
import com.essot.web.backend.entity.concrete.ProductXFeature;
import com.essot.web.backend.entity.concrete.ProductXTechSpec;
import com.essot.web.util.EssotDAOEnum;

public class DAOFactory {
	
	@Autowired
	private IEssotDAO productDAO;
	
	@Autowired
	private IEssotDAO productCategoryDAO;
	
	@Autowired
	private IEssotDAO productCategoryXProductDAO;
	
	@Autowired
	private IEssotDAO productFeatureDAO;
	
	@Autowired
	private IEssotDAO productEnCodeDAO;
	
	@Autowired
	private IEssotDAO productTechSpecDAO;
	
	@Autowired
	private IEssotDAO relatedSKUDAO;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public IEssotDAO getDAOClass(IEssotEntity entity){
		if(entity instanceof Product){
			return productDAO;
		}else if (entity instanceof ProductCategory){
			return productCategoryDAO;
		}else if (entity instanceof ProductCategoryXProduct){
			return productCategoryXProductDAO;
		}else if (entity instanceof ProductXENCode){
			return productEnCodeDAO;
		}else if (entity instanceof ProductXFeature){
			return productFeatureDAO;
		}else if (entity instanceof ProductXTechSpec){
			return productTechSpecDAO;
		}else if (entity instanceof RelatedSKUDAO){
			return relatedSKUDAO;	
		}else {
		
			return null;
		}
	}
	
	/**
	 * 
	 * @param requestedDAO
	 * @return
	 */
	public IEssotDAO getDAOClassByDAOEnum(EssotDAOEnum requestedDAO){
		
		switch(requestedDAO){
		case PRODUCT_CATEGORY :
			return productCategoryDAO;
		case PRODUCT_CATEGORY_X_PRODUCT :
			return productCategoryXProductDAO;
		case PRODUCT :
			return productDAO;
		case PRODUCT_X_EN_CODE :
			return productEnCodeDAO;
		case PRODUCT_X_FEATURE :
			return productFeatureDAO;
		case PRODUCT_X_TECH_SPEC :
			return productTechSpecDAO;
		case RELATED_SKU :
			return relatedSKUDAO;
		default :
			return null;
		}		
	}
}
