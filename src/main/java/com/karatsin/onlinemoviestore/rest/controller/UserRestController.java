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

import com.karatsin.onlinemoviestore.entity.Users;
import com.karatsin.onlinemoviestore.exception.CustomException;
import com.karatsin.onlinemoviestore.rest.response.UserErrorResponse;

@RestController
@RequestMapping("/api")
public class UserRestController {
	List<Users> theUsers;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void loadData() {	
		theUsers = new ArrayList<>();
		
		theUsers.add(new Users("ilias","karatsin"));
		theUsers.add(new Users("ilias1","karatsin1"));
		theUsers.add(new Users("ilias2","karatsin2"));
	}
	
	/* endpoint for "/users" - return a list of users
	   Jackson will convert that list of students to JSON array */
	@GetMapping("/users")
	public List<Users> getUsers(){
	
		return theUsers;
	}
	
	/* Binding of path variable userId to retrieve a single user */
	@GetMapping("/users/{userId}")
	public Users getUser(@PathVariable int userId){
		
		if( (userId < 0) || userId >= theUsers.size())
			throw new CustomException("User with id "+ userId +" not found");
		
		return theUsers.get(userId);
	}
	
	
}
