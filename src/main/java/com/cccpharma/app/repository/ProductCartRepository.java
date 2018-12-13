package com.cccpharma.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.ProductCart;

@Repository
public interface ProductCartRepository extends CrudRepository<ProductCart, Long> {

}
