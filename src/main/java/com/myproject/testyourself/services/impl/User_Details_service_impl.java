package com.myproject.testyourself.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.testyourself.dao.User_Details_dao;
import com.myproject.testyourself.model.User_Details;
import com.myproject.testyourself.services.User_Details_service;

@Service
public class User_Details_service_impl implements User_Details_service {

	@Autowired
	private User_Details_dao userDao;
	
	@Transactional
	public void addUser(User_Details user) {
		userDao.addUser(user);
	}

	@Transactional
	public void editUser(User_Details user) {
		userDao.editUser(user);
	}

	@Transactional
	public User_Details getUser(long user_id) {
		return userDao.getUser(user_id);
	}

	@Transactional
	public List getAllUser() {
		return userDao.getAllUser();
	}

}
