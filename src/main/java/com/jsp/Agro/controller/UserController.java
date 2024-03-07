package com.jsp.Agro.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.service.UserService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	public static String UserDir=System.getProperty("user.dir")+"/src/main/resources/images";
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> register(@RequestBody User user){
		return service.register(user);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(String email,String password){
		return service.login(email, password);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		return service.deleteUser(id);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<User>> fetchUsr(int id){
		return service.fetchUser(id);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return service.fetchAll();
	}
	
	@PatchMapping("/forgot")
	public ResponseEntity<ResponseStructure<String>> forgotPass(String email,String password,String confirm){
			return service.forgotPass(email, password,confirm);
	}
	
	@PutMapping("/updateImage")
	public ResponseEntity<ResponseStructure<User>> updateUserImage(@RequestParam("id")int id, @RequestParam("image") MultipartFile file) throws IOException{
		User user=new User();
		Image img=new Image();
			img.setName(file.getOriginalFilename());
			img.setImgData(file.getBytes());
			
			Path filenameAndPath = Paths.get(UserDir, file.getOriginalFilename());
			Files.write(filenameAndPath, file.getBytes());
			
		user.setImage(img);
		user.setId(id);
		return service.updateUser(user);
	}
	
	
}
