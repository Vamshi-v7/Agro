package com.jsp.Agro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro.entity.Rental;
import com.jsp.Agro.entity.User;

public interface RentalRepo extends JpaRepository<Rental, Integer> {
	
	@Query("select e from Rental e where user=?1")
	public List<Rental> findByName(User user);

}
