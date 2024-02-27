package com.jsp.Agro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordIncorrectException extends RuntimeException {
   private String msg="Invalid Password";
}
