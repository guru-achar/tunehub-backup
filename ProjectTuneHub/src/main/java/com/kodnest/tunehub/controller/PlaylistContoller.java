package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceImpl;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;


@Controller
public class PlaylistContoller {
	
	@Autowired
	SongServiceImpl songServiceImpl;
	
	@Autowired
	PlaylistServiceImpl playlistServiceImpl;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=songServiceImpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		playlistServiceImpl.addplaylist(playlist);//updating the playlist table
		//updating song table
		List<Song> songList = playlist.getSongs();
		for (Song s : songList) {
			s.getPlaylists().add(playlist);
			songServiceImpl.updateSong(s);
		}
		return "adminhome";
	}
	
	@GetMapping("/viewplaylist")
	public String viewplaylist(Model model) {
		List<Playlist> allplaylist=playlistServiceImpl.featchAllPlaylist();
		model.addAttribute("allplaylist", allplaylist);
		return "displayplaylist";
	}
	
}
