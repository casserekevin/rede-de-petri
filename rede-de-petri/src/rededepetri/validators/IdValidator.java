package rededepetri.validators;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class IdValidator {
	private static ArrayList<Integer> ids = new ArrayList<>();

	public static Integer validate(Integer id) {
		
		boolean valid = true;
		if(id <= 0) {
			valid = false;
		}
		else if(ids.contains(id)) {
			valid = false;			
		}
		
		if(!valid) {
			throw new InvalidParameterException("Id inválido");
		}
		
		ids.add(id);
		
		return id;
	}
}
