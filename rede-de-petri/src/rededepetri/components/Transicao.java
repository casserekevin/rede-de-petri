package rededepetri.components;

import java.util.ArrayList;

import rededepetri.interfaces.Conectaveis;

public class Transicao implements Conectaveis{
	
	private String nome;
	
	private ArrayList<Arco> arcos_de_entrada;
	private ArrayList<Arco> arcos_de_saida;
	
	public Transicao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
