package com.dailycodebuffer.springboot.tutorial.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Genre {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	private String name;
	private String photo_url;
	
	@ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
	private List<Song> songs = new ArrayList();
	
	
	public void addSong(Song song) {
        this.songs.add(song);
    }

	
	
	 public void removeSong(Song song) {
	        this.songs.remove(song);
	 }
	
	
	
	
}
