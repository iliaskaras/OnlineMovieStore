package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;

/* UserService is an interface which its implementation will delegates the calls 
 * from the UserService to the actual UserDAO layer. */
public interface IMovieService {
	
	public List<Movie> getMovies();

	public void saveMovie(Movie theMovie);

	public Movie getMovieById(int theId);
	
	public Movie getMovieByTitle(String theMovieTitle, int movieId) throws CustomerNotFoundException;
	
	public List<Movie> getMoviesByGenreType(int theGenreTypeId);
	
	public void deleteMovie(int theId);
}
