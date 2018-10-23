package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id",nullable = false)
	private int id;
	
	@Column(name="genre_type_id",nullable = false)
	@JoinColumn(name="genre_type_id", nullable = false)
	private int genreTypeId;
	
	@Column(name="video_format_type_id",nullable = false)
	@JoinColumn(name="video_format_type_id", nullable = false)
	private int videoFormatTypeId;
	
	@Column(name="rental_package_id",nullable = false)
	@JoinColumn(name="rental_package_id", nullable = false)
	private int rentalPackageId;
	
	@Column(name="release_year",nullable = false)
	private int releaseYear;
	
	@Column(name="movie_title",nullable = false)
	private String movieTitle;
	
	@Column(name="movie_description",nullable = false)
	private String movieDescription;
	
	public Movie() { }

	public Movie(int id, int genreTypeId, int videoFormatTypeId, int releaseYear, String movieTitle,
			String movieDescription, int rentalPackageId) {
		this.id = id;
		this.genreTypeId = genreTypeId;
		this.videoFormatTypeId = videoFormatTypeId;
		this.releaseYear = releaseYear;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.rentalPackageId = rentalPackageId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGenreTypeId() {
		return genreTypeId;
	}

	public void setGenreTypeId(int genreTypeId) {
		this.genreTypeId = genreTypeId;
	}

	public int getVideoFormatTypeId() {
		return videoFormatTypeId;
	}

	public void setVideoFormatTypeId(int videoFormatTypeId) {
		this.videoFormatTypeId = videoFormatTypeId;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public int getRentalPackageId() {
		return rentalPackageId;
	}

	public void setRentalPackageId(int rentalPackageId) {
		this.rentalPackageId = rentalPackageId;
	}
	
	public static class MovieBuilder {
		
		private int id;
		private int genreTypeId;
		private int videoFormatTypeId;
		private int rentalPackageId;
		private int releaseYear;
		private String movieTitle;
		private String movieDescription;
		
		public MovieBuilder() {}

		public MovieBuilder setId(int id) {
			this.id = id;
			return this;
		}

		public MovieBuilder setGenreTypeId(int genreTypeId) {
			this.genreTypeId = genreTypeId;
			return this;
		}

		public MovieBuilder setVideoFormatTypeId(int videoFormatTypeId) {
			this.videoFormatTypeId = videoFormatTypeId;
			return this;
		}

		public MovieBuilder setRentalPackageId(int rentalPackageId) {
			this.rentalPackageId = rentalPackageId;
			return this;
		}

		public MovieBuilder setReleaseYear(int releaseYear) {
			this.releaseYear = releaseYear;
			return this;
		}

		public MovieBuilder setMovieTitle(String movieTitle) {
			this.movieTitle = movieTitle;
			return this;
		}

		public MovieBuilder setMovieDescription(String movieDescription) {
			this.movieDescription = movieDescription;
			return this;
		}
		
		public Movie build() {
            // call the private constructor in the outer class
            return new Movie(this);
        }
		
		
	}
	
	private Movie(MovieBuilder builder) {
		this.id = builder.id;
		this.genreTypeId = builder.genreTypeId;
		this.videoFormatTypeId = builder.videoFormatTypeId;
		this.releaseYear = builder.releaseYear;
		this.movieTitle = builder.movieTitle;
		this.movieDescription = builder.movieDescription;
		this.rentalPackageId = builder.rentalPackageId;
	}

}

