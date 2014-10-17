package com.essot.web.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;

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
	 * @param requestedDAO
	 * @return
	 */
	public IEssotDAO getDAO(EssotDAOEnum requestedDAO){
		
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
