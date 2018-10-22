package com.karatsin.onlinemoviestore.entity;

import java.util.Date;

import javax.validation.Valid;

public class TransactionWrapper {
	
	@Valid
	private Transaction transaction;
	@Valid
	private Movie movie;
	
	private boolean isPaid;
	

	
	public TransactionWrapper() {}
	
	public TransactionWrapper(@Valid Transaction transaction, @Valid Movie movie) {
		this.transaction = transaction;
		this.movie = movie;
		setPaid(transaction.getPaid());
	}

	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	
	
	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(int isPaid) {
		if(isPaid==0)
			this.isPaid = false;
		else
			this.isPaid = true;	
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}



	
	
	
}

