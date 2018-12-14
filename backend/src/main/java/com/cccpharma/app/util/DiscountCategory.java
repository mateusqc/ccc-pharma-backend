package com.cccpharma.app.util;

public enum DiscountCategory {
	SUPER("Super Desconto"),
	GREAT("Ótimo Desconto"),
	GOOD("Bom Desconto"),
	NO("Sem Desconto");
	
	
	private String DiscountCategory;
	
	DiscountCategory(String category) {
		this.DiscountCategory = category;
	}
	
	public String getDiscountCategory() {
		return this.DiscountCategory;
	}
	
	public double getDiscount() {
		switch(this) {
			case SUPER:	return 0.5;
			case GREAT: return 0.25;
			case GOOD: 	return 0.1;
			case NO:	return 0;
			default: 	return 0.2;
		}
	}
	
}
