package com.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@Column(unique=true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String Password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Users(long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		Password = password;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

}
