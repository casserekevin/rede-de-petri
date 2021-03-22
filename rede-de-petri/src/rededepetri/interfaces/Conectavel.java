package rededepetri.interfaces;

import rededepetri.components.Arco;

public interface Conectavel {
	
	public void conectarEntrada(Arco arco);
	
	public void conectarSaida(Arco arco);

}
