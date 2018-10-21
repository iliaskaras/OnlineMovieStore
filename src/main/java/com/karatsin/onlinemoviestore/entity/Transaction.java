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
	
	@Column(name="movie_rental_id",nullable = false)
	private int movieRentalId;
	
	@Column(name="transaction_date",nullable = false)
	private Date transactionDate;
	
	@Column(name="transaction_amount",nullable = false)
	private Float transactionAmount;
	
	@Column(name="transaction_comment",nullable = false)
	private String transactionComment;
	
}
