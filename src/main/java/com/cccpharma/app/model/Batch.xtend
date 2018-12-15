package com.cccpharma.app.model

import java.io.Serializable
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity @Table(name="batch") class Batch implements Serializable {
	static final long serialVersionUID = 4262619552581632044L
	@Id @GeneratedValue(strategy=GenerationType::SEQUENCE) long id
	@Column(name="product_id", nullable=false) long productId
	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "product", nullable = false)
	// private Product product;
	@Column(name="quantity", nullable=false) int quantity
	@Column(name="expiration_date") @Temporal(TemporalType::DATE) Date expirationDate

	def long getId() {
		return id
	}

	def void setId(long id) {
		this.id = id
	}

	def long getProductId() {
		return productId
	}

	def void setProductId(long productId) {
		this.productId = productId
	}

	def int getQuantity() {
		return quantity
	}

	def void setQuantity(int quantity) {
		this.quantity = quantity
	}

	def Date getExpirationDate() {
		return expirationDate
	}

	def void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate
	}

	override int hashCode() {
		val int prime = 31
		var int result = 1
		result = prime * result + (if((expirationDate === null)) 0 else expirationDate.hashCode() )
		result = prime * result + ((id.bitwiseXor((id >>> 32))) as int)
		result = prime * result + ((productId.bitwiseXor((productId >>> 32))) as int)
		result = prime * result + quantity
		return result
	}

	override boolean equals(Object obj) {
		if(this === obj) return true
		if(obj === null) return false
		if(getClass() !== obj.getClass()) return false
		var Batch other = (obj as Batch)
		if (expirationDate === null) {
			if(other.expirationDate !== null) return false
		} else if(!expirationDate.equals(other.expirationDate)) return false
		if(id !== other.id) return false
		if(productId !== other.productId) return false
		if(quantity !== other.quantity) return false
		return true
	}

	override String toString() {
		return '''Batch [id=«id», productId=«productId», quantity=«quantity», expirationDate=«expirationDate»]'''.
			toString
	}
}
