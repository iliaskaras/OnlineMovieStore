package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.dao.IMovieDAO;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.controller.exception.movie.MovieNotFoundException;

@Service
public class MovieService implements IMovieService{
	
	    /* inject the customer dao */
		@Autowired
		private IMovieDAO movieDAO;
		
		@Override
		@Transactional
		public List<Movie> getMovies() {
			return movieDAO.getMovies();
		}

		@Override
		@Transactional
		public void saveMovie(Movie theMovie) {

			movieDAO.saveMovie(theMovie);
			
		}

		@Override
		@Transactional
		public Movie getMovieById(int theId) {
			Movie theMovie = movieDAO.getMovieById(theId);
			
			if (theMovie == null)
				throw new CustomerNotFoundException("Movie with id :"+theId+", not found!"); 
			
			return theMovie;
		}

		@Override
		@Transactional
		public Movie getMovieByTitle(String theMovieTitle, int movieId) throws MovieNotFoundException {
			
			Movie theMovie = movieDAO.getMovieByTitle(theMovieTitle, movieId);
			
			if (theMovie == null)
				throw new MovieNotFoundException("Movie with title :"+theMovieTitle+", not found!"); 
			
			return theMovie;
		}

		@Override
		@Transactional
		public void deleteMovie(int theId) {
			
			movieDAO.deleteMovie(theId);
			
		}
	
}
