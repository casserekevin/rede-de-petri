package rededepetri;

import java.io.InvalidClassException;
import java.util.ArrayList;

import rededepetri.components.Arco;
import rededepetri.components.Lugar;
import rededepetri.components.Transicao;

public class RedeDePetri {
	
	private ArrayList<Lugar> lugares;
	private ArrayList<Transicao> transicoes;
	private ArrayList<Arco> arcos;
	
	public RedeDePetri() throws InvalidClassException {
		
		Lugar l1 = new Lugar("L1", 1);

		Transicao t1 = new Transicao("T1");
		
		Lugar l2 = new Lugar("L2");
		
		Arco a1 = new Arco(l1, t1);
		
		Arco a2 = new Arco(t1, l2);
		
	}

	public void run() {
		System.out.println("Running...");
	}

}
