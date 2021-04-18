package rededepetri.key_listeners;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import rededepetri.RedeDePetri;

public class MyNativeKeyListener implements NativeKeyListener{
	
	RedeDePetri rede;
	
	public MyNativeKeyListener(RedeDePetri rede) {
		this.rede = rede;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nke) {
		if(nke.getKeyCode() == NativeKeyEvent.VC_ENTER) {
			System.out.println(NativeKeyEvent.getKeyText(nke.getKeyCode()));
			if(!rede.run()) {
				System.exit(0);
			}
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nke) {
		// TODO Auto-generated method stub
		
	}

}
