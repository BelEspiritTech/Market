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

	public int compare(Object obj1, Object obj2) {
		if(obj1 == null || obj2 == null){
			return 0;
		}else if(!(obj1 instanceof ProductCategoryDetails) || !(obj2 instanceof ProductCategoryDetails) ){
			return 0;
		}
		else{
			return ((ProductCategoryDetails)obj1).getPriority().compareTo(((ProductCategoryDetails)obj2).getPriority());
		}
	}

}
