package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.Artist;

public interface ArtistService {

	public Artist findArtistById(Long id);

	public Iterable<Artist> findAllArtists();

	public Iterable<Artist> findArtistsByName(String name);

}
