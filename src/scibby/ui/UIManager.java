package scibby.ui;

import java.util.ArrayList;

import scibby.core.Game;
import scibby.events.Event;
import scibby.events.EventListener;
import scibby.graphics.Screen;
import scibby.layer.Layer;

public class UIManager extends Layer{

	EventListener listener = Game.getGc();

	public static ArrayList<UIPanel> panels = new ArrayList<UIPanel>();

	public UIManager(EventListener listener){
		this.listener = listener;
	}

	@Override
	public void tick(){
		for(UIPanel panel : panels){
			panel.tick();
		}
	}

	@Override
	public void render(Screen screen){
		for(UIPanel panel : panels){
			panel.render(screen);
		}
	}

	@Override
	public void onEvent(Event event){
		for(UIPanel panel : panels){
			panel.onEvent(event);
		}
	}

	public void addPanel(UIPanel panel){
		panels.add(panel);
	}

	public void removePanel(UIPanel panel){
		panels.add(panel);
	}

	public void clearUI(){
		panels.clear();
	}

}
