package com.jsp.Agro.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	
	@Query("select i from Image i where name=?1")
	public Optional<Image> findByName(String name);

}
