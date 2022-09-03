package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.Genre;

public interface GenreService {

	
	 public  Genre findGenreById(Long id);
	
	 public Iterable<Genre> findAllGenres();
	 
	 public Iterable<Genre> findGenresByName(String name);
	 
	 
	 
	 
	 
}
