package com.cccpharma.app.model;

import java.util.Map;

public class Cart {

	private String userToken;
	
	private Map<Long, Integer> products;

	public Cart(String userToken, Map<Long, Integer> products) {
		this.userToken = userToken;
		this.products = products;
	}
	
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Map<Long, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Long, Integer> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [userToken=" + userToken + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((userToken == null) ? 0 : userToken.hashCode());
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
		Cart other = (Cart) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (userToken == null) {
			if (other.userToken != null)
				return false;
		} else if (!userToken.equals(other.userToken))
			return false;
		return true;
	}
	
	
}
