package rededepetri.components;

import java.io.InvalidClassException;

import rededepetri.interfaces.Conectavel;

public class Arco {
	private Conectavel entrada;
	private Conectavel saida;
	
	private int peso;

	public Arco(int peso) {
		this.peso = peso;
	}
	
	public Arco() throws InvalidClassException {
		this(1);
	}
	
	public void conectar(Conectavel c1, Conectavel c2) throws InvalidClassException {
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
		}				
	}

	public Conectavel getEntrada() {
		return entrada;
	}

	public Conectavel getSaida() {
		return saida;
	}

	public int getPeso() {
		return peso;
	}
	
	
}
