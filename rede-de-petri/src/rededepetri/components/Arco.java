package rededepetri.components;

import java.io.InvalidClassException;

import rededepetri.interfaces.Conectavel;
import rededepetri.validators.IdValidator;
import rededepetri.validators.NameValidator;
import rededepetri.validators.TypeValidator;

public class Arco {
	private Conectavel entrada;
	private Conectavel saida;
	
	private int id;
	private String nome;
	private int peso;
	private String tipo;

	public Arco(int id, String nome, int peso, String tipo) {
		this.id = IdValidator.validate(id);
		this.nome = NameValidator.validate(nome);
		this.peso = peso;
		this.tipo = TypeValidator.validate(tipo);
	}
	
	public Arco(int id, String nome) {
		this(id, nome, 1, "normal");
	}
	
	public Arco() {
		
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
		this.nome = NameValidator.validate(nome);
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = TypeValidator.validate(tipo);
	}
	
	public boolean isNormal() {
		return tipo.equals("normal");
	}
	
	public boolean isReset() {
		return tipo.equals("reset");
	}
	
	public boolean isInhibitor() {
		return tipo.equals("inhibitor");
	}

	public Arco conectar(Conectavel c1, Conectavel c2) throws InvalidClassException {
		boolean isOk = false;
		
		Conectavel con1 = null;
		Conectavel con2 = null;
		
		try {
			Lugar lugar = (Lugar) c1;
			Transicao transicao = (Transicao) c2;
			
			con1 = lugar;
			con2 = transicao;
			isOk = true;
		} catch (ClassCastException e) {
			try {
				Transicao transicao = (Transicao) c1;	
				Lugar lugar = (Lugar) c2;
				
				
				con1 = transicao;
				con2 = lugar;
				isOk = true;
			} catch (ClassCastException e2) {
				
				isOk = false;
				
				throw new InvalidClassException("Conexão inválida"); 
			}
		}
		
		if(isOk) {
			con1.conectarSaida(this);
			con2.conectarEntrada(this);
			
			this.entrada = con1;
			this.saida = con2;
			
			return this;
		}
		return null;				
	}

	public Conectavel getEntrada() {
		return entrada;
	}

	public Conectavel getSaida() {
		return saida;
	}	
}
