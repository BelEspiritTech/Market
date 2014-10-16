package com.essot.web.util;


public class EssotComparatorFactory {
	public static IEssotComparator getInstance(EssotComparatorEnum bizCase){
		switch(bizCase){
			case HOME_PRODUCTS : return new HomeProductComparator();
			default : return null;
		}
	}
}
