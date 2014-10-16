/**
 * 
 */
package com.essot.web.util;

import com.essot.web.controller.data.ProductCategoryDetails;

/**
 * @author Administrator
 *
 */
public class HomeProductComparator implements IEssotComparator {

	public int compare(ProductCategoryDetails o1, ProductCategoryDetails o2) {
		if(o1 == null || o2 == null){
			return 0;
		}else{
			return o1.getPriority().compareTo(o2.getPriority());
		}
	}

}
