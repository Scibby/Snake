package game.main;

import game.states.GameState;
import game.states.MenuState;
import scibby.core.Game;
import scibby.core.GameContainer;
import scibby.states.GameStateManager;

public class Main extends Game{

	private static GameContainer gc;
	
	public static MenuState menuState = new MenuState(0);
	public static GameState gameState = new GameState(1);
	
	private void init(){
		gc = init(512, 552, gc, "Snake");
		gc.setAlwaysOnTop(true);
		GameStateManager.addState(menuState);
		GameStateManager.addState(gameState);
		GameStateManager.currentState = 0;
		menuState.start();
	}
	
	public static void main(String[] args){
		new Main().init();
	}

}
