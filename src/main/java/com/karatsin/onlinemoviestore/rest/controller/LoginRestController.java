package com.karatsin.onlinemoviestore.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.rest.controller.exception.account.InvalidAccountUsernameException;

@RestController
@RequestMapping("/api")
public class LoginRestController {
	
	@Autowired
	IAccountService accountService;
	@Autowired
	ICustomerService customerService;
	
	
	/* Perform the customer login, checks and validates if there are any form errors, if there are reload 
	 * the page showing them. If not find the account, log in, save the accountLoggedInId and show
	 * logged_in form  */
	@PostMapping(value = "/customer/login")
	public ModelAndView performCustomerLogin
	      (@Valid @ModelAttribute("accountDetails") Account accountDTO,
	       BindingResult accountDetailsBindingResult,
	       WebRequest request, Errors errors, Model model, HttpSession session){    

		
		/** If there are errors in the login form, return the same UI with warning errors  */
		if(accountDetailsBindingResult.hasErrors()) {
          
            return new ModelAndView("login_form");
        }
		
		Account account;
		
		try {
			account = accountService.getAccountByUsername(accountDTO.getUsername());
		} catch (InvalidAccountUsernameException exception) {
			exception.printStackTrace();
			accountDetailsBindingResult.rejectValue("username", "errors.signup.username","Username is not correct.");
		
			return new ModelAndView("login_form");
		} 
		
		if(!validAccountPasswordGiven(account, accountDTO.getPassword())) {
			accountDetailsBindingResult.rejectValue("password", "errors.signup.password","Password is not correct.");
			
			return new ModelAndView("login_form");
		}
			
		session.setAttribute("accountLoggedInId", account.getId()); 
	    return new ModelAndView("home_logged_in");
	   
	}

	/* Checks if the given password is valid based on BCryptPasswordEncoder */
	private boolean validAccountPasswordGiven(Account account, String inputPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
		if(!bCryptPasswordEncoder.matches(inputPassword, account.getPassword())) {
			return false;
		}
		
		return true;
	}
	
    /* Loading of login form */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/customer/login")
	public ModelAndView showLoginForm(WebRequest request, Model model) {
		
		Account account = new Account();
		model.addAttribute("accountDetails", account);
	    
	    return new ModelAndView("login_form", (Map<String, ?>) model);

	}	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/customer/loggedIn")
	public ModelAndView loggedInForm(WebRequest request, Model model) {
		
	    return new ModelAndView("home_logged_in");

	}	


}
