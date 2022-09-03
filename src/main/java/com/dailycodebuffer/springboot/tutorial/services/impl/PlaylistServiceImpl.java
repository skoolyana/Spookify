package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Playlist;
import com.dailycodebuffer.springboot.tutorial.domain.Song;
import com.dailycodebuffer.springboot.tutorial.domain.User;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.AlreadyExistsException;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.UnauthorizedUserException;
import com.dailycodebuffer.springboot.tutorial.repository.PlaylistRepository;
import com.dailycodebuffer.springboot.tutorial.services.PlaylistService;
import com.dailycodebuffer.springboot.tutorial.services.SongService;
import com.dailycodebuffer.springboot.tutorial.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

	private final UserService userService;

	private final SongService songService;
	
	private final PlaylistRepository playlistRepository;

	
	
	@Override
	public Playlist findPlaylistByIdAndUser(Long id, User user) {
		
		Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Playlist ID: %s does not exist", id)));
		
		if (!playlist.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedUserException(String.format("User: %s is not authorized to access playlist ID: %s",
                    user.getUsername(), id));
        }

		
		  return playlist;
				
		
	}

	@Override
	public Iterable<Playlist> findAllPlaylistsByUser(User user) {
		 // the User object sent from controller does not have
        // children because of lazy fetching
        user = userService.findUserById(user.getId());
        return user.getPlaylists();
	}

	@Override
	public Iterable<Playlist> findPlaylistsByNameAndUser(String name, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist savePlaylist(String name, User user) {
		
		 user = userService.findUserById(user.getId());
		
		 Playlist playlist = new Playlist();
		 playlist.setName(name);
	     playlist.setUser(user);
				
		// TODO Auto-generated method stub
	     return playlistRepository.save(playlist);
	}

	@Override
	public Playlist updatePlaylistById(String name, Long id, User user) {

		 user = userService.findUserById(user.getId());

		 Playlist playlist = findPlaylistByIdAndUser(id, user);
		 playlist.setName(name);
		 
		// TODO Auto-generated method stub
		 return playlistRepository.save(playlist);

	
	
	}

	@Override
	public void deletePlaylistById(Long id, User user) {
		// TODO Auto-generated method stub

		user = userService.findUserById(user.getId());
		
		Playlist playlist = findPlaylistByIdAndUser(id, user);
		
		 playlistRepository.delete(playlist);
		
	}

	@Override
	public Playlist addSongById(Long playlistId, Long songId, User user) {

		 user = userService.findUserById(user.getId());
	     
		 Playlist playlist = findPlaylistByIdAndUser(playlistId, user);
	
	     Song song = songService.findSongById(songId);
	     
	     
	     if (playlist.getSongs().contains(song)) {
	            throw new AlreadyExistsException(String.format("Song: %s already exists in playlist: %s",
	                    song.getName(), playlist.getName()));
	        }
	     
	     playlist.addSong(song);

	     
	     return playlistRepository.save(playlist);
	
	}

	@Override
	public Playlist removeSongById(Long playlistId, Long songId, User user) {

		  user = userService.findUserById(user.getId());
	      
		  Playlist playlist = findPlaylistByIdAndUser(playlistId, user);
	      
		  Song song = songService.findSongById(songId);
		
		
		  if (!playlist.getSongs().contains(song)) {
	            throw new NotFoundException(String.format("Song: %s not found in playlist: %s",
	                    song.getName(), playlist.getName()));
	        }

		  
		  playlist.removeSong(song);
		  
		  return playlistRepository.save(playlist);
		  
	}

}
