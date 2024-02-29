package com.jsp.Agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.EmailExistsException;
import com.jsp.Agro.exceptions.PasswordIncorrectException;
import com.jsp.Agro.exceptions.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	@Autowired
	JavaMailSender javaSender;
	
//1.	Register
	public ResponseEntity<ResponseStructure<User>> register(User user){
		ResponseStructure<User> rs=new ResponseStructure<>();
		User db = dao.register(user);
		if(db!=null) {
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Registered Successfully....!");
			rs.setData(db);
			
			try {
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			
			mailMessage.setFrom("pitlavamshikrishna1502@gamil.com");
			mailMessage.setTo(user.getEmail());
			mailMessage.setText("\r\n"
					+ "Subject: Welcome to Our Agro Application\r\n"
					+ "\r\n"
					+ "Dear "+user.getFirstName()+"\r\n"
					+ "\r\n"
					+ "Welcome to Our Agro Application! We are thrilled to have you on board and are excited about the journey ahead. Your registration is complete, and you are now a valued member of our community.\r\n"
					+ "\r\n"
					+ "Here at our Agro Application, we are committed to revolutionizing the way you experience agriculture. Whether you are a seasoned farmer, a hobbyist gardener, or someone passionate about sustainable living, our platform offers a range of tools and resources to support your agricultural endeavors.");
			mailMessage.setSubject("Welcome to Our Agro Application");
			javaSender.send(mailMessage);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		}throw new EmailExistsException("Email Already Exists With :"+user.getEmail());
	}
//2.	Login
	public ResponseEntity<ResponseStructure<User>> login(String email,String password){
		ResponseStructure<User> rs=new ResponseStructure<>();
		User db = dao.login(email);
		if(db!=null) {
			if(password.equals(db.getPassword()))
			{
				rs.setStatus(HttpStatus.ACCEPTED.value());
				rs.setMsg("Login Successfull....!");
				rs.setData(db);
				
				return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.ACCEPTED);
			}else {
				throw new PasswordIncorrectException("Incorrect Password For Email:"+email);
			}
		}else {
			throw new UserNotFoundException("User Not Found With The Email:"+email);
		}
	}
//3.	Fetch User
	public ResponseEntity<ResponseStructure<User>> fetchUser(int id){
		ResponseStructure<User> rs=new ResponseStructure<>();
		User db = dao.fetchById(id);
		if(db!=null) {
			rs.setStatus(HttpStatus.OK.value());
			rs.setMsg("User Fetched Successfully....!");
			rs.setData(db);
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		throw new UserNotFoundException("User Not Found For id:"+id);
	}
	
//4.	FetchAll
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		ResponseStructure<List<User>> rs=new ResponseStructure<>();
		 List<User> db = dao.fetchAll() ;
		 
		if(db.size()!=0) {
			rs.setStatus(HttpStatus.OK.value());
			rs.setMsg("All Users Fetched Successfully....!");
			rs.setData(db);
			return new ResponseEntity<ResponseStructure<List<User>>>(rs,HttpStatus.OK);
		}
		throw new UserNotFoundException("No Users Found");
		
	}
	
//5.	Update User
	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		ResponseStructure<User> rs=new ResponseStructure<>();
		User db = dao.updateUser(user);
		if(db!=null) {
			rs.setStatus(HttpStatus.OK.value());
			rs.setMsg("User Updated Successfully....!");
			rs.setData(db);
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		throw new UserNotFoundException("User Not Found For id:"+user.getId());
	}
	
//6.	Delete User
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		ResponseStructure<User> rs=new ResponseStructure<>();
		User db = dao.deleteUser(id);
		if(db!=null) {
			rs.setStatus(HttpStatus.OK.value());
			rs.setMsg("User deleted Successfully....!");
			rs.setData(db);
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		throw new UserNotFoundException("User Not Found For id:"+id);
	}
	
//7.	OTP Generator
	public static int  generateOTP() {
		int pin=(int)(Math.random()*9000)+1000;
		
		return pin;
	}
	
//	Forgot Password
	public ResponseEntity<ResponseStructure<String>> forgotPass(String email,String password,String confirm){
		if(password.equals(confirm)) {
		ResponseStructure<String> rs=new ResponseStructure<>();
		User db = dao.modifyUser(email, password);
		if(db!=null) {
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setMsg("Password Updated");
			rs.setData("Password Updated Successfully for email: "+email);
			
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("User NOT Found With Email: "+email);
	  }
	  throw new PasswordIncorrectException("New Password: "+password+" And Confirm Password: "+confirm+" Both are not equal");
	}


}
