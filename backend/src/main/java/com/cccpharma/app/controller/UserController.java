package com.cccpharma.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.User;
import com.cccpharma.app.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/")
	public List<User> getUsuarios() {
		System.out.println("GETTING ALL USERS...");
		return userService.getAllUsers();
	}
	
}
