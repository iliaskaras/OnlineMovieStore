package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.AccountRegistrationRestExceptionHandler;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerWithEmailExistException;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	List<Customer> theCustomers;
	
	@Autowired
	ICustomerService customerService;
	
	/* return a list of customers
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/customers/all/")
	public List<Customer> getCustomers(){
	
		return customerService.getCustomers();
	}
	

	/* GET method to get a certain customer
	 * Binding of path variable customerEmail to retrieve a single user 
	 * add mapping for GET /customers/{customerEmail}
	 * 
	 * @return on success : the customer with mail customerEmail
	 * @return on failure : CustomerNotFoundException */
	@GetMapping("/customer/email=/{customerEmail}")
	public Customer getCustomer(@PathVariable String customerEmail) throws CustomerNotFoundException {
		
		Customer theCustomer = customerService.getCustomerByEmail(customerEmail);
	
		return theCustomer;
	}
	
	/* returns "Customer with email :"+customerEmail+", not found!" if there are not any customers with the given mail,
	 * or throws CustomerWithEmailExistException. */
	@GetMapping("/customerExistance/email=/{customerEmail}")
	public String customerWithMailExist(@PathVariable String customerEmail) throws CustomerWithEmailExistException {
		
		customerService.customerWithMailExist(customerEmail);
		
		return "Customer with email :"+customerEmail+", not found!";
	}
	
	
	/* POST method to create a new customer
	 * add new customer, mapping for POST / customers 
	 * @RequestBody to access the request body as a given POJO */
	@PostMapping("/customers/add")
	public Customer addCustomer(@RequestBody Customer theCustomer){
		
		/* Id of zero means DAO will insert a new customer, 
		 * just in case a non zero id passed into the method */
		theCustomer.setId(0); 
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	/* PUT method to update an existing customer
	 * update an existing customer, mapping for PUT / customers 
	 * @RequestBody to access the request body as a given POJO 
	 * 
	 * The given pojo must have the field id set, so saveCustomer will
	 * update the existing customer with the specified id 
	 * 
	 * @return an echo of the updated customer */
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer){
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	/* DELETE method to delete an existing customer
	 * delete an existing customer, mapping for DELETE / customers 
	 * 
	 * The given pojo must have the field id set, so deleteCustomer will
	 * delete the existing customer with the specified id 
	 * 
	 * @return on fail : CustomerNotFoundException if the customer doesn't exist + 404 error code
	 * @return on success : Customer with id: "+customerId+", deleted succesfully! */
	@DeleteMapping("/customers/id=/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		
		customerService.getCustomerById(customerId);
		
		customerService.deleteCustomer(customerId);
		
		return "Customer with id: "+customerId+", deleted succesfully!";
		
	}
	


}
