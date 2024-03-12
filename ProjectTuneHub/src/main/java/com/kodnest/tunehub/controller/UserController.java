package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	SongServiceImpl songServiceImpl;


	@PostMapping("/register")
	public String addUsers(@ModelAttribute User user) {
		String email = user.getEmail();//email taken from registration form
		boolean status=userServiceImpl.emailExist(email);//checking if email entered in form is present in db or not
		if(status==false) {
			userServiceImpl.addUsers(user);
			System.out.println("user added");}
		else {
			System.out.println("User already exist");
		}return "login";
	}


	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session,Model model ) {
		if(userServiceImpl.validateUser(email,password)==true) {
			String role= userServiceImpl.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminhome";}
			else 
			{ 
				User user = userServiceImpl.getUser(email);
				boolean userstatus = user.isIspremium();
				
				List<Song> fetchAllSongs = songServiceImpl.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				
				model.addAttribute("ispremium", userstatus);
				return "customerhome";
				}
			}
		else 
		{
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
		
	}



}
