package com.karatsin.onlinemoviestore.rest.controller.exception.transaction;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface ITransactionExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(TransactionNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
