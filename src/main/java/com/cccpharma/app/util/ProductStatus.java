package com.cccpharma.app.util;

public enum ProductStatus {
	AVAILABLE("Disponível"),
	SOLD("Vendido"); 

	private String productStatus;
	
	ProductStatus(String status) {
		this.productStatus = status;
	}
	
	public String getProductCategory() {
		return this.productStatus;
	}
}
