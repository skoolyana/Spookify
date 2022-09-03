package com.dailycodebuffer.springboot.tutorial.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import com.dailycodebuffer.springboot.tutorial.domain.annotations.JacksonIdSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "users") // PostgreSQL - reserved keyword 'user'
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username should not be blank")
	@Size(min = 3, max = 15, message = "Username should be of 3 to 15 characters")
	@Column(unique = true)
	private String username;
	@NotBlank(message = "Full name should not be blank")
	private String fullName;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // ensures in Jackson that this field
	@NotBlank(message = "Password should not be blank") // is not sent in response POJO
	private String password;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Transient
	private String confirmPassword;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "LIKED", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "SONG_ID"))
	private List<Song> likedSongs = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Playlist> playlists = new ArrayList<>();

	@JacksonIdSerializer
	@Cache(region = "userCache", usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	public void addSong(Song song) {
		this.likedSongs.add(song);
	}

	public void removeSong(Song song) {
		this.likedSongs.remove(song);
	}

	public void addPlaylist(Playlist playlist) {
		this.playlists.add(playlist);
	}

	public void removePlaylist(Playlist playlist) {
		this.playlists.remove(playlist);
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	
}
