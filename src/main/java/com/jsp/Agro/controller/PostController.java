package com.jsp.Agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.service.PostService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class PostController {
	
	@Autowired
	PostService service;
	
	@PostMapping("savePost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam("id")int id,@RequestParam("file")MultipartFile file,String caption,String location) throws IOException{
		Image img=new Image();
		img.setImgData(file.getBytes());
		img.setName(file.getOriginalFilename());
		
		Post post=new Post();
		post.setCaption(caption);
		post.setLocation(location);
		post.setImage(img);
		
		return service.savePost(id, post);
	}
	
	@GetMapping("fetchPost")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@RequestParam("id")int id){
		
		return service.fetchPost(id);
	}
	
	@DeleteMapping("deleteUserPost")
	public ResponseEntity<ResponseStructure<Post>> deleteUserPost(@RequestParam("id")int id,@RequestParam("uid")int uid){
		
		return service.deleteUserPost(id,uid);
	}
	
	@DeleteMapping("deletePost")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam("id")int id){
		
		return service.deletePost(id);
	}

}
