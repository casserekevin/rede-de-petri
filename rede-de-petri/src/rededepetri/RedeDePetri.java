package rededepetri;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;

import rededepetri.components.Arco;
import rededepetri.components.Lugar;
import rededepetri.components.Transicao;
import rededepetri.reader.PNMLReader;
import rededepetri.sorters.PrioritySorter;

public class RedeDePetri {
	
	private ArrayList<Transicao> transicoes = new ArrayList<>();
	
	public RedeDePetri(String filename) throws InvalidClassException {
		
		PNMLReader reader = new PNMLReader();
		ArrayList<Object> rede = reader.read(filename);
		
		transicoes = (ArrayList<Transicao>) rede.get(1);
		System.out.println("rede criada");
		
	}

	public void run() {
		System.out.println("Executando...");
		while (hasActiveTransitions()) {
			ciclo();			
		}
		System.out.println("Executou...");
	}
	
	private void ciclo() {
		for (Transicao transicao : transicoes) {
			if(transicao.verificaAtivacao()) {
				transicao.setAtiva(true);
			}
			else {
				transicao.setAtiva(false);
			}
		}
		
		ArrayList<Transicao> transicoesAtivas = new ArrayList<>();
		for (Transicao transicao : transicoes) {
			if(transicao.isAtiva()) {
				transicoesAtivas.add(transicao);
			}
		}
		
		transicoesAtivas.sort(new PrioritySorter());
		
		for (Transicao transicao : transicoesAtivas) {
			if(transicao.verificaAtivacao()) {
				transicao.ativar();				
			}
		}
	}
	
	private boolean hasActiveTransitions() {
		int counter = 0;
		for (Transicao transicao : transicoes) {
			if(transicao.verificaAtivacao()) {
				counter++;
			}
		}
		
		return counter > 0 ? true : false;
	}

}
