package scibby.ui;

import scibby.events.Event;
import scibby.graphics.Screen;
import scibby.util.Vector2i;

public class UIComponent{

	protected Vector2i position;
	protected Vector2i offset;
	public int width;
	public int height;

	protected UIPanel parent;

	public UIComponent(Vector2i position, int width, int height, UIPanel parent){
		this.position = position;
		this.offset = new Vector2i(parent.position);
		this.width = width;
		this.height = height;
		this.parent = parent;
	}

	public UIComponent(int x, int y, int width, int height, UIPanel parent){
		position = new Vector2i(x, y);
		this.offset = new Vector2i(parent.position);
		this.width = width;
		this.height = height;
		this.parent = parent;
	}

	public void tick(){
	}

	public void onEvent(Event event){
	}

	public void render(Screen screen){
	}

	public Vector2i getAbsolutePosition(){
		return new Vector2i(position).add(offset);
	}

}
