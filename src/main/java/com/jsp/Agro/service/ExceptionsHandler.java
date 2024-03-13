package com.jsp.Agro.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.Agro.exceptions.EmailExistsException;
import com.jsp.Agro.exceptions.ImageAlreadyExist;
import com.jsp.Agro.exceptions.ImageNotFound;
import com.jsp.Agro.exceptions.PasswordIncorrectException;
import com.jsp.Agro.exceptions.PostNotFoundException;
import com.jsp.Agro.exceptions.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<ResponseStructure<String>> emailExists(EmailExistsException e){
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMsg("Email Already Exists");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException e){
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMsg("User doesn't Exists");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PasswordIncorrectException.class)
	public ResponseEntity<ResponseStructure<String>> invalidPassword(PasswordIncorrectException e){
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMsg("Invalid Password");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sQLIntegrity(PasswordIncorrectException e){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMsg("SqL I");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ImageNotFound.class)
	public ResponseEntity<ResponseStructure<String>> noImage(ImageNotFound e){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMsg("Image Not Found");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ImageAlreadyExist.class)
	public ResponseEntity<ResponseStructure<String>> imageExist(ImageAlreadyExist e){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMsg("Image Already Exists");
		rs.setData(e.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	  List<ObjectError> error = ex.getAllErrors(); 
	  Map<String, String> map = new HashMap<String, String>(); 
	  ResponseStructure<Object> structure = new ResponseStructure<>(); 
	 
	  for (ObjectError objectError : error) { 
	   String filedName = ((FieldError) objectError).getField(); 
	   String message = ((FieldError) objectError).getDefaultMessage(); 
	   map.put(filedName, message); 
	 
	  } 
	  structure.setMsg("provide valid details"); 
	  structure.setStatus(HttpStatus.NOT_FOUND.value()); 
	  structure.setData(map); 
	 
	  return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST); 
	 } 
	 
	 @ExceptionHandler(ConstraintViolationException.class) 
	 public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) { 
	  ResponseStructure<Object> structure = new ResponseStructure<>(); 
	  Map<String, String> map = new HashMap<String, String>(); 
	 
	  for (ConstraintViolation<?> violation : ex.getConstraintViolations()) { 
	   String field = violation.getPropertyPath().toString(); 
	   String message = violation.getMessage(); 
	   map.put(field, message); 
	 
	  } 
	  
	  structure.setMsg("provide proper details"); 
	  structure.setStatus(HttpStatus.NOT_FOUND.value()); 
	  structure.setData(map); 
	 
	  return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST); 
	 
	 }
	 
	 @ExceptionHandler(PostNotFoundException.class)
	 public ResponseEntity<ResponseStructure<String>> postNotFound(PostNotFoundException ex){
		 ResponseStructure<String> rs=new ResponseStructure<>();
		 rs.setStatus(HttpStatus.BAD_REQUEST.value());
		 rs.setMsg(ex.getMsg());
		 rs.setData("Post Doesn't exist with given Id");
		 
		 return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	 }

}
