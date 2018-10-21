package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Movie;

public interface IMovieDAO {

	public List<Movie> getMovies();

	public void saveMovie(Movie theMovie);

	public Movie getMovieById(int theId);
	
	public Movie getMovieByTitle(String theTitle, int movieId);

	public List<Movie> getMoviesByGenre(int theGenreTypeId);
	
	public void deleteMovie(int theId);
}
