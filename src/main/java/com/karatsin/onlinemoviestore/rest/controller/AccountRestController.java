package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.services.IAccountService;
import com.karatsin.onlinemoviestore.rest.services.ICustomerService;
import com.karatsin.onlinemoviestore.rest.services.IPaymentMethodService;
import com.karatsin.onlinemoviestore.rest.services.ITransactionService;
import com.karatsin.onlinemoviestore.session.UserSession;


/* 
 * AccountRegistrationRestController, the main RestController for AccountDTO,
 * 
 * In addition, connects the Account Service with the Customer one, to register a new account
 * 	and unregister (delete) an existed account when there are not any unpaid orders. 
 * */
@RestController
@RequestMapping("/api")
public class AccountRestController {
	List<Account> theAccounts;
	
	
	@Autowired
	IAccountService accountService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IPaymentMethodService paymentMethodService;
	@Autowired
	ITransactionService transactionService;

	/* registerUserAccount is the method that called when a new customer is registering to our database.
	 * 
	 * If there are any form errors, the appropriate warning are shown to the registration form. 
	 * 
	 * If there are not any errors, then we are searching if a customer already exists with with that email,
	 * if not then the registration is proceding. */
	@PostMapping(value = "/account/registration")
	public ModelAndView registerUserAccount
	      (@Valid @ModelAttribute("registrationWrapper") RegistrationWrapper registrationWrapperDTO,
	       BindingResult registrationWrapperBindingResult,
	       WebRequest request, Errors errors, Model model, HttpSession session){    

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

		session.setAttribute("accountLoggedInId", registrationWrapperDTO.getAccount().getId()); 
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
	
	/* endpoint for "/accounts" - return a list of accounts
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/accounts/all/")
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
	
	/*
	 * getDeleteAccount is called when a logged in customer selects to delete the account. 
	 * 
	 * An account to be deleted successfully it needs to not have any unpaid orders
	 * 
	 * When the account is deleted, the customer entry is deleted also and all the transactions 
	 * saved for that customer. 
	 * */
	@GetMapping("/account/loggedin/delete/")
	public String getDeleteAccount(HttpSession session){
		int accountId = UserSession.getCustomerId(session);
		
		Account theAccount = accountService.getAccountById(accountId);
		
		boolean hasUnpaidTransactions = transactionService.accountHasUnpaidTransactions(accountId);
		
		if(hasUnpaidTransactions)
			return "Account with id: "+accountId+" and Customer with id: "+theAccount.getCustomer().getId()+", had unpaid transactions"
					+ "and and therefore cannot be deleted, please ensure to pay all your open orders!";
		
		transactionService.deleteTransactionsByAccount(accountId);
		accountService.deleteAccount(accountId);
		customerService.deleteCustomer(theAccount.getCustomer().getId());
		
		
		return "Account with id: "+accountId+" and Customer with id: "+theAccount.getCustomer().getId()+", deleted succesfully"
				+ "and all the transactions assosiated with that account!";
		
	}
	
	
	/* Loading of the payment method list of the form for the user to select payment method */
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
