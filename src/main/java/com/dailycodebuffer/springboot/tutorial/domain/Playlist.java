package com.dailycodebuffer.springboot.tutorial.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dailycodebuffer.springboot.tutorial.domain.annotations.JacksonIdSerializer;

import javax.persistence.JoinColumn;
import lombok.Data;


@Entity
@Data
@Cache(region = "playlistCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@JacksonIdSerializer
    @Cache(region = "playlistCache", usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "SONG_PLAYLIST", joinColumns = @JoinColumn(name = "PLAYLIST_ID"), inverseJoinColumns = @JoinColumn(name = "SONG_ID"))
	private List<Song> songs = new ArrayList<>();

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public void addSong(Song song) {
		this.songs.add(song);
	}

	public void removeSong(Song song) {
		this.songs.remove(song);
	}

}
