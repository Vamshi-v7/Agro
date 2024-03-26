package com.jsp.Agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.entity.TransactionHistory;
import com.jsp.Agro.service.TransactionService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class TransactionController {
	@Autowired
	TransactionService service;
	
	@PostMapping("saveTransaction")
	public ResponseEntity<ResponseStructure<TransactionHistory>> saveTransaction(@RequestParam("uId")int id,@RequestParam("mode")String mode){
		return service.saveTransaction(id, mode);
	}
	
	@GetMapping("fetchByTransactionId")
	public ResponseEntity<ResponseStructure<TransactionHistory>> fetchByTransactionId(@RequestParam("id")int id){
		return service.fetchById(id);
	}

}
