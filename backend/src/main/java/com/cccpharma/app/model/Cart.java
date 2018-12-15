package com.cccpharma.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable{
	private static final long serialVersionUID = 4262619552581632044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_cart_owner", nullable = false)
//	private User user;
	@Column(nullable = false, unique = true)
	private String userCookie;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "products")
	private List<Product> products;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserCookie() {
		return userCookie;
	}
	public void setUserCookie(String userCookie) {
		this.userCookie = userCookie;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((userCookie == null) ? 0 : userCookie.hashCode());
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
		if (id != other.id)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (userCookie == null) {
			if (other.userCookie != null)
				return false;
		} else if (!userCookie.equals(other.userCookie))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userCookie=" + userCookie + ", products=" + products + "]";
	}	

}
