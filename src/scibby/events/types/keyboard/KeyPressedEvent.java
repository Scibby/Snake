package scibby.events.types.keyboard;

public class KeyPressedEvent extends KeyButtonEvent{

	public KeyPressedEvent(int button){
		super(button, Type.KEY_PRESSED);
	}

}
