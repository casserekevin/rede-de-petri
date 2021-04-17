package rededepetri;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;

import rededepetri.components.Arco;
import rededepetri.components.Lugar;
import rededepetri.components.Transicao;
import rededepetri.sorters.PrioritySorter;

public class RedeDePetri {
	
	private ArrayList<Transicao> transicoes = new ArrayList<>();
	
	public RedeDePetri() throws InvalidClassException {
		
		Lugar l1 = new Lugar("L1", 2);
		Lugar l2 = new Lugar("L2", 0);
		Lugar l3 = new Lugar("L2", 0);

		Transicao t1 = new Transicao("T1", 1);
		Transicao t2 = new Transicao("T2", 2);
		
		
		Arco a1 = new Arco("a1").conectar(l1, t1);
		Arco a2 = new Arco("a2", 2).conectar(l1, t2);
		
		Arco a3 = new Arco("a3").conectar(t1, l2);
		Arco a4 = new Arco("a4").conectar(t2, l3);
		
		this.transicoes.add(t1);
		this.transicoes.add(t2);
	}

	public void run() {
		while (hasActiveTransitions()) {
			ciclo();			
		}
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
