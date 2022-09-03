package com.dailycodebuffer.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.springboot.tutorial.domain.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

	Iterable<Song> findByNameContainingIgnoreCase(String name);

}
