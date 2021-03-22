package rededepetri.components;

import java.util.ArrayList;

import rededepetri.interfaces.Conectaveis;

public class Lugar implements Conectaveis{
	
	private String nome;
	private int numero_de_marcas;
	
	private ArrayList<Arco> arcos_de_entrada;
	private ArrayList<Arco> arcos_de_saida;

	public Lugar(String nome) {
		this.setNome(nome);
		this.setNumeroDeMarcas(0);
	}
	
	public Lugar(String nome, int numero_de_marcas) {
		this.setNome(nome);
		this.setNumeroDeMarcas(numero_de_marcas);
	}

	public int getNumeroDeMarcas() {
		return numero_de_marcas;
	}

	public void setNumeroDeMarcas(int numero_de_marcas) {
		this.numero_de_marcas = numero_de_marcas;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionaUmaMarca() {
		this.numero_de_marcas++;
	}
	
	public void subtraiUmaMarca() {
		this.numero_de_marcas--;
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
