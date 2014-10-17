package com.essot.web.util;

public enum EssotDAOEnum {
	/**
	 * DAO FUNCTION ON Product Category.
	 */
	PRODUCT_CATEGORY("productCategory"),
	
	/**
	 * DAO FUNCTION ON Product Category By Product.
	 */
	PRODUCT_CATEGORY_X_PRODUCT("productCategoryXProduct"),
	
	/**
	 * DAO FUNCTION ON Product.
	 */
	PRODUCT("product"),
	
	/**
	 * DAO FUNCTION ON Product By En Code.
	 */
	PRODUCT_X_EN_CODE("productXEnCode"),
	
	/**
	 * DAO FUNCTION ON Product By Feature.
	 */
	PRODUCT_X_FEATURE("productXFeature"),
	
	/**
	 * DAO FUNCTION ON Product By Tech Spec.
	 */
	PRODUCT_X_TECH_SPEC("productXTechSpec"),
	
	/**
	 * DAO FUNCTION ON Related SKU.
	 */
	RELATED_SKU("relatedSKU");
	
	/**
	 * value of the enum constant.
	 */
	private final String value;

	/**
	 * Custom Constructor.
	 * 
	 * @param value
	 *            the value
	 */
	private EssotDAOEnum(final String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value.
	 */
	public String value() {
		return this.value;
	}
}
