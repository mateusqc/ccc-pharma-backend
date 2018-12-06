package com.cccpharma.model;

import javax.persistence.Entity;

@Entity
public class User {
	private long id;
	private String name;
	private String email;
	private String password;
}
