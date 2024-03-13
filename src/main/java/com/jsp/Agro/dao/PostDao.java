package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.repo.PostRepo;

@Repository
public class PostDao {

	@Autowired
	PostRepo repo;
	
//	To SAVE The Post
	public Post savePost(Post post) {
		return repo.save(post);
	}
	
//	To Update The Existing Post
	public Post updatePost(Post post) {
		Optional<Post> db = repo.findById(post.getId());
		Post dbPost = db.get();
		if(db.isPresent()) {
			if(post.getCaption()!=null)
				dbPost.setCaption(post.getCaption());
			if(post.getLocation()!=null)
				dbPost.setLocation(post.getLocation());
			if(post.getImage()!=null)
				dbPost.setImage(post.getImage());
			if(post.getComment()!=null)
				dbPost.setComment(post.getComment());
			
			return repo.save(db.get());
		}
		return null;
	}
	
//	To Delete The Post
	public Post deletePost(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent()) {
			repo.delete(db.get());
			return db.get();
		}
		return null;
	}
	
//	To Get The Post
	public Post getPost(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent())
			return db.get();
		return null;
	}
	
//	To GET The Comment
	public List<Post> fetchAll() {
		 List<Post> list = repo.findAll();
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
}
