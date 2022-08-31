package com.dailycodebuffer.springboot.tutorial.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Song {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String name;
	 private String link;

	 @ManyToOne(fetch = FetchType.EAGER)
	 private Artist artist;
	 
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 private Album album;
	 
	 
	 @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinTable(name = "SONG_GENRE",joinColumns = @JoinColumn(name = "SONG_ID"),inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
	 private List<Genre> genres = new ArrayList<>();
	 
	 
}
