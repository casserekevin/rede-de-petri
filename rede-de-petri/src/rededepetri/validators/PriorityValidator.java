package rededepetri.validators;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class PriorityValidator {
	
	private static ArrayList<Integer> priorities = new ArrayList<>();

	public static Integer validate(Integer priority) {
		
		boolean valid = true;
		if(priority <= 0) {
			valid = false;
		}
		else if(priorities.contains(priority)) {
			valid = false;			
		}
		
		if(!valid) {
			throw new InvalidParameterException("Prioridade inválida");
		}
		
		priorities.add(priority);
		
		return priority;
	}
}
