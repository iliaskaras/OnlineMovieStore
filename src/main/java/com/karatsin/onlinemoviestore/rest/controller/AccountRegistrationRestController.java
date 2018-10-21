package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.enums.UriPaths;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.mysql.jdbc.log.Log;

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
	
	/* GET method to get a certain account
	 * Binding of path variable accountId to retrieve a single account 
	 * add mapping for GET /customers/{customerId}
	 * 
	 * @return the new created customer */
	@GetMapping("/accounts/id=/{accountId}")
	public Account getAccount(@PathVariable int accountId) {
		
		Account theAccount = accountService.getAccountById(accountId);
		
		return theAccount;
	}
	
	/* PUT method to update an existing account
	 * update an existing account, mapping for PUT / accounts 
	 * 
	 * The given pojo must have the field id set, so saveAccount will
	 * update the existing account with the specified id 
	 * 
	 * @return an echo of the updated account */
	@PutMapping("/accounts/save/")
	public Account updateAccount(@RequestBody Account theAccount){
		
		accountService.saveAccount(theAccount);
		
		return theAccount;
	}
	
	/* DELETE method to delete an existing account
	 * delete an existing account, mapping for DELETE / accounts 
	 * 
	 * The given pojo must have the field id set, so saveAccount will
	 * update the existing account with the specified id 
	 * 
	 * @return an echo of the updated account */
	@DeleteMapping("/account/delete/")
	public String deleteAccount(@RequestParam("accountId")  int accountId){
		
		Account theAccount = accountService.getAccountById(accountId);
		
		customerService.deleteCustomer(theAccount.getCustomer().getId());
		accountService.deleteAccount(accountId);
		
		return "Account with id: "+accountId+" and Customer with id: "+theAccount.getCustomer().getId()+", deleted succesfully!";
		
	}
	
	@PostMapping(value = "/account/registration")
	public ModelAndView registerUserAccount
	      (@Valid @ModelAttribute("registrationWrapper") RegistrationWrapper registrationWrapperDTO,
	       BindingResult registrationWrapperBindingResult,
	       WebRequest request, Errors errors, Model model){    

		/** If there are errors in the registration form, return the same UI with warning errors  */
		if(registrationWrapperBindingResult.hasErrors()) {
          
            return new ModelAndView("registration_form");
        }
		

		try {
			Customer customer = customerService.customerWithMailExist(registrationWrapperDTO.getCustomer().getEmail());
		} catch (CustomerWithEmailExistException exception) {
			exception.printStackTrace();
			registrationWrapperBindingResult.rejectValue("customer.email", "errors.signup.email","Email address is already in use.");
		
			return new ModelAndView("registration_form");
		} 
		
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
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/account/registration")
	public ModelAndView showRegistrationForm(WebRequest request, Model model) {
		RegistrationWrapper registrationWrapper = new RegistrationWrapper();
		
	    model.addAttribute("registrationWrapper", registrationWrapper);
	    
	    return new ModelAndView("registration_form", (Map<String, ?>) model);

	}	

	@ModelAttribute("paymentMethodList")
	public ArrayList<String> getPaymentMethodList() {
		List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();
		ArrayList<String> paymentMethodList = new ArrayList<String>();

		for(PaymentMethod paymentMethod : paymentMethods) {
			 paymentMethodList.add(paymentMethod.getPaymentType());
		}
		
	    return paymentMethodList;
	}



}
