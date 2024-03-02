package com.jsp.Agro.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	private int likes;
	
	@OneToMany(cascade =CascadeType.ALL )
	private List<Comment> comment;
	
	private LocalDateTime dateTime=LocalDateTime.now();
	private String caption;
	private String location;
	
	static DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-YYYY HH:MM:SS");
}
