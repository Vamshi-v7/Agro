package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.repo.EquipmentRepo;

@Repository
public class EquipmentDao {
	@Autowired
	EquipmentRepo repo;
	
	public Equipment save(Equipment equipment) {
		return repo.save(equipment);
	}
	
	public Equipment fetchById(int id) {
		Optional<Equipment> opt = repo.findById(id);
		if(opt.isPresent())
			return opt.get();
		return null;
	}
	
	public List<Equipment>  fetchByName(String name) {
		 return repo.findByName(name);
	}
	public List<Equipment>  fetchByUser(User user) {
		return repo.findByUser(user);
	}
	
	public List<Equipment>  fetchAll() {
		return repo.findAll();
	}
	
	
}
