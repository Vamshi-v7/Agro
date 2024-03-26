package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Rental;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.repo.RentalRepo;

@Repository
public class RentalDao {
	@Autowired
	RentalRepo repo;
	
	
	public Rental save(Rental rent) {
		return repo.save(rent);
	}
	
	public Rental fetchById(int id) {
		Optional<Rental> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public List<Rental> fetchByUser(User user) {
		return repo.findByName(user);
	}
}
