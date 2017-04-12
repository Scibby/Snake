package scibby.events.types.keyboard;

import scibby.events.Event;

public abstract class KeyButtonEvent extends Event{
	protected int button;
	
	protected KeyButtonEvent(int button, Type type) {
		super(type);
		this.button = button;
	}

	public int getButton() {
		return button;
	}

}
