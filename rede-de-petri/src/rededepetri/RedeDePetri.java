package rededepetri;

import java.io.InvalidClassException;
import java.util.ArrayList;

import rededepetri.components.Arco;
import rededepetri.components.Lugar;
import rededepetri.components.Transicao;

public class RedeDePetri {
	
	private ArrayList<Lugar> lugares;
	private ArrayList<Transicao> transicoes = new ArrayList<>();
	private ArrayList<Arco> arcos;
	
	public RedeDePetri() throws InvalidClassException {
		
		Lugar l1 = new Lugar("L1", 1);
		Lugar l3 = new Lugar("L3", 1);

		Transicao t1 = new Transicao("T1");
		
		Lugar l2 = new Lugar("L2", 0);
		Lugar l4 = new Lugar("L4", 0);
		
		Arco a1 = new Arco();
		a1.conectar(l1, t1);
		Arco a3 = new Arco(1);
		a3.conectar(l3, t1);
		
		Arco a2 = new Arco();
		a2.conectar(t1, l2);
		Arco a4 = new Arco(2);
		a4.conectar(t1, l4);
		
		this.transicoes.add(t1);
	}

	public void run() {
		for (Transicao transicao : transicoes) {
			transicao.verificaAtivacao();
		}
		
		ArrayList<Transicao> transicoesAtivas = new ArrayList<>();
		for (Transicao transicao : transicoes) {
			if(transicao.isAtiva()) {
				transicoesAtivas.add(transicao);
			}
		}
		
		for (Transicao transicao : transicoesAtivas) {
			if(transicao.verificaAtivacao()) {
				transicao.ativar();				
			}
		}
	}

}
