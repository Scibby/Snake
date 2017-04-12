package scibby.entities;

import scibby.graphics.Screen;
import scibby.graphics.Sprite;

public class Tile{

	private Sprite sprite;

	private boolean solid;
	
	public Tile(Sprite sprite, boolean solid){
		this.sprite = sprite;
		this.solid = solid;
	}

	public void render(int x, int y, Screen screen){
		if(sprite != null){
			screen.renderSprite(x, y, sprite);
		}
	}

	public boolean isSolid(){
		return solid;
	}

}
