package com.dailycodebuffer.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.springboot.tutorial.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

	
	
}
