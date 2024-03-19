package com.jsp.Agro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
	@Query("select e from Equipment e where e.name=?1")
	public List<Equipment> findByName(String name);
	
	@Query("select e from Equipment e where user=?1")
	public List<Equipment> findByUser(User user);
	
	
}
