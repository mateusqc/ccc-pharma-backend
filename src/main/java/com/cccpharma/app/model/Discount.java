package com.cccpharma.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cccpharma.app.util.DiscountCategory;
import com.cccpharma.app.util.ProductCategory;

@Entity
@Table(name = "discount")
public class Discount implements Serializable {
	private static final long serialVersionUID = 4262619552581632044L;

	@Enumerated(EnumType.STRING)
	@Column(name = "discount_category")
	private DiscountCategory discountCategory;
	
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "product_category")
	private ProductCategory productCategory;
	
	public Discount() {}
	
	public Discount(ProductCategory productCategory, DiscountCategory discountCategory) {
		this.discountCategory = discountCategory;
		this.productCategory = productCategory;
	}

	public DiscountCategory getDiscountCategory() {
		return discountCategory;
	}

	public void setDiscountCategory(DiscountCategory discountCategory) {
		this.discountCategory = discountCategory;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountCategory == null) ? 0 : discountCategory.hashCode());
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (discountCategory != other.discountCategory)
			return false;
		if (productCategory != other.productCategory)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discount [discountCategory=" + discountCategory + ", productCategory=" + productCategory + "]";
	}
	
	
	
}
