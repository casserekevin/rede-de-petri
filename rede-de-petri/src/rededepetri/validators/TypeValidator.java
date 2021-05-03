package rededepetri.validators;

import java.security.InvalidParameterException;

public class TypeValidator {
	
	public static String validate(String tipo) {
		
		boolean valid = false;
		if(tipo.equals("normal")) {
			valid = true;
		}
		else if(tipo.equals("reset")) {
			valid = true;
		}
		else if(tipo.equals("inhibitor")) {
			valid = true;
		}
		
		
		if(!valid) {
			throw new InvalidParameterException("Tipo inválido");
		}
		
		return tipo;
	}

}
