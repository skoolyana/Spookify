package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Artist;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.repository.ArtistRepository;
import com.dailycodebuffer.springboot.tutorial.services.ArtistService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

	private final ArtistRepository artistRepository;

	@Cacheable(value = "artists", key = "#artistId")
	@Override
	public Artist findArtistById(Long id) {
		// TODO Auto-generated method stub
		return artistRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Artist ID: %s does not exist", id)));
	}

	@Cacheable("artists")
	@Override
	public Iterable<Artist> findAllArtists() {
		// TODO Auto-generated method stub
		return artistRepository.findAll();
	}

	@Override
	public Iterable<Artist> findArtistsByName(String name) {
		// TODO Auto-generated method stub
		return artistRepository.findByNameContainingIgnoreCase(name);
	}

}
