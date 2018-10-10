package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.User;

/* UserService is an interface which its implementation will delegates the calls 
 * from the UserService to the actual UserDAO layer. */
public interface IUserService {
	
	public List<User> getUsers();

	public void saveUser(User theCustomer);

	public User getUser(int theId);

	public void deleteUser(int theId);
}
