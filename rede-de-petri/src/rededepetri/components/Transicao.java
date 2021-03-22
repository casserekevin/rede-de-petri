package rededepetri.components;

import java.util.ArrayList;

import rededepetri.interfaces.Conectavel;

public class Transicao implements Conectavel{
	
	private String nome;
	
	private ArrayList<Arco> arcos_de_entrada = new ArrayList<>();
	private ArrayList<Arco> arcos_de_saida = new ArrayList<>();;
	
	private boolean ativa;
	
	public Transicao(String nome) {
		this.nome = nome;
		this.ativa = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAtiva() {
		return ativa;
	}

	public boolean verificaAtivacao() {
		int contador_de_validos = 0;
		
		for (Arco arco : arcos_de_entrada) {
			Lugar entrada = (Lugar) arco.getEntrada();
			
			if(entrada.getNumeroDeMarcas() >= arco.getPeso()) {
				contador_de_validos++;
			}
		}
		
		if(contador_de_validos == this.arcos_de_entrada.size()) {
			this.ativa = true;
			return true;
		}
		else {
			this.ativa = false;
			return false;
		}
		
	}

	public void ativar() {
		for (Arco arco : arcos_de_entrada) {
			Lugar entrada = (Lugar) arco.getEntrada();
			
			entrada.subtrairMarcas(arco.getPeso());
		}
		
		for (Arco arco : arcos_de_saida) {
			Lugar saida = (Lugar) arco.getSaida();
			
			saida.adicionarMarcas(arco.getPeso());
		}
	}
	
	@Override
	public void conectarEntrada(Arco arco) {
		this.arcos_de_entrada.add(arco);
	}

	@Override
	public void conectarSaida(Arco arco) {
		this.arcos_de_saida.add(arco);
	}
}
