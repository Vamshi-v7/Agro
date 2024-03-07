package com.jsp.Agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.entity.Image;
import com.jsp.Agro.service.ImageService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class ImageController {
	
	@Autowired
	ImageService service;
	
	@PatchMapping("editImage")
	public ResponseEntity<ResponseStructure<Image>> editImage(@RequestParam("id") int id,@RequestParam("image") MultipartFile file) throws IOException{
		
		return service.editImage(file, id);
	}
	
	@GetMapping("fetchImage")
	public ResponseEntity<byte[]> fetchImage(@RequestParam("id") int id){

		return service.fetchImage(id);
	}
	
	
	@DeleteMapping("deleteImage")
	public ResponseEntity<ResponseStructure<Image>> deleteImage(@RequestParam("id") int id){

		return service.deleteImage(id);
	}
	


}
