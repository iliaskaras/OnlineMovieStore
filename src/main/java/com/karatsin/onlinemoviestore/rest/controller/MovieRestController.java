package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.GenreType;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.controller.exception.movie.MovieNotFoundException;
import com.karatsin.onlinemoviestore.rest.response.ErrorResponse;

@RestController
@RequestMapping("/api")
public class MovieRestController {
	
	@Autowired
	IMovieService movieService;
	@Autowired
	IGenreTypeService genreTypeService;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void loadData() {	
	}
	
	@GetMapping("/movies/id=/{movieId}")
	public Movie getMovies(@PathVariable int movieId){
		
		Movie theMovie = movieService.getMovieById(movieId);
		
		return theMovie;
	}
	
	@GetMapping("/movies")
	public List<Movie> getMovies(){
	
		return movieService.getMovies();
	}
	
	@GetMapping("/movies/title={movieTitle}/id={movieId}")
	public ModelAndView getMovieSubmitOrderForm(@PathVariable String movieTitle, @PathVariable int movieId) {
		
		Movie theMovie = movieService.getMovieByTitle(movieTitle, movieId);
		
		return new ModelAndView("movie_submit_order", "theMovie", theMovie);
	}
	
	
	
	@DeleteMapping("/movies/delete/id=/{movieId}")
	public String deleteMovie(@PathVariable int movieId){
		
		movieService.deleteMovie(movieId);
		
		return "Movie with id: "+movieId+", deleted succesfully!";
		
	}
	
	@GetMapping(value = "/movies/all")
	public ModelAndView showMoviesForm(WebRequest request, Model model) {
		List<Movie> movies = movieService.getMovies();
		GenreType genreType = new GenreType();

		model.addAttribute("movies", movies);
	    model.addAttribute("genreType", genreType);
	    return new ModelAndView("movies_form", (Map<String, ?>) model);
	    
	}	
	
	@GetMapping(value = "/movies/all/genreType/")
	public ModelAndView showMoviesByGenreType(@Valid @ModelAttribute("genreType") GenreType genreTypeDTO, WebRequest request, Model model) {
		GenreType genreType = genreTypeService.getGenreTypeByType(genreTypeDTO.getGenreType());
		List<Movie> movies = movieService.getMoviesByGenreType(genreType.getGenreTypeId());
		
		model.addAttribute("movies", movies);
	    model.addAttribute("genreType", genreType);
	    return new ModelAndView("movies_form", (Map<String, ?>) model);

	}	
	
	@ModelAttribute("genreTypesList")
	public ArrayList<String> getGenreTypesList() {
		List<GenreType> genreTypes = genreTypeService.getGenreTypes();
		ArrayList<String> genreTypesList = new ArrayList<String>();

		for(GenreType genreType : genreTypes) {
			genreTypesList.add(genreType.getGenreType());
		}
		
	    return genreTypesList;
	}
	
}
