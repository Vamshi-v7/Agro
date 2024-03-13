package com.jsp.Agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.CommentDao;
import com.jsp.Agro.dao.PostDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.PostNotFoundException;
import com.jsp.Agro.exceptions.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class CommentService {
	
	@Autowired
	CommentDao cdao;
	
	@Autowired
	PostDao pDao;
	
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<Comment>> saveComment(int pId,int uId,String msg){
		ResponseStructure<Comment> rs=new ResponseStructure<>();
		Post post = pDao.getPost(pId);
		if(post!=null) {
			User user = uDao.fetchById(uId);
			if(user!=null) {
				Comment com=new Comment();
				com.setText(msg);
				com.setUser(user);
				
				cdao.saveComment(com);
				List<Comment> list = post.getComment();
					list.add(com);
					
					pDao.updatePost(post);
					
				rs.setStatus(HttpStatus.CREATED.value());
				rs.setMsg("Comment Saved Successfully...");
				rs.setData(com);
				
				return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.CREATED);
				
			}
			throw new UserNotFoundException("User Doesn't Exist With ID :"+uId);
		}
		throw new PostNotFoundException("Post Doesn't Exist With ID: "+ pId);
	}
	
	public ResponseEntity<ResponseStructure<Comment>> fetchComment(int id){
		ResponseStructure<Comment> rs=new ResponseStructure<>();
		Comment comment = cdao.getComment(id);
		if(comment!=null) {
				
				rs.setStatus(HttpStatus.CREATED.value());
				rs.setMsg("Comment fetched Successfully...");
				rs.setData(comment);
				
				return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.CREATED);
		}
		throw new PostNotFoundException("Post Doesn't Exist With ID: "+ id);
	}
	
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int pId,int cId){
		ResponseStructure<Comment> rs=new ResponseStructure<>();
		Post post = pDao.getPost(pId);
		if(post!=null) {
			Comment comment = cdao.getComment(cId);
			if(comment!=null) {
				List<Comment> list = post.getComment();
				if(list.contains(comment)) {
					list.remove(comment);
				}
				rs.setStatus(HttpStatus.CREATED.value());
				rs.setMsg("Comment deleted Successfully...");
				rs.setData(cdao.deleteComment(cId));
			
				return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.CREATED);
			}
		}
		throw new PostNotFoundException("Post Doesn't Exist With ID: "+ pId);
	}
	
	
}
