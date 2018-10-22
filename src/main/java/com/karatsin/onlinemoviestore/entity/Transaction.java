package com.karatsin.onlinemoviestore.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id",nullable = false)
	private int transactionId;
	
	@Column(name="account_id",nullable = false)
	private int accountId;
	
	@Column(name="paid",nullable = false)
	private int paid;
	
	@Column(name="movie_id",nullable = false)
	private int movieId;
	
	@Column(name="transaction_date",nullable = false)
	private Date transactionDate;
	
	@Column(name="rental_end_date",nullable = false)
	private Date rentalEndDate;
	
	@Column(name="number_of_order_days",nullable = false)
	private int numberOfOrderDays;
	
	@Column(name="transaction_amount",nullable = false)
	private Float transactionAmount;
	
	@Column(name="transaction_comment",nullable = false)
	private String transactionComment;

	
	public Transaction() {}
	
	public Transaction(int transactionId, int accountId, int paid, int movieId, Date transactionDate,
			Date rentalEndDate, Float transactionAmount, String transactionComment, int numberOfOrderDays) {
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.paid = paid;
		this.movieId = movieId;
		this.transactionDate = transactionDate;
		this.rentalEndDate = rentalEndDate;
		this.transactionAmount = transactionAmount;
		this.transactionComment = transactionComment;
		this.numberOfOrderDays = numberOfOrderDays;
	}
	
	public Date getRentalEndDate() {
		return rentalEndDate;
	}

	public void setRentalEndDate(Date rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionComment() {
		return transactionComment;
	}

	public void setTransactionComment(String transactionComment) {
		this.transactionComment = transactionComment;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getNumberOfOrderDays() {
		return numberOfOrderDays;
	}

	public void setNumberOfOrderDays(int numberOfOrderDays) {
		this.numberOfOrderDays = numberOfOrderDays;
	}

	
	public static class TransactionBuilder {
		private int transactionId;
		private int accountId;
		private int paid;
		private int movieId;
		private Date transactionDate;
		private Date rentalEndDate;
		private int numberOfOrderDays;
		private Float transactionAmount;
		private String transactionComment;
	
		public TransactionBuilder() {}
		
		public TransactionBuilder setTransactionId(int transactionId) {
			this.transactionId = transactionId;
			return this;
		}
		public TransactionBuilder setAccountId(int accountId) {
			this.accountId = accountId;
			return this;
		}
		public TransactionBuilder setPaid(int paid) {
			this.paid = paid;
			return this;
		}
		public TransactionBuilder setMovieId(int movieId) {
			this.movieId = movieId;
			return this;
		}
		public TransactionBuilder setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}
		public TransactionBuilder setRentalEndDate(Date rentalEndDate) {
			this.rentalEndDate = rentalEndDate;
			return this;
		}
		public TransactionBuilder setNumberOfOrderDays(int numberOfOrderDays) {
			this.numberOfOrderDays = numberOfOrderDays;
			return this;
		}
		public TransactionBuilder setTransactionAmount(Float transactionAmount) {
			this.transactionAmount = transactionAmount;
			return this;
		}
		public TransactionBuilder setTransactionComment(String transactionComment) {
			this.transactionComment = transactionComment;
			return this;
		}
		
		public Transaction build() {
            // call the private constructor in the outer class
            return new Transaction(this);
        }
		
		

	}
	
	private Transaction(TransactionBuilder builder) {
		this.transactionId = builder.transactionId;
		this.accountId = builder.accountId;
		this.paid = builder.paid;
		this.movieId = builder.movieId;
		this.transactionDate = builder.transactionDate;
		this.rentalEndDate = builder.rentalEndDate;
		this.transactionAmount = builder.transactionAmount;
		this.transactionComment = builder.transactionComment;
		this.numberOfOrderDays = builder.numberOfOrderDays;
	}
	
	
}
