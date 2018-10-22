package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;

public interface ICustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int theId);
	
	public Customer getCustomerByEmail(String theCustomerEmail) throws CustomerNotFoundException;;
	
	public Customer customerWithMailExist(String theCustomerEmail);

	public void deleteCustomer(int theId);
}
