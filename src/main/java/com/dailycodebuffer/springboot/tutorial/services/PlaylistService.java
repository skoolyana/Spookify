package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.Playlist;
import com.dailycodebuffer.springboot.tutorial.domain.User;

public interface PlaylistService {

	public Playlist findPlaylistByIdAndUser(Long id, User user);

	public Iterable<Playlist> findAllPlaylistsByUser(User user);

	public Iterable<Playlist> findPlaylistsByNameAndUser(String name, User user);

	public Playlist savePlaylist(String name, User user);

	public Playlist updatePlaylistById(String name, Long id, User user);

	public void deletePlaylistById(Long id, User user);

	public Playlist addSongById(Long playlistId, Long songId, User user);

	public Playlist removeSongById(Long playlistId, Long songId, User user);

}
