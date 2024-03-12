package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceImpl implements SongService{
	
	@Autowired
	SongRepository songRepository;
	
	public String addSong(Song song) {
		songRepository.save(song);
		return "song added successfully";
		
	}
	
	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs=songRepository.findAll();		
		return songs;
	}
	
	@Override
	public  boolean songExists(String name) {
	Song song=songRepository.findByName(name);
	if(song==null) {
		return false;
	}
	else {
		return true;
	
	}

}

	public void updateSong(Song s) {
		songRepository.save(s);
	}

	


	
}
