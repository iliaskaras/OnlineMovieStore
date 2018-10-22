package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;

public class MovieCast {

	@Column(name="movie_id", nullable = false)
	private int movieId;
	
	@Column(name="actor_id", nullable = false)
	private int actorId;
	
	@Column(name="director_id", nullable = false)
	private int directorId;

	public MovieCast() {}

	public MovieCast(int movieId, int actorId, int directorId) {
		this.movieId = movieId;
		this.actorId = actorId;
		this.directorId = directorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
		
}
