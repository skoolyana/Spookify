package com.dailycodebuffer.springboot.tutorial.services;
import com.dailycodebuffer.springboot.tutorial.domain.Comment;
import com.dailycodebuffer.springboot.tutorial.domain.User;



public interface CommentService {

	

	  
	  public Comment findCommentById(Long id);
	  
	  public Iterable<Comment> findCommentsBySongId(Long id);
	  
	  public Comment saveComment(Long id, String content, User user);
	  
	  public Comment updateComment(Long id, String content, User user);
	  
	  public void removeComment(Long id, User user);
	  
	  
	
	
}
