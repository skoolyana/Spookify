package com.dailycodebuffer.springboot.tutorial.services.impl;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.domain.Comment;
import com.dailycodebuffer.springboot.tutorial.domain.Song;
import com.dailycodebuffer.springboot.tutorial.domain.User;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.NotFoundException;
import com.dailycodebuffer.springboot.tutorial.domain.exceptions.UnauthorizedUserException;
import com.dailycodebuffer.springboot.tutorial.repository.CommentRepository;
import com.dailycodebuffer.springboot.tutorial.services.CommentService;
import com.dailycodebuffer.springboot.tutorial.services.SongService;
import com.dailycodebuffer.springboot.tutorial.services.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final SongService songService;
	private final UserService userService;

	@Override
	public Comment findCommentById(Long id) {
		return commentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Comment ID: %s does not exist", id)));
	}

	@Override
	public Iterable<Comment> findCommentsBySongId(Long id) {

		Song song = songService.findSongById(id);
		return song.getComments();

	}

	@Override
	public Comment saveComment(Long id, String content, User user) {
		// TODO Auto-generated method stub

		user = userService.findUserById(user.getId());
		Song song = songService.findSongById(id);

		Comment comment = new Comment();
		comment.setContent(content);
		comment.setUser(user);
		comment.setSong(song);

		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(Long id, String content, User user) {

		user = userService.findUserById(user.getId());

		Comment comment = findCommentById(id);

		if (!comment.getUser().equals(user)) {
			throw new UnauthorizedUserException(
					String.format("User: %s cannot update this comment", user.getUsername()));
		}

		 comment.setContent(content);
	     return commentRepository.save(comment);
	}

	@Override
	public void removeComment(Long id, User user) {
		
		
		user = userService.findUserById(user.getId());

        Comment comment = findCommentById(id);
        if (!comment.getUser().equals(user)) {
            throw new UnauthorizedUserException(String.format("User: %s cannot delete this comment", user.getUsername()));
        }

        commentRepository.delete(comment);
	}

}
