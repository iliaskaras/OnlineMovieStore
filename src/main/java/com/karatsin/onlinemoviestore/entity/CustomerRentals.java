package com.karatsin.onlinemoviestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_rentals")
public class CustomerRentals {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_rental_id",nullable = false)
	private int movieRentalId;
	
	@Column(name="movie_id",nullable = false)
	private int movieId;
	
	@Column(name="rental_start_date",nullable = false)
	private Date rentalStartDate;
	
	@Column(name="rental_end_date",nullable = false)
	private Date rentalEndDate;

	public CustomerRentals() {}
	
	public CustomerRentals(int movieRentalId, int movieId, Date rentalStartDate, Date rentalEndDate) {
		this.movieRentalId = movieRentalId;
		this.movieId = movieId;
		this.rentalStartDate = rentalStartDate;
		this.rentalEndDate = rentalEndDate;
	}

	public int getMovieRentalId() {
		return movieRentalId;
	}

	public void setMovieRentalId(int movieRentalId) {
		this.movieRentalId = movieRentalId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public Date getRentalStartDate() {
		return rentalStartDate;
	}

	public void setRentalStartDate(Date rentalStartDate) {
		this.rentalStartDate = rentalStartDate;
	}

	public Date getRentalEndDate() {
		return rentalEndDate;
	}

	public void setRentalEndDate(Date rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}
	
	

}
