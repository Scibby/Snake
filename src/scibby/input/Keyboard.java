package scibby.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import scibby.events.EventListener;
import scibby.events.types.keyboard.KeyPressedEvent;
import scibby.events.types.keyboard.KeyReleasedEvent;

public class Keyboard implements KeyListener{

	private static boolean[] keys = new boolean[65536];
	
	private EventListener listener;
	
	public Keyboard(EventListener listener){
		this.listener = listener;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
		KeyPressedEvent event = new KeyPressedEvent(e.getKeyCode());
		listener.onEvent(event);
	}

	@Override
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
		KeyReleasedEvent event = new KeyReleasedEvent(e.getKeyCode());
		listener.onEvent(event);
	}

	public static boolean isKeyPressed(int key){
		return keys[key];
	}
	
	@SuppressWarnings("unused")
	public static void releaseKeys(){
		for(boolean k : keys){
			k = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){}

}
