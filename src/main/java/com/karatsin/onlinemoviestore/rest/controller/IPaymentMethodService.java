package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.PaymentMethod;

public interface IPaymentMethodService {
	
	public List<PaymentMethod> getPaymentMethods();

	public void savePaymentMethod(PaymentMethod thePaymentMethod);

	public PaymentMethod getPaymentMethodById(int theId);
	
	public PaymentMethod getPaymentMethodByType(String thePaymentMethodType);

	public void deletePaymentMethodById(int theId);
	
	public void deletePaymentMethodByType(String thePaymentMethodType);
}
