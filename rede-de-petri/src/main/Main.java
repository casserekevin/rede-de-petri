package main;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rededepetri.RedeDePetri;
import rededepetri.key_listeners.MyNativeKeyListener;

public class Main {

	public static void main(String[] args) throws InvalidClassException {

		System.out.println("Iniciando...");
		RedeDePetri rede = new RedeDePetri("inicializacao.pnml");
		
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.SEVERE);
		logger.setUseParentHandlers(false);
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GlobalScreen.addNativeKeyListener(new MyNativeKeyListener(rede));
//		rede.run();
//
//		System.out.println("Finalizou...");
	}

}
