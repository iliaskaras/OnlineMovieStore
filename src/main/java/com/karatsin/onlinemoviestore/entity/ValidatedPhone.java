package com.karatsin.onlinemoviestore.entity;

import com.karatsin.onlinemoviestore.validation.constraints.PhoneNumberConstraint;

public class ValidatedPhone {

	@PhoneNumberConstraint
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ValidatedPhone() {}
	public ValidatedPhone(String phone) {
		super();
		this.phone = phone;
	}
	
	
}
