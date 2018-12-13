package com.cccpharma.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}