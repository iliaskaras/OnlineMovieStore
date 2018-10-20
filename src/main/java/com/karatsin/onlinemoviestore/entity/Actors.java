package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actors")
public class Actors {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id",nullable = false)
	private int actorId;
	
	@Column(name="actor_first_name",nullable = false)
	private String actorFirstName;
	
	@Column(name="actor_last_name",nullable = false)
	private String actorLastName;
	
	@Column(name="actor_details",nullable = false)
	private String actorDetails;
	
	@Column(name="actor_gender",nullable = false)
	private String actorGender;

	public Actors() {}
	
	public Actors(int actorId, String actorFirstName, String actorLastName, String actorDetails, String actorGender) {
		this.actorId = actorId;
		this.actorFirstName = actorFirstName;
		this.actorLastName = actorLastName;
		this.actorDetails = actorDetails;
		this.actorGender = actorGender;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getActorFirstName() {
		return actorFirstName;
	}

	public void setActorFirstName(String actorFirstName) {
		this.actorFirstName = actorFirstName;
	}

	public String getActorLastName() {
		return actorLastName;
	}

	public void setActorLastName(String actorLastName) {
		this.actorLastName = actorLastName;
	}

	public String getActorDetails() {
		return actorDetails;
	}

	public void setActorDetails(String actorDetails) {
		this.actorDetails = actorDetails;
	}

	public String getActorGender() {
		return actorGender;
	}

	public void setActorGender(String actorGender) {
		this.actorGender = actorGender;
	}
	
}
