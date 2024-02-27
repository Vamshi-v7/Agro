package com.jsp.Agro.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public Optional<User> fetchByEmail(String email);

}
