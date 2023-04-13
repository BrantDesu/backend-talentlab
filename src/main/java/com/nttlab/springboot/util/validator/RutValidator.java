package com.nttlab.springboot.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RutValidator implements ConstraintValidator<ValidatorRut, String>{
	
	@Override
	public boolean isValid(String rut, ConstraintValidatorContext context) {
		try {
			String[] rut_dv = rut.split("-");
			if (rut_dv.length == 2) {
				int rutValor = Integer.parseInt(rut_dv[0]);
				String DV = rut_dv[1].substring(0).toUpperCase();
				char dv = DV.charAt(0);
				int m = 0, s = 1;
				for (; rutValor != 0; rutValor /= 10) {
					s = (s + rutValor % 10 * (9 - m++ % 6)) % 11;
				}
				return dv == (char) (s != 0 ? s + 47 : 75);
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

