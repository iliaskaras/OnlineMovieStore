package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="directors")
public class Director {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="director_id",nullable = false)
	private int directorId;
	
	@Column(name="director_first_name",nullable = false)
	private String directorFirstName;
	
	@Column(name="director_last_name",nullable = false)
	private String directorLastName;
	
	@Column(name="director_details",nullable = false)
	private String directorDetails;
	
	@Column(name="director_citinenship",nullable = false)
	private String directorCitinenship;

	public Director() {}
	
	public Director(int directorId, String directorFirstName, String directorLastName, String directorDetails,
			String directorCitinenship) {
		this.directorId = directorId;
		this.directorFirstName = directorFirstName;
		this.directorLastName = directorLastName;
		this.directorDetails = directorDetails;
		this.directorCitinenship = directorCitinenship;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	public String getDirectorDetails() {
		return directorDetails;
	}

	public void setDirectorDetails(String directorDetails) {
		this.directorDetails = directorDetails;
	}

	public String getDirectorCitinenship() {
		return directorCitinenship;
	}

	public void setDirectorCitinenship(String directorCitinenship) {
		this.directorCitinenship = directorCitinenship;
	}
	
	

}
