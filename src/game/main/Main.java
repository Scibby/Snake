package game.main;

import game.states.GameState;
import scibby.core.Game;
import scibby.core.GameContainer;
import scibby.states.GameStateManager;

public class Main extends Game{

	private static GameContainer gc;
	
	public static GameState gameState = new GameState(0);
	
	private void init(){
		gc = init(512, 552, gc, "Snake");
		gc.setAlwaysOnTop(true);
		GameStateManager.addState(gameState);
		GameStateManager.currentState = 0;
		gameState.start();
	}
	
	public static void main(String[] args){
		new Main().init();
	}

}
