package com.karatsin.onlinemoviestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name="transaction_date",nullable = false)
	private Date transactionDate;
	
	@Column(name="rental_end_date",nullable = false)
	private Date rentalEndDate;
	
	@Column(name="transaction_amount",nullable = false)
	private Float transactionAmount;
	
	@Column(name="transaction_comment",nullable = false)
	private String transactionComment;

	public Transaction() {}
	
	public Transaction(int transactionId, int accountId, int paid, Date transactionDate,
			Date rentalEndDate, Float transactionAmount, String transactionComment) {
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.paid = paid;
		this.transactionDate = transactionDate;
		this.rentalEndDate = rentalEndDate;
		this.transactionAmount = transactionAmount;
		this.transactionComment = transactionComment;
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

	
	
	
	
}
