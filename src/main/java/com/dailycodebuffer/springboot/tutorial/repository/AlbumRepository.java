package com.dailycodebuffer.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.springboot.tutorial.domain.Album;
import com.dailycodebuffer.springboot.tutorial.domain.Artist;

@Repository
public interface AlbumRepository  extends JpaRepository<Album, Long>  {
	
	 Iterable<Album> findByNameContainingIgnoreCase(String name);

}
