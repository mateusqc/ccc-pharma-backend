package com.cccpharma.app.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productCart")
public class ProductCart implements Serializable{
	@Column(name = "cartId")
	private long cartId;
	@Column(name = "productId")
	private long productId;
	@Column(name = "quantity")
	private long quantity;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cartId ^ (cartId >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
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
		ProductCart other = (ProductCart) obj;
		if (cartId != other.cartId)
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductCart [cartId=" + cartId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	
	
}
