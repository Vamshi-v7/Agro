package com.jsp.Agro.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.EmailExistsException;
import com.jsp.Agro.exceptions.IdNotFoundException;
import com.jsp.Agro.exceptions.PasswordIncorrectException;
import com.jsp.Agro.util.ResponseStructure;

import jakarta.mail.internet.MimeMessage;

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
			MimeMessage mailMessage=javaSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mailMessage, true);
			
			helper.setFrom("pitlavamshikrishna1502@gamil.com");
			helper.setTo(user.getEmail());
			helper.setText("\r\n"
					+ "Subject: Welcome to Agro Application\r\n"
					+ "\r\n"
					+ "Dear "+user.getFirstName()+"\r\n"
					+ "\r\n"
					+ "Welcome to Agro Application! We are thrilled to have you on board and are excited about the journey ahead. Your registration is complete, and you are now a valued member of our community.\r\n"
					+ "\r\n"
					+ "Here at our Agro Application, we are committed to revolutionizing the way you experience agriculture. Whether you are a seasoned farmer, a hobbyist gardener, or someone passionate about sustainable living, our platform offers a range of tools and resources to support your agricultural endeavors."
					+ "\n\n\n\nThanks & regards,\nAgro Resource pvt.Ltd\nMail-Us : agroprojectbatch3@gmail.com\n\n");
			helper.setSubject("Welcome to Our Agrow Application(Team-3)");
			
			FileSystemResource file = new FileSystemResource(new File("C:/Users/Parsha Bharath Kumar/Downloads/Agro/Agro/src/main/resources/images/agro image.jpg"));
			helper.addAttachment(file.getFilename(), file);
			
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
		User db = dao.fetchByEmail(email);
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
			throw new IdNotFoundException("User Not Found With The Email:"+email);
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
		throw new IdNotFoundException("User Not Found For id:"+id);
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
		throw new IdNotFoundException("No Users Found");
		
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
		throw new IdNotFoundException("User Not Found For id:"+user.getId());
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
		throw new IdNotFoundException("User Not Found For id:"+id);
	}
	
//7.	OTP Generator
	public static int  generateOTP() {
		int pin=(int)(Math.random()*9000)+1000;
		
		return pin;
	}
	
//	Forgot Password
	public ResponseEntity<ResponseStructure<String>> forgotPass(String email,String password,String confirm){
		
		if(password.equals(confirm)) 
		{
			User dbUser = dao.fetchByEmail(email);
			if(dbUser!=null) {
				 int otp=generateOTP();
				 
				 SimpleMailMessage message=new SimpleMailMessage();
				 	message.setFrom("pitlavamshikrishna1502@gamil.com");
				 	message.setTo(email);
				 	message.setSubject("Authenticating The User!");
				 	message.setText("Dear "+dbUser.getFirstName()+",\nYou have generated an action to Update Your Password ! If Not Report.\n\nOTP : ["+otp+"]   is an OTP to update Your Password. "
				 			+ "Please Enter the OTP to proceed and Do not share it with anyone. \n\nWish you the best!\n\n"
				 			+ "Regars\nTeam Agro\nAgro India PVT LTD.");
			
				 	javaSender.send(message);
				 	
				 	dbUser.setPassword(password);
				 	ResponseStructure<String> rs=new ResponseStructure<>();
//				 	User db = dao.modifyUser(email, password);
				 	User db = dao.updateUser(dbUser);
		
				 		rs.setStatus(HttpStatus.ACCEPTED.value());
				 		rs.setMsg("Password Updated");
				 		rs.setData("Password Updated Successfully for email: "+db);
			
				 		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ACCEPTED);			 	
			}
			throw new IdNotFoundException("User NOT Found With Email: "+email +" Please Register..!"); 
		}
		throw new PasswordIncorrectException("New Password: "+password+" And Confirm Password: "+confirm+" Both are not equal");
	}

}
