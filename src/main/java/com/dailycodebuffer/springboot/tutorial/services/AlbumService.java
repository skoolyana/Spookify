package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.Album;


public interface AlbumService {

	public Album findAlbumById(Long id);

	public Iterable<Album> findAllAlbums();

	public Iterable<Album> findAlbumsByName(String name);

}
