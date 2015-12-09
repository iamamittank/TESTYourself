package com.myproject.testyourself.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User_Details {

	@Id
	private float user_id;

	private String name;

	private String email;

	public float getUser_id() {
		return user_id;
	}

	public void setUser_id(float user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
