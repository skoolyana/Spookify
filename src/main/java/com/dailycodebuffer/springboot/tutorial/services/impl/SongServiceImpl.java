package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Song;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.repository.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongServiceImpl {

	private final SongRepository songRepository;
	
	
	public Song findSongById(Long id) {

        return songRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Song ID: %s does not exist", id)));
    }
	
	
	public Iterable<Song> findAllSongs() {

        return songRepository.findAll();
    }
	
	
	public Iterable<Song> findSongsByName(String name) {

        return songRepository.findByNameContainingIgnoreCase(name);
    }
	

	
	
	
	
	
}
