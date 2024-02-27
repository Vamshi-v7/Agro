package com.jsp.Agro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hNo;
	private String street;
	private String landMark;
	private String mandal;
	private String district;
	private String state;
	private int pinCODE;

}
