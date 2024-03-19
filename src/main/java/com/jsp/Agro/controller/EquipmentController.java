package com.jsp.Agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.service.EquipmentService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class EquipmentController {
	@Autowired
	EquipmentService service;
	
	@PostMapping("saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> save(@RequestParam("uId")int uId,@RequestParam String name,@RequestParam double cost,@RequestParam int qty){
		Equipment equipment=new Equipment();
		equipment.setCostPHour(cost);
		equipment.setName(name);
		equipment.setQuantity(qty);
		
		return service.save(equipment, uId);
	}
	
	@GetMapping("getById")
	public ResponseEntity<ResponseStructure<Equipment>> getById(@RequestParam("id")int id){
		
		return service.fetchById(id);
	}
	
	@GetMapping("getByName")
	public ResponseEntity<ResponseStructure<List<Equipment>>> getByName(@RequestParam String name){
		
		return service.fetchByName(name);
	}
	
	@GetMapping("getByUser")
	public ResponseEntity<ResponseStructure<List<Equipment>>> getByUser(@RequestParam("id")int uId){
		
		return service.fetchByUser(uId);
	}
	
	@GetMapping("getAll")
	public ResponseEntity<ResponseStructure<List<Equipment>>> getByAll(){
		return service.fetchAll();
	}

}
