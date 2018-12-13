package com.cccpharma.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable{
	private static final long serialVersionUID = 4262619552581632044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_cart_owner", nullable = false)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			targetEntity = ProductCart.class,
			mappedBy = "cart")
	private List<ProductCart> productsCart;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<ProductCart> getProductsCart() {
		return productsCart;
	}
	public void setProductsCart(List<ProductCart> productsCart) {
		this.productsCart = productsCart;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((productsCart == null) ? 0 : productsCart.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (productsCart == null) {
			if (other.productsCart != null)
				return false;
		} else if (!productsCart.equals(other.productsCart))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", productsCart=" + productsCart + "]";
	}	

}
