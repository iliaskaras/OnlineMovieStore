package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.dao.IPaymentMethodDAO;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;

@Service
public class PaymentMethodService implements IPaymentMethodService{

	 /* inject the user dao */
	@Autowired
	private IPaymentMethodDAO paymentMethodDAO;
	
	@Override
	@Transactional
	public List<PaymentMethod> getPaymentMethods() {

		return paymentMethodDAO.getPaymentMethods();
	}
	
	@Override
	@Transactional
	public void savePaymentMethod(PaymentMethod thePaymentMethod) {
		
	    paymentMethodDAO.savePaymentMethod(thePaymentMethod);
	}

	@Override
	@Transactional
	public PaymentMethod getPaymentMethodByType(String thePaymentMethodType) {

		PaymentMethod thePaymentMethod = paymentMethodDAO.getPaymentMethodByType(thePaymentMethodType);
		
		if (thePaymentMethod == null)
			throw new PaymentMethodException("Payment Method with type :"+thePaymentMethodType+", doesn't exist!"); 
		
		return thePaymentMethod;
	}

	@Override
	@Transactional
	public PaymentMethod getPaymentMethodById(int theId) {

		return paymentMethodDAO.getPaymentMethodById(theId);
		
	}
	

	@Override
	@Transactional
	public void deletePaymentMethodById(int theId) {
		
		paymentMethodDAO.deletePaymentMethodById(theId);
	}
	
	@Override
	@Transactional
	public void deletePaymentMethodByType(String thePaymentMethodType) {
		
		paymentMethodDAO.deletePaymentMethodByType(thePaymentMethodType);
	}

}
