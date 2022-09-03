package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.User;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.AlreadyExistsException;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.services.UserRepository;
import com.dailycodebuffer.springboot.tutorial.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	 final UserRepository userRepository;


	@Override	
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		 return userRepository.findByUsername(username);
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		  return userRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException(String.format("User ID: %s does not exist", id)));
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		 try {
	            return userRepository.save(user);
	        } catch (Exception e) {
	            throw new AlreadyExistsException(String.format("Username: %s already exists", user.getUsername()));
	        }
	}


	
	
	
}
