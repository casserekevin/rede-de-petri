package main;

import java.io.InvalidClassException;

import rededepetri.RedeDePetri;

public class Main {

	public static void main(String[] args) throws InvalidClassException {
		System.out.println("Beginning...");
		
		RedeDePetri rede = new RedeDePetri();
		rede.run();

		System.out.println("End");
	}

}
