package com.cccpharma.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cccpharma.app.util.DiscountCategory;
import com.cccpharma.app.util.ProductCategory;
import com.cccpharma.app.util.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 4262619552581632044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "bar_code", nullable = false, unique = true)
	private String barCode;
	
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ProductStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
	private ProductCategory category;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Batch> batches;
	
	@OneToMany(mappedBy = "sales")
	private List<Sale> sales;

	private Integer stock;
	
	private Integer expiredStock;
	
	private DiscountCategory discount = DiscountCategory.NO;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public Integer getExpiredStock() {
		return expiredStock;
	}

	public void setStockData() {
		this.stock = 0;
		this.expiredStock = 0;
		for (Batch batch : batches) {
			if (batch.isExpired()) {
				this.expiredStock += batch.getQuantity();
			} else {
				this.stock += batch.getQuantity();
			}
		}
		if (this.stock == 0) {
			this.status = ProductStatus.UNAVAILABLE;
		} else {
			this.status = ProductStatus.AVAILABLE;
		}
	}

	public double getListingPrice() {
		return getPrice();
	}
	
	public DiscountCategory getDiscount() {
		return discount;
	}

	public void setDiscount(DiscountCategory discount) {
		this.discount = discount;
	}

	public double getSellingPrice() {
		if (discount == null)
			return getPrice();
		
		return getPrice() - getPrice()*discount.getDiscount();
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
		setStockData();
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
	
	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", barCode=" + barCode + ", manufacturer=" + manufacturer
				+ ", status=" + status + ", category=" + category + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (category != other.category)
			return false;
		if (id != other.id)
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
