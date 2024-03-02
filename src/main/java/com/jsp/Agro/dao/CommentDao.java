package com.jsp.Agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.repo.CommentRepo;

@Repository
public class CommentDao {
	
	@Autowired
	CommentRepo repo;
	
//	To Save The Comment
	public Comment saveComment(Comment comment) {
		return repo.save(comment);
	}
	
//	To Edit The Comment
	public Comment editComment(Comment comment) {
		Optional<Comment> opt = repo.findById(comment.getId());
		Comment db = opt.get();
		if(opt.isPresent())
		{
			if(comment.getText()!=null)
				db.setText(comment.getText());
			if(comment.getUser()!=null)
				db.setUser(comment.getUser());
			
			return repo.save(db);
		}
		return null;
	}
	
//	To Delete The Comment
	public Comment deleteComment(int id) {
		Optional<Comment> opt = repo.findById(id);
		if(opt.isPresent()) {
			repo.delete(opt.get());
			return opt.get();
		}
		return null;
	}
	
//	To GET The Comment
	public Comment getComment(int id) {
		Optional<Comment> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

}
