package com.cccpharma.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.User;
import com.cccpharma.app.util.UserRole;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.role = :role")
	public List<User> findByRole(@Param("role") UserRole role);
}