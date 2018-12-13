package com.cccpharma.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
