package game.entities;

import static game.entities.Direction.Down;
import static game.entities.Direction.Left;
import static game.entities.Direction.Right;
import static game.entities.Direction.Up;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import game.main.Main;
import scibby.entities.Mob;
import scibby.events.Event;
import scibby.graphics.Screen;
import scibby.input.Keyboard;
import scibby.states.GameStateManager;
import scibby.util.Vector2i;

public class Snake extends Mob{

	public Snake(int x, int y){
		super(x, y, 16, 16, null);
	}

	public Vector2i pos = new Vector2i((int) x, (int) y);

	public ArrayList<Vector2i> tail = new ArrayList<Vector2i>();
	public ArrayList<Integer> colours = new ArrayList<Integer>();

	private Random rand = new Random();

	public static int score = 0;

	private int timer = 0;

	Direction dir = Down;
	private final int SPEED = 16;

	private boolean add = false;

	private boolean warped = false;

	private boolean changedDir = false;

	private Vector2i oldPos = new Vector2i();

	public void tick(Food food){
		super.tick();
		int xa = 0, ya = 0;

		if(!changedDir){
			if(Keyboard.isKeyPressed(KeyEvent.VK_W) || Keyboard.isKeyPressed(KeyEvent.VK_UP) && dir != Down){
				dir = Up;
				changedDir = true;
			}else if(Keyboard.isKeyPressed(KeyEvent.VK_S) || Keyboard.isKeyPressed(KeyEvent.VK_DOWN) && dir != Up){
				dir = Down;
				changedDir = true;
			}else if(Keyboard.isKeyPressed(KeyEvent.VK_D) || Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && dir != Left){
				dir = Right;
				changedDir = true;
			}else if(Keyboard.isKeyPressed(KeyEvent.VK_A) || Keyboard.isKeyPressed(KeyEvent.VK_LEFT) && dir != Right){
				dir = Left;
				changedDir = true;
			}
		}

		if(timer % 6 == 0){

			changedDir = false;

			for(Vector2i piece : tail){
				if(Vector2i.getDistance(pos, piece) <= 0){
					death();
				}
			}

			switch(dir){
				case Up:
					ya -= SPEED;
					break;
				case Down:
					ya += SPEED;
					break;
				case Left:
					xa -= SPEED;
					break;
				case Right:
					xa += SPEED;
					break;
			}

			if(warped){
				move(xa, ya);
				warped = false;
			}else if(!warp(xa, ya)){
				move(xa, ya);
			}

			if(add){
				tail.add(new Vector2i(pos));
				add = false;
				int r = rand.nextInt(255);
				int g = rand.nextInt(255);
				int b = rand.nextInt(255);

				colours.add((r << 16) + (g << 8) + (b << 0));
			}

			pos.set((int) x, (int) y);

			tail.add(new Vector2i(oldPos));
			tail.remove(0);

			if(Vector2i.getDistance(pos, food.pos) <= 0){
				score++;
				add = true;
				food.newLocation(this);
			}

			oldPos = new Vector2i(pos);

		}

		timer++;
	}

	private boolean warp(int xa, int ya){

		if(x == 16 && dir == Left){
			x = 512;
			warped = true;
		}else if(x == 512 && dir == Right){
			x = 16;
			warped = true;
		}else if(y == 16 && dir == Up){
			y = 512;
			warped = true;
		}else if(y == 512 && dir == Down){
			y = 16;
			warped = true;
		}
		return warped;

	}

	private void death(){
		Main.gameState.clearLayers();
		GameStateManager.currentState = 2;
		Main.gameOverState.start(score);
		score = 0;
	}

	@Override
	public void render(Screen screen){
		super.render(screen);
		screen.fillRect((int) x, (int) y, width, height, 0x44ff44, false);

		for(int i = 0; i < tail.size(); i++){
			screen.fillRect((int) tail.get(i).getX(), (int) tail.get(i).getY(), width, height, colours.get(i), false);
		}
	}

	@Override
	public void onEvent(Event event){
		super.onEvent(event);
	}

}
