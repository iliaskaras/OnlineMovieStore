package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_methods")
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_method_id")
	private int id;
	
	@Column(name="payment_method_description")
	private String paymentType;

	public PaymentMethod() {}
	
	public PaymentMethod(int id, String paymentType) {
		this.id = id;
		this.paymentType = paymentType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	
}
