package com.essot.web.util;


public class EssotComparatorFactory {
	public static IEssotComparator getInstance(EssotComparatorEnum bizCase){
		switch(bizCase){
			case HOME_PRODUCTS : return new HomeProductComparator();
			case GET_MENU: return new GetMenuComparator();
			default : return null;
		}
	}
}
