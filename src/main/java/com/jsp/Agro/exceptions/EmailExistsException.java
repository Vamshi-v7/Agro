package com.jsp.Agro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailExistsException extends RuntimeException {
	private String msg="Email Already Exists...[*_*]";

}
