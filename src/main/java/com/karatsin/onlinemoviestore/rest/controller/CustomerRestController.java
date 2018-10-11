package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomException;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerRestExceptionHandler;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	List<Customer> theCustomers;
	
	@Autowired
	ICustomerService customerService;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void loadData() {	

	}
	
	/* endpoint for "/users" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
	
		return customerService.getCustomers();
	}
	
	/* Binding of path variable customerID to retrieve a single user 
	   add mapping for GET /customers/{customerId} */
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if (theCustomer == null)
			throw new CustomException("Customer with id :"+customerId+", not found!"); 
		
		return theCustomer;
	}
	
	/* add new customer, mapping for POST / customers 
	 * @RequestBody to access the request body as a given POJO */
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer){
		
		// Id of zero means DAO will insert a new customer, just in case a non zero id passed into the method
		theCustomer.setId(0); 
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	

}
