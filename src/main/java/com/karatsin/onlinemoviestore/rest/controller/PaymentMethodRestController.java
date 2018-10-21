package com.karatsin.onlinemoviestore.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.enums.UriPaths;
import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;

@RestController
@RequestMapping("/api")
public class PaymentMethodRestController {
	
	
	@Autowired
	IPaymentMethodService paymentMethodService;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void initVariables() {	
		restTemplate = new RestTemplate();
	}
	
	@GetMapping("/paymentmethod/{paymentMethodType}")
	public PaymentMethod getPaymentMethod(@PathVariable String paymentMethodType) {
		
		PaymentMethod thePaymentMethod = paymentMethodService.getPaymentMethodByType(paymentMethodType);
		
		if (thePaymentMethod == null)
			throw new PaymentMethodException("Payment Method with type :"+paymentMethodType+", doesn't exist!"); 
		
		return thePaymentMethod;
	}
	
	
	/* endpoint for "/paymentmethod" - return a list of paymentMethods
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/paymentmethods")
	public List<PaymentMethod> getPaymentMethods(){
	
		return paymentMethodService.getPaymentMethods();
	}
	

	

}
