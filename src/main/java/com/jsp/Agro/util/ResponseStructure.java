package com.jsp.Agro.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
	private String msg;
	private int status;
	private T data;
}
