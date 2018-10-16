package com.karatsin.onlinemoviestore.enums;


public enum UriPaths {

	
	GET_CUSTOMER_BY_EMAIL("api/customers/"),
	GET_PAYMENTMETHOD_BY_TYPE("api/paymentmethod/"),
	GET_PAYMENT_METHODS("api/paymentmethods/"),
	GET_REGISTRATION_FORM("api/account/registration/");
	
	 // declaring private variable for getting values 
    private String action; 
  
    // getter method 
    public String getAction() 
    { 
        return this.action; 
    } 
  
    // enum constructor - cannot be public or protected 
    private UriPaths(String action) 
    { 
        this.action = "http://localhost:8080/OnlineMovieStore/"+action; 
    } 
}
