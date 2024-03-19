package com.jsp.Agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.EquipmentDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class EquipmentService {
	
	@Autowired
	EquipmentDao eDao;
	
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<Equipment>> save(Equipment equipment,int uID){
		ResponseStructure<Equipment> rs=new ResponseStructure<>();
		User user = uDao.fetchById(uID);
		if(user!=null) {
			equipment.setUser(user);
			Equipment db = eDao.save(equipment);
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Equipment Saved Successfully...!");
			rs.setData(db);
			
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("User Not Exist with ID:"+uID);
	}
	public ResponseEntity<ResponseStructure<Equipment>> fetchById(int id){
		ResponseStructure<Equipment> rs=new ResponseStructure<>();
		Equipment equipment = eDao.fetchById(id);
		if(equipment!=null) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Equipment fetched Successfully...!");
			rs.setData(equipment);
			
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("User Not Exist with ID:"+id);
	}
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchByName (String name){
		ResponseStructure<List<Equipment>> rs=new ResponseStructure<>();
		List<Equipment> equipment = eDao.fetchByName(name);
		if(equipment.size()!=0) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Equipment Saved Successfully...!");
			rs.setData(equipment);
			
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("No Equipments Found with name :"+name);
	}
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchByUser(int uID){
		ResponseStructure<List<Equipment>> rs=new ResponseStructure<>();
		User user = uDao.fetchById(uID);
		if(user!=null) {
			
			List<Equipment> db = eDao.fetchByUser(user);
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Equipment Saved Successfully...!");
			rs.setData(db);
			
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("User Not Exist with ID:"+uID);
	}
	
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchAll(){
		ResponseStructure<List<Equipment>> rs=new ResponseStructure<>();
		List<Equipment> list = eDao.fetchAll();
		if(list.size()!=0) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Equipment Saved Successfully...!");
			rs.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFoundException("No Equipments Found");
	}

}
