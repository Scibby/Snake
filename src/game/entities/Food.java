package game.entities;

import java.util.Random;

import scibby.entities.Mob;
import scibby.graphics.Screen;
import scibby.util.Vector2i;

public class Food extends Mob{

	private static Random rand = new Random();

	public Vector2i pos = new Vector2i((int) x, (int) y);

	public Food(int x, int y){
		super(x, y, 16, 16, null);
	}

	@Override
	public void tick(){

	}

	public void render(Screen screen){
		screen.fillRect((int) x, (int) y, width, height, 0xde2a2a, false);
	}

	public void newLocation(){
		this.x = (rand.nextInt(32) + 1) * 16;
		this.y = (rand.nextInt(32) + 1) * 16;

		pos.set((int) x, (int) y);
	}

}
