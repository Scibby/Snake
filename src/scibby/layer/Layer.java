package scibby.layer;

import scibby.events.Event;
import scibby.events.EventListener;
import scibby.graphics.Screen;

public class Layer implements EventListener{

	@Override
	public void onEvent(Event event){}

	public void tick(){}

	public void render(Screen screen){}

}
