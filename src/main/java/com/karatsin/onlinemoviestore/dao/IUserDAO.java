package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.User;

public interface IUserDAO {

	public List<User> getUsers();

	public void saveUser(User theCustomer);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
}
