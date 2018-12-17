package com.cccpharma.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Discount;
import com.cccpharma.app.util.ProductCategory;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, ProductCategory>{
	
}
