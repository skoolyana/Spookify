package com.dailycodebuffer.springboot.tutorial.services;

import com.dailycodebuffer.springboot.tutorial.domain.User;

public interface UserService{

	
	 public User findUserByUsername(String username);
	 
	 public User findUserById(Long id);
	  
	 public User saveUser(User user);
	 

}
