package com.jsp.Agro.entity;

import java.util.List;
import com.jsp.Agro.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Field cannot be Null")
	@NotBlank(message = "Field Cannot be Blank")
	private String firstName;
	
	@NotNull(message = "Field cannot be Null")
	@NotBlank(message = "Field Cannot be Blank")
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private long phone;
	private String password;
	private String gender;
	private int age;
	
	@Enumerated(value = EnumType.STRING)
	private UserType type;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;
}
