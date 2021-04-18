package rededepetri.components;

import java.util.ArrayList;

import rededepetri.interfaces.Conectavel;
import rededepetri.validators.IdValidator;

public class Lugar implements Conectavel{
	
	private int id;
	private String nome;
	private int numero_de_marcas;
	
	private ArrayList<Arco> arcos_de_entrada = new ArrayList<>();
	private ArrayList<Arco> arcos_de_saida = new ArrayList<>();

	public Lugar(int id, String nome, int numero_de_marcas) {
		this.id = IdValidator.validate(id);
		this.setNome(nome);
		this.setNumeroDeMarcas(numero_de_marcas);
	}
	
	public Lugar(int id, String nome) {
		this(id, nome, 0);
	}
	
	public Lugar() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = IdValidator.validate(id);
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNumeroDeMarcas() {
		return numero_de_marcas;
	}

	public void setNumeroDeMarcas(int numero_de_marcas) {
		this.numero_de_marcas = numero_de_marcas;
	}
	
	public void adicionaUmaMarca() {
		this.numero_de_marcas++;
	}
	
	public void adicionarMarcas(int numero_de_marcas) {
		this.numero_de_marcas += numero_de_marcas;
	}
	
	
	public void subtraiUmaMarca() {
		this.numero_de_marcas--;
	}
	
	public void subtrairMarcas(int numero_de_marcas) {
		this.numero_de_marcas -= numero_de_marcas;
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
