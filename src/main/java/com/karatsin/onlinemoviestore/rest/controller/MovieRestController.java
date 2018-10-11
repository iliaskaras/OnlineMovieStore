package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomException;
import com.karatsin.onlinemoviestore.rest.response.CustomerErrorResponse;

@RestController
@RequestMapping("/api")
public class MovieRestController {
	List<Movie> theMovies;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void loadData() {	
		theMovies = new ArrayList<>();
		
		theMovies.add(new Movie("movie","111"));
		theMovies.add(new Movie("movie1","222"));
		theMovies.add(new Movie("movie2","333"));
	}
	
	/* endpoint for "/users" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/movies")
	public List<Movie> getUsers(){
	
		return theMovies;
	}
	
	/* Binding of path variable userId to retrieve a single user */
	@GetMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable int movieId){
		
		if( (movieId < 0) || movieId >= theMovies.size())
			throw new CustomException("Movie with id "+ movieId +" not found");
		
		return theMovies.get(movieId);
	}
	
	
}
