package game.states;

import java.awt.Font;

import game.entities.Snake;
import game.levels.GameLevel;
import scibby.core.Game;
import scibby.graphics.Screen;
import scibby.level.Level;
import scibby.states.State;
import scibby.ui.UIPanel;
import scibby.ui.components.UILabel;

public class GameState extends State{

	private Level level;
	private static UIPanel panel;
	public static UILabel score;

	public GameState(int stateId){
		super(stateId);
	}

	@Override
	public void start(){
		level = new GameLevel(34, 34, 512, 512, 16);
		Level.addLevel(level);
		Level.setLevel(1);
		addLayer(level);
		level.getCamera().useCamera = false;

		panel = new UIPanel(0, 0, Game.getGc().getWidth(), 40);

		score = new UILabel(10, 30, 10, 10, panel, "Score: 0", new Font("Arial", Font.PLAIN, 26));

		score.setColour(0xeeeeee);

		panel.addComponent(score);
		Game.getUI().addPanel(panel);
		addLayer(Game.getUI());
	}

	@Override
	public void tick(){
		super.tick();
		score.text = "Score: " + Snake.score;
	}

	@Override
	public void render(Screen screen){
		super.render(screen);
	}

}
