package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Customer;

public interface ICustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int theId);
	
	public Customer getCustomerByEmail(String theEmail);

	public void deleteCustomer(int theId);
	
}
