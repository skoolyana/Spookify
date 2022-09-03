package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Genre;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.repository.GenreRepository;
import com.dailycodebuffer.springboot.tutorial.services.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

	 private final GenreRepository genreRepository;
	
	 
	@Cacheable(value = "genres", key = "#genreId")
	@Override
	public Genre findGenreById(Long id) {
		// TODO Auto-generated method stub
		return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre ID: %s does not exist", id)));
	}

	
	@Cacheable("genres")
	@Override
	public Iterable<Genre> findAllGenres() {
		// TODO Auto-generated method stub
		 return genreRepository.findAll();
	}

	@Override
	public Iterable<Genre> findGenresByName(String name) {
		// TODO Auto-generated method stub
		 return genreRepository.findByNameContainingIgnoreCase(name);
	}

	
	
}
