package com.cccpharma.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 4262619552581632044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "bar_code")
	private String barCode;
	@Column(name = "manufacturer")
	private String manufacturer;
	@Column(name = "status")
	private String status;
	@Column(name = "category")
	private String category;
	
	public Product() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBarCode() {
		return barCode;
	}
	
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", barCode=" + barCode + ", manufacturer=" + manufacturer
				+ ", status=" + status + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Product other = (Product) obj;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
