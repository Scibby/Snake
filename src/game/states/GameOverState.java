package game.states;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;

import game.main.Main;
import scibby.core.Game;
import scibby.states.GameStateManager;
import scibby.states.State;
import scibby.ui.UIActionListener;
import scibby.ui.UIButtonListener;
import scibby.ui.UIPanel;
import scibby.ui.components.UIButton;
import scibby.ui.components.UILabel;

public class GameOverState extends State{

	private UIPanel panel;
	private UILabel message;
	private UIButton replay;
	
	public GameOverState(int stateId){
		super(stateId);
	}
	
	public void start(int score){
		clearLayers();
		Main.getUI().clearUI();

		Font messageFont = new Font("Arial", Font.PLAIN, 28);

		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(messageFont);
		
		panel = new UIPanel(0, 0, Main.getGc().getWidth(), Main.getGc().getHeight());
		
		message = new UILabel((Game.getGc().getWidth() / 2) - (fm.stringWidth("Your score was: " + score) / 2), 75, 10, 10, panel, "Your score was: " + score, messageFont);
		
		message.setColour(0xeeeeee);
		
		replay = new UIButton(Game.getGc().getWidth() / 2 - 100, 200, 200, 50, panel, new UIActionListener(){

			@Override
			public void onAction(){
				clearLayers();
				Game.getUI().clearUI();
				GameStateManager.currentState = 1;
				Main.gameState.start();
			}
		});
		
		panel.addComponent(message);
		panel.addComponent(replay);
		
		replay.setColour(0xeeeeee);
		replay.label.setColour(0xeeeeee);
		replay.setText("Replay");
		replay.setButtonListener(new UIButtonListener(){
			@Override
			public void buttonEntered(UIButton button){
				super.buttonEntered(button);
				replay.setColour(0xdff442);
				replay.label.setColour(0xdff442);
			}
			
			@Override
			public void buttonExited(UIButton button){
				super.buttonExited(button);
				replay.setColour(0xeeeeee);
				replay.label.setColour(0xeeeeee);
			}
		});
		
		Game.getUI().addPanel(panel);
		addLayer(Main.getUI());
	}

	@Override
	public void tick(){
		super.tick();
	}
	
}
