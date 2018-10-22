package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.GenreType;

public interface IGenreTypeService {
	
	public List<GenreType> getGenreTypes();

	public void saveGenreType(GenreType theGenreType);

	public GenreType getGenreTypeById(int theId);
	
	public GenreType getGenreTypeByType(String theGenreType);

	public void deleteGenreType(int theId);
}
