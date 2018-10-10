package com.karatsin.onlinemoviestore.entity;

public class Movie {
	
	private String firstName;
	private String lastName;
	
	public Movie() { }
	
	public Movie(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	

}

