package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public String addUsers( User user) {
		userRepository.save(user);
		return "user added success";
	}

	//logic to check the duplicate entry
	public boolean emailExist(String email) {
		if(userRepository.findByEmail(email)!=null) {
			return true;
			}
		else {
			return false;

		}
	}

	public boolean validateUser(String email, String password) {
	     User user = userRepository.findByEmail(email);
	       String dbpassword=user.getPassword();
	       if(password.equals(dbpassword)) {
	    	   return true;
	       }
	       else {
	    	   return false;
	       }
	}

	public String getRole(String email) {
		User user = userRepository.findByEmail(email);
		return user.getRole();
	}
	
	public User getUser(String email) {
		return userRepository.findByEmail(email);
		
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}

	
}

	
