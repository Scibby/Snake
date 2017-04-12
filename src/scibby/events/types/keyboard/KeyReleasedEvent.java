package scibby.events.types.keyboard;

public class KeyReleasedEvent extends KeyButtonEvent{

	public KeyReleasedEvent(int button){
		super(button, Type.KEY_RELEASED);
	}

}
