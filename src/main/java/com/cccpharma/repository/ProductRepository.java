package com.cccpharma.repository;

import org.springframework.data.repository.CrudRepository;

import com.cccpharma.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
