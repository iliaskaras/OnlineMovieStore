package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.entity.ValidatedPhone;
import com.karatsin.onlinemoviestore.enums.UriPaths;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerException;

@RestController
@RequestMapping("/api")
public class AccountRegistrationRestController {
	List<Account> theAccounts;
	
	
	@Autowired
	IAccountService accountService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IPaymentMethodService paymentMethodService;
	
	private final String prefixUri = "http://localhost:8080/OnlineMovieStore/api";
	private RestTemplate restTemplate;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void initVariables() {	
		restTemplate = new RestTemplate();
	}
	
	/* endpoint for "/users" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/accounts")
	public List<Account> getAccounts(){
	
		return accountService.getAccounts();
	}
	
	/* GET method to get a certain customer
	 * Binding of path variable customerID to retrieve a single user 
	 * add mapping for GET /customers/{customerId}
	 * 
	 * @return the new created customer */
	@GetMapping("/accounts/{accountId}")
	public Account getAccount(@PathVariable int accountId) {
		
		Account theAccount = accountService.getAccount(accountId);
		
		if (theAccount == null)
			throw new CustomerException("Account with id :"+accountId+", not found!"); 
		
		return theAccount;
	}
	
	/* POST method to create a new customer
	 * add new customer, mapping for POST / customers 
	 * @RequestBody to access the request body as a given POJO */
	@PostMapping("/register")
	public Account createAccount(@RequestBody Account theAccount){
		
		/* Id of zero means DAO will insert a new customer, 
		 * just in case a non zero id passed into the method */
		theAccount.setId(0); 
		
		accountService.saveAccount(theAccount);
		
		return theAccount;
	}
	
	/* PUT method to update an existing customer
	 * update an existing customer, mapping for PUT / customers 
	 * @RequestBody to access the request body as a given POJO 
	 * 
	 * The given pojo must have the field id set, so saveCustomer will
	 * update the existing customer with the specified id 
	 * 
	 * @return an echo of the updated customer */
	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account theAccount){
		
		accountService.saveAccount(theAccount);
		
		return theAccount;
	}
	
	/* DELETE method to delete an existing customer
	 * delete an existing customer, mapping for DELETE / customers 
	 * @RequestBody to access the request body as a given POJO 
	 * 
	 * The given pojo must have the field id set, so saveCustomer will
	 * update the existing customer with the specified id 
	 * 
	 * @return an echo of the updated customer */
	@DeleteMapping("/accounts/{accountId}")
	public String deleteCustomer(@PathVariable int accountId){
		
		Account theAccount = accountService.getAccount(accountId);
		
		if (theAccount == null)
			throw new CustomerException("Account with id :"+accountId+", not found!"); 
		
		accountService.deleteAccount(accountId);
		
		return "Account with id"+accountId+", deleted succesfully!";
		
	}
	
	@PostMapping("/accounts/delete/{accountId}")
	public String deleteCustomerPost(@PathVariable int accountId){
		
		Account theAccount = accountService.getAccount(accountId);
		
		if (theAccount == null)
			throw new CustomerException("Account with id :"+accountId+", not found!"); 
		
		accountService.deleteAccount(accountId);
		
		return "Account with id"+accountId+", deleted succesfully!";
		
	}
	
	@PostMapping(value = "/account/registration")
	public ModelAndView registerUserAccount
	      (
	       @Valid @ModelAttribute("registrationWrapper") RegistrationWrapper registrationWrapperDTO,
	       BindingResult registrationWrapperBindingResult,
	       WebRequest request, Errors errors){    

		/** If there are errors in the registration form, return the same UI with warning errors  */
		if(registrationWrapperBindingResult.hasErrors()) {
          
            return new ModelAndView("registration_form");
        }
		
		customerService.customerMailExist(registrationWrapperDTO.getCustomer().getEmail());
		
		PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodByType(registrationWrapperDTO.getPaymentMethod().getPaymentType());
	
		registrationWrapperDTO.getAccount().setPaymentMethodId(paymentMethod.getId());
		registrationWrapperDTO.getAccount().setCustomer(registrationWrapperDTO.getCustomer());
		
	
		accountService.saveAccount(registrationWrapperDTO.getAccount());
		customerService.saveCustomer(registrationWrapperDTO.getCustomer());


	    return new ModelAndView("home_logged_in");
	   
	}
	
	/* When the user will request /account/registration, this method will get called and
	 * the following objects : customerDTO, paymentMethodDTO and accountDTO will back 
	 * up the filling registration form. */
	@GetMapping(value = "/account/registration")
	public ModelAndView showRegistrationForm(WebRequest request, Model model) {
		RegistrationWrapper registrationWrapper = new RegistrationWrapper();
		
	    model.addAttribute("registrationWrapper", registrationWrapper);
	    
	    return new ModelAndView("registration_form", (Map<String, ?>) model);

	}	

	@ModelAttribute("paymentMethodList")
	public ArrayList<String> getPaymentMethodList() {
		
		StringBuilder paymentMethodUri = new StringBuilder()
				.append(UriPaths.GET_PAYMENT_METHODS.getAction());
		
		PaymentMethod[] response = restTemplate.getForObject(paymentMethodUri.toString(), PaymentMethod[].class);
		ArrayList<String> paymentMethodList = new ArrayList<String>();

		for(PaymentMethod paymentMethod : response) {
			 paymentMethodList.add(paymentMethod.getPaymentType());
		}
		
	    return paymentMethodList;
	}
	


}
