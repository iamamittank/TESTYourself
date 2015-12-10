package com.myproject.testyourself.services;

import java.util.List;

import com.myproject.testyourself.model.User_Details;

public interface User_Details_service {

	public void addUser(User_Details user);
	public void editUser(User_Details user);
	public User_Details getUser(long user_id);
	public List getAllUser();
	
}
