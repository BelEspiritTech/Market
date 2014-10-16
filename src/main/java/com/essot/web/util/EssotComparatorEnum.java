package com.essot.web.util;

public enum EssotComparatorEnum {
	/**
	 * Business Case HOME Products.
	 */
	HOME_PRODUCTS("HomeProducts");
	
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
	private EssotComparatorEnum(final String value) {
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
