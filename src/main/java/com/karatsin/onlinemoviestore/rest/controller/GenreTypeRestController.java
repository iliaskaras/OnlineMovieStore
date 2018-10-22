package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karatsin.onlinemoviestore.entity.GenreType;

@RestController
@RequestMapping("/api")
public class GenreTypeRestController {
	
	@Autowired
	IGenreTypeService genreTypeService;
	
	/* endpoint for "/users" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/genretypes/all")
	public List<GenreType> getGenreTypes(){
	
		return genreTypeService.getGenreTypes();
	}	
	
	@GetMapping("/genretypes/id=/{genreTypeId}")
	public GenreType getGenreTypeById(@PathVariable int genreTypeId) {
		
		GenreType theGenreType = genreTypeService.getGenreTypeById(genreTypeId);
		
		return theGenreType;
	}
	
	@DeleteMapping("/genretypes/id=/{genreTypeId}")
	public String deleteGenreType(@PathVariable int genreTypeId){
		
		GenreType theGenreType = genreTypeService.getGenreTypeById(genreTypeId);
		
		genreTypeService.deleteGenreType(genreTypeId);
		
		return "GenreType with id: "+genreTypeId+", deleted succesfully!";
		
	}
	


}
