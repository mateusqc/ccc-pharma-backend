package com.cccpharma.app.util;

public enum UserRole {
	ADMIN("Administrador"),
	CLIENT("Cliente"); 

	private String userRole;
	
	UserRole(String role) {
		this.userRole = role;
	}
	
	public String getUserRole() {
		return this.userRole;
	}
}
