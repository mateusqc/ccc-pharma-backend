package com.cccpharma.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.util.ProductCategory;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	@Query("SELECT p FROM Product p WHERE p.category = :category")
	public List<Product> findByCategory(@Param("category") ProductCategory category);
	
	@Query("SELECT p FROM Product p WHERE bar_code = :barcode")
	public Product findByBarCode(@Param("barcode") String barCode);
}
