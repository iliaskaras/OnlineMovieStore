package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerException;

@Service
public class CustomerService implements ICustomerService{
	
	    /* inject the user dao */
		@Autowired
		private ICustomerDAO customerDAO;
		
		@Override
		@Transactional
		public List<Customer> getCustomers() {
			return customerDAO.getCustomers();
		}

		@Override
		@Transactional
		public void saveCustomer(Customer theCustomer) {

			customerDAO.saveCustomer(theCustomer);
		}

		@Override
		@Transactional
		public Customer getCustomerById(int theCustomerID) {
			
			return customerDAO.getCustomerById(theCustomerID);
		}
		
		@Override
		@Transactional
		public Customer getCustomerByEmail(String theCustomerEmail) {
			
//			return customerDAO.getCustomerByEmail(theCustomerEmail);
			Customer theCustomer = customerDAO.getCustomerByEmail(theCustomerEmail);
			
			if (theCustomer == null)
				throw new CustomerException("Customer with email :"+theCustomerEmail+", not found!"); 
			
			return theCustomer;
		}

		@Override
		@Transactional
		public void deleteCustomer(int theCustomerID) {
			
			customerDAO.deleteCustomer(theCustomerID);
		}
	
}
