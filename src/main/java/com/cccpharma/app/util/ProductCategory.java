package com.cccpharma.app.util;

public enum ProductCategory {
	MEDICINE("Medicamento"),
	HYGIENE("Higiene"),
	COSMETIC("Cosmético"),
	FOOD("Alimento");
	
	private String productCategory;
	
	ProductCategory(String category) {
		this.productCategory = category;
	}
	
	public String getProductCategory() {
		return this.productCategory;
	}
}
