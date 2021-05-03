package rededepetri.validators;

import java.security.InvalidParameterException;

public class NameValidator {

	public static String validate(String name) {
		
		boolean maiorQue3 = false;
		boolean menorQue1 = false;
		if(name.length() > 3) {
			maiorQue3 = true;
		}
		if(name.length() < 1) {
			menorQue1 = true;
		}
		
		
		if(maiorQue3) {
			throw new InvalidParameterException("Nome maior que 3 caracteres");
		}
		if(menorQue1) {
			throw new InvalidParameterException("Nome menor que 1 caracter");
		}
		return name;
	}
}
