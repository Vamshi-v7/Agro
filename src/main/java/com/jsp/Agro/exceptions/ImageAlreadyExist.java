 package com.jsp.Agro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageAlreadyExist extends RuntimeException {
	private String msg="Image Already Exists";
}
