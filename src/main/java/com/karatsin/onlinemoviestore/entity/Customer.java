package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.karatsin.onlinemoviestore.entity.Account.AccountBuilder;
import com.karatsin.onlinemoviestore.validation.constraints.AgeConstraint;
import com.karatsin.onlinemoviestore.validation.constraints.PhoneNumberConstraint;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int id;
	
	@Column(name="customer_first_name")
	@NotEmpty
	private String firstName;
	
	@Column(name="customer_last_name")
	@NotEmpty
	private String lastName;
	
	@Column(name="customer_email")
	@NotEmpty @Email
	private String email;
	
	@Column(name="customer_phone")
	@PhoneNumberConstraint
	private String phone;
	
	@Column(name="customer_age")
	@AgeConstraint(minAge=16)
	private Integer age;
	
	public Customer() { }
	
	public Customer( String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(int id, String firstName, String lastName, String email, String phone, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	/* For debugging */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [id="+ id +", firstname="+ firstName +", lastname="+ lastName +"]";
	}
	
	public static class CustomerBuilder{
		private int id;
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		private Integer age;
		
		public CustomerBuilder setId(int id) {
			this.id = id;
			return this;
		}
		public CustomerBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public CustomerBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public CustomerBuilder setEmail(String email) {
			this.email = email;
			return this;
		}
		public CustomerBuilder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public CustomerBuilder setAge(Integer age) {
			this.age = age;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}
	
	private Customer(CustomerBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.phone = builder.phone;
		this.age = builder.age;
	}

}
