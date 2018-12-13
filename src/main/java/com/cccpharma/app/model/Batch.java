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
@Table(name = "product")
public class Batch  implements Serializable {
	private static final long serialVersionUID = 4262619552581632044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name = "productId")
	private long productId;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "shelfLife")
	private Date shelfLife;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(Date shelfLife) {
		this.shelfLife = shelfLife;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((shelfLife == null) ? 0 : shelfLife.hashCode());
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
		Batch other = (Batch) obj;
		if (id != other.id)
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (shelfLife == null) {
			if (other.shelfLife != null)
				return false;
		} else if (!shelfLife.equals(other.shelfLife))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Batch [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", shelfLife=" + shelfLife
				+ "]";
	}
}
