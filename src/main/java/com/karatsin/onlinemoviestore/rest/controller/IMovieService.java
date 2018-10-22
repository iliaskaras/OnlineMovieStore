package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;

public interface IMovieService {
	
	public List<Movie> getMovies();
	
	public List<Movie> getMoviesByTransactionsId(List<Integer> transactionMovieIds) ;

	public void saveMovie(Movie theMovie);

	public Movie getMovieById(int theId);
	
	public Movie getMovieByTitle(String theMovieTitle, int movieId) throws CustomerNotFoundException;
	
	public List<Movie> getMoviesByGenreType(int theGenreTypeId);
	
	public void deleteMovie(int theId);
}
