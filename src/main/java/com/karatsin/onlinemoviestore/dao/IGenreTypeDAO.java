package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.GenreType;

public interface IGenreTypeDAO {

	public List<GenreType> getGenreTypes();

	public void saveGenreType(GenreType theGenreType);

	public GenreType getGenreTypeById(int theId);
	
	public GenreType getGenreTypeByType(String theGenreType);

	public void deleteGenreType(int theId);

	
}
