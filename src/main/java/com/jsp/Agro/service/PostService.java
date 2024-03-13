package com.jsp.Agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.PostDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class PostService {
	
	@Autowired
	UserDao uDao;
	
	@Autowired
	PostDao pDao;
	
	public ResponseEntity<ResponseStructure<Post>> savePost(int id,Post post){
		ResponseStructure<Post> rs=new ResponseStructure<>();
		
		User user = uDao.fetchById(id);
		if(user!=null) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Post Created Successfully...  :)");
			rs.setData(pDao.savePost(post));
			
			List<Post> li = user.getPost();
				li.add(post);
//			user.setPost(li);
			uDao.updateUser(user);
			
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("User Doesn't Exist with ID: "+id);
	}
	
	public ResponseEntity<ResponseStructure<Post>> fetchPost(int id){
		ResponseStructure<Post> rs=new ResponseStructure<>();
		
		 Post post = pDao.getPost(id);
		if(post!=null) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Post found Successfully...  :)");
			rs.setData(post);
			
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("Post Doesn't Exist with ID: "+id);
	}
	
	public ResponseEntity<ResponseStructure<Post>> deleteUserPost(int id,int uid){
		ResponseStructure<Post> rs=new ResponseStructure<>();
		User user = uDao.fetchById(uid);
		if(user!=null) {
			Post post = pDao.getPost(id);
			if(post!=null) {
				List<Post> li = user.getPost();
				if(li.contains(post)) {
					System.out.println("Post found : "+post );
					li.remove(post);
					
					rs.setStatus(HttpStatus.CREATED.value());
					rs.setMsg("Post delete Successfully...  :)");
					rs.setData(pDao.deletePost(id));
					
					return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.CREATED);
				}else {
					rs.setStatus(HttpStatus.CREATED.value());
					rs.setMsg("No Post FOUND For User...: "+uid+" with ID "+id);
					rs.setData(null);
					return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.CREATED);
				}

			}
			throw new UserNotFoundException("Post Doesn't Exist with ID: "+id);
		}
	
		throw new UserNotFoundException("User Doesn't Exist with ID: "+id);
	}
	
	public ResponseEntity<ResponseStructure<Post>> deletePost(int id){
		ResponseStructure<Post> rs=new ResponseStructure<>();
		Post post = pDao.getPost(id);
		if(post!=null) {
			List<User> li = uDao.fetchAll();
			for (User user : li) {
				List<Post> posts = user.getPost();
				if(posts.contains(post)) {
					posts.remove(post);
					
					rs.setStatus(HttpStatus.CREATED.value());
					rs.setMsg("Post delete Successfully...  :)");
					rs.setData(pDao.deletePost(id));
					
					return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.ACCEPTED);
				}
			}
		}
		throw new UserNotFoundException("Post Doesn't Exist with ID: "+id);
	}

}
