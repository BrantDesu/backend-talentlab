package com.nttlab.springboot.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidatorEmail, String> {
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(email); 
        return matcher.matches();
	}	

}
