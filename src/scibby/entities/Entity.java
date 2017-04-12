package scibby.entities;

import scibby.graphics.Screen;

public abstract class Entity{

	public double x, y;
	
	public int width, height;
	
	protected boolean removed = false;

	public Entity(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
}
