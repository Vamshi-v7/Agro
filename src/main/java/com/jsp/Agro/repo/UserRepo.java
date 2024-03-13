package com.jsp.Agro.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select e from User e where email=?1")
	public Optional<User> fetchByEmail(String email);
	
	@Query("delete from User e where email=?1")
	public Optional<User> deleteByEmail(String email);
	
	@Query("select e from User e where image=?1")
	public Optional<User> fetchByImage(Image img);
	

}
