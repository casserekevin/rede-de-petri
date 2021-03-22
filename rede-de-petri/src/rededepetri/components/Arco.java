package rededepetri.components;

import java.io.InvalidClassException;

import rededepetri.interfaces.Conectaveis;

public class Arco {
	private Conectaveis entrada;
	private Conectaveis saida;

	public Arco(Conectaveis c1, Conectaveis c2) throws InvalidClassException {
		boolean isOk = false;
		
		Conectaveis con1 = null;
		Conectaveis con2 = null;
		
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
}
