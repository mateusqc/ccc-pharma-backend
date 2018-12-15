package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.User;
import com.cccpharma.app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		Iterable<User> userData = userRepository.findAll();
		userData.forEach(list::add);
		
		return list;
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
