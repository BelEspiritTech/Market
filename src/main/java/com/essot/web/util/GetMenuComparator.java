/**
 * 
 */
package com.essot.web.util;

import com.essot.web.controller.data.MenuData;

/**
 * @author Administrator
 *
 */
public class GetMenuComparator implements IEssotComparator {

	public int compare(Object obj1, Object obj2) {
		if(obj1 == null || obj2 == null){
			return 0;
		}else if(!(obj1 instanceof MenuData) || !(obj2 instanceof MenuData) ){
			return 0;
		}
		else{
			return ((MenuData)obj1).getParentCategoryID().compareTo(((MenuData)obj2).getParentCategoryID());
		}
	}

}
