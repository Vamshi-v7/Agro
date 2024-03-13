package com.jsp.Agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.service.CommentService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam("pId")int pId,@RequestParam("uId")int uId,@RequestParam("msg")String msg){
		return service.saveComment(pId, uId, msg);
	}
	
	@GetMapping("fetchComment")
	public ResponseEntity<ResponseStructure<Comment>> getComment(@RequestParam("id")int id){
		return service.fetchComment(id);
	}
	
	@DeleteMapping("deleteComment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam("pId")int pId,@RequestParam("cId")int cId){
		return service.deleteComment(pId, cId);
	}

}
