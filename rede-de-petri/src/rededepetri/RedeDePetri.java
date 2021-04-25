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
	
	private ArrayList<Lugar> lugares = new ArrayList<>();
	private ArrayList<Transicao> transicoes = new ArrayList<>();
	//private ArrayList<Arco> arcos = new ArrayList<>();
	
	private int ciclo = 0;
	
	private boolean hasCabecalho = false;
	private String dataString = "";
	
	@SuppressWarnings("unchecked")
	public RedeDePetri(String filename) throws InvalidClassException {
		
		PNMLReader reader = new PNMLReader();
		ArrayList<Object> rede = reader.read(filename);
		
		lugares = (ArrayList<Lugar>) rede.get(0);
		transicoes = (ArrayList<Transicao>) rede.get(1);
		//arcos = (ArrayList<Arco>) rede.get(2);
		System.out.println("rede criada");
		
	}

	public boolean run() {
		if (hasActiveTransitions()) {
			ciclo();
			
			return true;
		}
		
		showData();
		return false;
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
		
		showData();
		
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
		
		
		ciclo++;
		//Transicoes voltarem ao estado inicial
		for (Transicao transicao : transicoes) {
			transicao.setAtiva(false);
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
	
	private void showData() {
		
		if(!hasCabecalho) {
			appendCabecalho();
			appendData();
		}
		else {
			appendData();
		}
			
		System.out.println(dataString);
	}
	
	private void appendCabecalho() {
		String cabecalho = "| Número do ciclo ";
		for (Lugar lugar : lugares) {
			cabecalho = cabecalho + "| " + lugar.getNome() + " ";
		}
		int index = 0;
		for (Transicao transicao : transicoes) {
			if(index == transicoes.size() - 1) {
				cabecalho = cabecalho + "| " + transicao.getNome() + " |";				
			}
			else {
				cabecalho = cabecalho + "| " + transicao.getNome() + " ";				
			}
			
			index++;
		}
		
		dataString = dataString + cabecalho + "\n";
		hasCabecalho = true;
	}
	
	private void appendData() {
		String dados = "";
		if(ciclo == 0) {
			dados = dados + "|      " + ciclo + " (Início) ";			
		}
		else {
			dados = dados + "|      " + ciclo + "          ";
		}
		
		for (Lugar lugar : lugares) {
			dados = dados + "|  " + lugar.getNumeroDeMarcas() + " ";
		}
		int index = 0;
		for (Transicao transicao : transicoes) {
			if(index == transicoes.size() - 1) {
				dados = dados + "|  " + transicao.getAtivaToString() + " |";				
			}
			else {
				dados = dados + "|  " + transicao.getAtivaToString() + " ";								
			}
			
			index++;
		}
		
		dataString = dataString + dados + "\n";
	}

}
