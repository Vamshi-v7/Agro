package com.jsp.Agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
