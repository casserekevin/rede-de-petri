package main;

import java.io.InvalidClassException;

import rededepetri.RedeDePetri;

public class Main {

	public static void main(String[] args) throws InvalidClassException {
		System.out.println("Iniciando...");
		
		RedeDePetri rede = new RedeDePetri("test.pnml.xml");
		rede.run();

		System.out.println("Finalizou...");
	}

}
