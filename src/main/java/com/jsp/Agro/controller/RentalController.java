package com.jsp.Agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.dao.EquipmentDao;
import com.jsp.Agro.entity.Rental;
import com.jsp.Agro.service.RentalService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class RentalController {
	
	@Autowired
	RentalService service;
	
	@Autowired
	EquipmentDao eDao;
	@PostMapping("saveRent")
	public ResponseEntity<ResponseStructure<Rental>> saveRent(@RequestBody Rental rent,@RequestParam("uId") int uId,@RequestParam("eId") int eId){
		
		return service.save(rent, uId,eId);
	}
	
	@GetMapping("fetchRentById")
	public ResponseEntity<ResponseStructure<Rental>> fetchById(@RequestParam int id){
		return service.fetchById(id);
	}

}
