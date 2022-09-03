package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.Song;

public interface SongService {
	
	public Song findSongById(Long id);
	
	public Iterable<Song> findAllSongs();
	
	public Iterable<Song> findSongsByName(String name);
	
	
}
