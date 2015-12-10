package com.myproject.testyourself.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myproject.testyourself.dao.User_Details_dao;
import com.myproject.testyourself.model.User_Details;

@Repository
public class User_Details_dao_impl implements User_Details_dao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void addUser(User_Details user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void editUser(User_Details user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public User_Details getUser(long user_id) {
		return (User_Details)session.getCurrentSession().get(User_Details.class, user_id);
	}

	@Override
	public List getAllUser() {
		return session.getCurrentSession().createQuery("from User_Details").list();
	}

}
