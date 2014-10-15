package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResponse {
	
	private List<ProductDetails> result;

	public List<ProductDetails> getResult() {
		return result;
	}

	public void setResult(List<ProductDetails> result) {
		this.result = result;
	}
	
}
