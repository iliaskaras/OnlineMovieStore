package com.karatsin.onlinemoviestore.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.validation.Valid;


public class RegistrationWrapper {
	
	@Valid
	private Account account;
	@Valid
	private Customer customer;
	@Valid
	private PaymentMethod paymentMethod;
	private ArrayList<String> listOfPaymentMethods;
	
	public RegistrationWrapper() {}
	
	public RegistrationWrapper(Account account, Customer customer, PaymentMethod paymentMethod) {
		
		this.account = account;
		this.customer = customer;
		this.paymentMethod = paymentMethod;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public ArrayList<String> getListOfPaymentMethods() {
		return listOfPaymentMethods;
	}
	public void setListOfPaymentMethods(ArrayList<String> listOfPaymentMethods) {
		this.listOfPaymentMethods = listOfPaymentMethods;
	}
	
	

}
