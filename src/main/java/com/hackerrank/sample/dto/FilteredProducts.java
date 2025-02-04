package com.hackerrank.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilteredProducts {
	

	

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	private String BarCode;

	private int price;
	
	public FilteredProducts(String a, int price) {
		BarCode = a;
		this.price = price;
	}


}
