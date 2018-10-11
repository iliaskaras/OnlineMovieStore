package com.karatsin.onlinemoviestore.entity;

public class Movie {
	
	private int id;
	private String firstName;
	private String lastName;
	
	public Movie() { }
	
	public Movie(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	/* For debugging */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [id="+ id +", firstname="+ firstName +", lastname="+ lastName +"]";
	}
	

}

