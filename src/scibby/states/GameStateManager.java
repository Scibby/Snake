package scibby.states;

import java.util.ArrayList;

import scibby.graphics.Screen;

public class GameStateManager{

	private static ArrayList<State> stateList = new ArrayList<State>();

	public static int currentState;

	private GameStateManager(){}

	public static void tick(){
		for(int i = 0; i < stateList.size(); i++){
			if(stateList.get(i).getID() == currentState){
				stateList.get(i).tick();
			}
		}
	}

	public static void render(Screen screen){
		for(int i = 0; i < stateList.size(); i++){
			if(stateList.get(i).getID() == currentState){
				stateList.get(i).render(screen);
			}
		}
	}

	public static void addState(State state){
		stateList.add(state);
	}

	public static void removeState(State state){
		stateList.remove(state);
	}

	public static State getCurrentState(){
		if(stateList.isEmpty()) return null;
		return stateList.get(currentState);
	}

}
