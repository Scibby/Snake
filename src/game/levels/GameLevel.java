package game.levels;

import java.util.Random;

import game.entities.Food;
import game.entities.Snake;
import scibby.entities.Tile;
import scibby.events.Event;
import scibby.graphics.Screen;
import scibby.level.Level;

public class GameLevel extends Level{

	public static Tile voidTile = new Tile(null, false);
	public static Tile wallTile = new Tile(null, true);

	private Random rand = new Random();

	private Snake snake = new Snake((rand.nextInt(32) + 1) * this.getTileSize(), (rand.nextInt(32) + 1) * this.getTileSize());
	
	private Food food = new Food((rand.nextInt(32) + 1) * this.getTileSize(), (rand.nextInt(32) + 1) * this.getTileSize());

	public GameLevel(int width, int height, int viewSizeX, int viewSizeY, int tileSize){
		super(width, height, viewSizeX, viewSizeY, tileSize);
		for(int y = 0; y < HEIGHT; y++){
			for(int x = 0; x < WIDTH; x++){
				if(y == 0){
					tiles.put(x + y * WIDTH, wallTile);
					continue;
				}else if(x == 0){
					tiles.put(x + y * WIDTH, wallTile);
				}else if(y == 33){
					tiles.put(x + y * WIDTH, wallTile);
				}else if(x == 33){
					tiles.put(x + y * WIDTH, wallTile);
				}else{
					tiles.put(x + y * WIDTH, voidTile);
				}
			}
		}
		mobs.add(snake);
		mobs.add(food);
	}

	@Override
	public void tick(){
		snake.tick(food);
		food.tick();
	}
	
	@Override
	public void onEvent(Event event){

	}

	@Override
	public void render(Screen screen){
		screen.setOffsets(16, -24);
		super.render(screen);
	}
}
