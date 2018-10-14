package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Customer;

/* UserService is an interface which its implementation will delegates the calls 
 * from the UserService to the actual UserDAO layer. */
public interface ICustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int theId);
	
	public Customer getCustomerByEmail(String theCustomerEmail);

	public void deleteCustomer(int theId);
}
