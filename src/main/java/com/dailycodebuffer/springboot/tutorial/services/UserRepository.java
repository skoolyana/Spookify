package com.dailycodebuffer.springboot.tutorial.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.springboot.tutorial.domain.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	  User findByUsername(String username);
}
