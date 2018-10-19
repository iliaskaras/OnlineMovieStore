package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;

@Service
public class CustomerService implements ICustomerService{
	
	    /* inject the customer dao */
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
			
			Customer theCustomer = customerDAO.getCustomerById(theCustomerID);
			
			if (theCustomer == null)
				throw new CustomerNotFoundException("Customer with id :"+theCustomerID+", not found!"); 
			
			return theCustomer;
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = {CustomerNotFoundException.class})
		public Customer getCustomerByEmail(String theCustomerEmail) throws CustomerNotFoundException {
			
			Customer theCustomer = customerDAO.getCustomerByEmail(theCustomerEmail);
			
			if (theCustomer == null)
				throw new CustomerNotFoundException("Customer with email :"+theCustomerEmail+", not found!"); 
			
			return theCustomer;
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = {CustomerWithEmailExistException.class})
		public Customer customerWithMailExist(String theCustomerEmail) throws CustomerWithEmailExistException  {
			
			Customer theCustomer = customerDAO.getCustomerByEmail(theCustomerEmail);
			
			if (theCustomer != null)
				throw new CustomerWithEmailExistException("Customer with email :"+theCustomerEmail+", already exist! Please type another mail. "); 
			
			return theCustomer;
		}

		@Override
		@Transactional
		public void deleteCustomer(int theCustomerID) {
			
			customerDAO.deleteCustomer(theCustomerID);
			
		}
	
}
