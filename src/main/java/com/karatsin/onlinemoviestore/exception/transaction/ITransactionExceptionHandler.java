package com.karatsin.onlinemoviestore.exception.transaction;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface ITransactionExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(TransactionNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
