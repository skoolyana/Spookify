package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Album;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.repository.AlbumRepository;
import com.dailycodebuffer.springboot.tutorial.services.AlbumService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

	private final AlbumRepository albumRepository;

	@Cacheable(value = "albums", key = "#albumId")
	public Album findAlbumById(Long id) {

		return albumRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Album ID: %s does not exist", id)));
	}

	@Cacheable("albums")
	public Iterable<Album> findAllAlbums() {

		return albumRepository.findAll();
	}

	public Iterable<Album> findAlbumsByName(String name) {

		return albumRepository.findByNameContainingIgnoreCase(name);
	}

}
