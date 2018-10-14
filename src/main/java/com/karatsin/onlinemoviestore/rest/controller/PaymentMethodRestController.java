package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerException;
import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;

@RestController
@RequestMapping("/api")
public class PaymentMethodRestController {
	
	
	@Autowired
	IPaymentMethodService paymentMethodService;
	
	/* GET method to get a certain customer
	 * Binding of path variable customerID to retrieve a single user 
	 * add mapping for GET /paymentmethod/{paymentMethodType}
	 * 
	 * @return the new created paymentmethod */
	@GetMapping("/paymentmethod/{paymentMethodType}")
	public PaymentMethod getPaymentMethod(@PathVariable String paymentMethodType) {
		
		PaymentMethod thePaymentMethod = paymentMethodService.getPaymentMethodByType(paymentMethodType);
		
		if (thePaymentMethod == null)
			throw new PaymentMethodException("Payment Method with type :"+paymentMethodType+", doesn't exist!"); 
		
//		return "test";
		return thePaymentMethod;
	}
	
	
	/* endpoint for "/paymentmethod" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/paymentmethods")
	public List<PaymentMethod> getPaymentMethods(){
	
		return paymentMethodService.getPaymentMethods();
	}

}
