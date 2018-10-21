package com.karatsin.onlinemoviestore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id",nullable = false)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
	
	@Column(name="payment_method_id", nullable = false)
	private int paymentMethodId;
	
	@Column(name="account_username")
	@Size(min=5, max = 15)
	@NotNull
	@NotEmpty
	private String username;
	
	@Column(name="account_password")
	//@Size(min = 6, max = 15)
	@NotNull
	@NotEmpty
	private String password;

	public Account() {}
	
	public Account(int id, Customer customer, int paymentMethodId, String username, String password) {
		this.id = id;
		this.customer = customer;
		this.paymentMethodId = paymentMethodId;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
