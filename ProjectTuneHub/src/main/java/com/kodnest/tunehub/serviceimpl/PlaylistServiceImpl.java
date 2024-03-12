package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl {
	
	@Autowired
	PlaylistRepository playListRepository;

	public void addplaylist(Playlist playlist) {
	playListRepository.save(playlist);
		
	}

	public List<Playlist> featchAllPlaylist() {
		List<Playlist> allplaylist = playListRepository.findAll();
		return allplaylist;
	}
}
