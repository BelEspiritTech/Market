package com.essot.web.controller.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetProductDetailsResponse {
	
	private ProductDetails details;

	public ProductDetails getDetails() {
		return details;
	}

	public void setDetails(ProductDetails details) {
		this.details = details;
	}	
}
