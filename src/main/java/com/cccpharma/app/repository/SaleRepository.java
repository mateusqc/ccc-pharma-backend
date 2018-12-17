package com.cccpharma.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.model.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long>{
	@Query("SELECT s FROM Sale s WHERE :product IN s.product")
	public List<Sale> findByProduct(@Param("product") Product product);
}
