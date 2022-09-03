package com.dailycodebuffer.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.springboot.tutorial.domain.Artist;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist, Long>  {
	
	 Iterable<Artist> findByNameContainingIgnoreCase(String name);

}
