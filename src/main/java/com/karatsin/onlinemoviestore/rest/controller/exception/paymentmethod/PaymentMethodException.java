package com.karatsin.onlinemoviestore.rest.controller.exception.paymentmethod;

public class PaymentMethodException extends RuntimeException{

	public PaymentMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentMethodException(String message) {
		super(message);
	}

	public PaymentMethodException(Throwable cause) {
		super(cause);
	}

	
}
