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
import scibby.events.EventDispatcher;
import scibby.events.types.keyboard.KeyPressedEvent;
import scibby.events.types.keyboard.KeyReleasedEvent;
import scibby.graphics.Screen;
import scibby.states.GameStateManager;
import scibby.util.AudioPlayer;
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

	private Direction dir = Down;
	private final int SPEED = 16;

	private boolean add = false;

	private boolean warped = false;

	private boolean changedDir = false;

	private AudioPlayer player = new AudioPlayer("eat");
	
	private Vector2i oldPos = new Vector2i();

	public void tick(Food food){
		super.tick();
		int xa = 0, ya = 0;

		if(timer % 6 == 0){

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

			changedDir = false;

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
				player.play();
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
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.KEY_PRESSED, (Event e) -> onKeyPressed((KeyPressedEvent) event));
		dispatcher.dispatch(Event.Type.KEY_RELEASED, (Event e) -> onKeyReleased((KeyReleasedEvent) event));
	}

	private boolean onKeyPressed(KeyPressedEvent event){

		int button = event.getButton();
		if(!changedDir){
			if((button == KeyEvent.VK_W || button == KeyEvent.VK_UP) && dir != Down){
				dir = Up;
				changedDir = true;
				return true;
			}else if((button == KeyEvent.VK_S || button == KeyEvent.VK_DOWN) && dir != Up){
				dir = Down;
				changedDir = true;
				return true;
			}else if((button == KeyEvent.VK_D || button == KeyEvent.VK_RIGHT) && dir != Left){
				dir = Right;
				changedDir = true;
				return true;
			}else if((button == KeyEvent.VK_A || button == KeyEvent.VK_LEFT) && dir != Right){
				dir = Left;
				changedDir = true;
				return true;
			}
		}

		return false;
	}

	private boolean onKeyReleased(KeyReleasedEvent event){
		return false;
	}

}
