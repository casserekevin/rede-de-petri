package rededepetri.components;

import java.util.ArrayList;

import rededepetri.interfaces.Conectavel;
import rededepetri.validators.PriorityValidator;

public class Transicao implements Conectavel{
	
	
	private ArrayList<Arco> arcos_de_entrada = new ArrayList<>();
	private ArrayList<Arco> arcos_de_saida = new ArrayList<>();
	
	private int id;
	private String nome;
	private boolean ativa;
	private int prioridade;
	
	public Transicao(int id, String nome, int prioridade) {
		this.id = id;
		this.nome = nome;
		this.prioridade = PriorityValidator.validate(prioridade);
		this.ativa = false;
	}
	
	public Transicao() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(int prioridade) {
		this.prioridade = PriorityValidator.validate(prioridade);
	}
	
	public boolean isAtiva() {
		return ativa;
	}
	
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
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
			return true;
		}
		else {
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
