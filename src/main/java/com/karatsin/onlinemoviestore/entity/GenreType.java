package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genre_types")
public class GenreType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_type_id",nullable = false)
	int genreTypeId;
	
	@Column(name="genre_type",nullable = false)
	String genreType;
	
	@Column(name="genre_description",nullable = false)
	String genreDescription;

	public GenreType() {
	}

	public GenreType(int genreTypeId, String genreType, String genreDescription) {
		this.genreTypeId = genreTypeId;
		this.genreType = genreType;
		this.genreDescription = genreDescription;
	}

	public int getGenreTypeId() {
		return genreTypeId;
	}

	public void setGenreTypeId(int genreTypeId) {
		this.genreTypeId = genreTypeId;
	}

	public String getGenreType() {
		return genreType;
	}

	public void setGenreType(String genreType) {
		this.genreType = genreType;
	}

	public String getGenreDescription() {
		return genreDescription;
	}

	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}

	
	
}
