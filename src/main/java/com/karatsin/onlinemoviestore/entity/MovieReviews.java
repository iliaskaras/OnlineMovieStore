package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie_reviews")
public class MovieReviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_review_id",nullable = false)
	private int movieReviewId;
	
	@Column(name="movie_id",nullable = false)
	private int movieId;
	
	@Column(name="movie_review",nullable = false)
	private String movieReview;
	
	@Column(name="movie_raiting",nullable = false)
	private int movieRaiting;

	public MovieReviews() {}
	
	public MovieReviews(int movieReviewId, int movieId, String movieReview, int movieRaiting) {
		this.movieReviewId = movieReviewId;
		this.movieId = movieId;
		this.movieReview = movieReview;
		this.movieRaiting = movieRaiting;
	}

	public int getMovieReviewId() {
		return movieReviewId;
	}

	public void setMovieReviewId(int movieReviewId) {
		this.movieReviewId = movieReviewId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieReview() {
		return movieReview;
	}

	public void setMovieReview(String movieReview) {
		this.movieReview = movieReview;
	}

	public int getMovieRaiting() {
		return movieRaiting;
	}

	public void setMovieRaiting(int movieRaiting) {
		this.movieRaiting = movieRaiting;
	}
	
	
}
