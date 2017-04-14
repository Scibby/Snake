package game.states;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;

import game.main.Main;
import scibby.core.Game;
import scibby.graphics.Screen;
import scibby.states.GameStateManager;
import scibby.states.State;
import scibby.ui.UIActionListener;
import scibby.ui.UIButtonListener;
import scibby.ui.UIPanel;
import scibby.ui.components.UIButton;
import scibby.ui.components.UILabel;

public class MenuState extends State{

	private UIPanel panel;
	private UILabel title;
	private UIButton button;

	public MenuState(int stateId){
		super(stateId);
	}

	@Override
	public void start(){

		Font titleFont = new Font("Arial", Font.PLAIN, 48);

		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(titleFont);

		panel = new UIPanel(0, 0, Game.getGc().getWidth(), Game.getGc().getHeight());

		title = new UILabel((Game.getGc().getWidth() / 2) - (fm.stringWidth("Snake") / 2), 75, 10, 10, panel, "Snake", titleFont);

		button = new UIButton(Game.getGc().getWidth() / 2 - 100, 200, 200, 50, panel, new UIActionListener(){

			@Override
			public void onAction(){
				clearLayers();
				Game.getUI().clearUI();
				GameStateManager.currentState = 1;
				Main.gameState.start();
			}

		});

		title.setColour(0xeeeeee);

		button.setColour(0xeeeeee);
		button.setButtonListener(new UIButtonListener(){
			
			@Override
			public void buttonEntered(UIButton button){
				super.buttonEntered(button);
				button.setColour(0xdff442);
				button.label.setColour(0xdff442);
			};

			@Override
			public void buttonExited(UIButton button){
				super.buttonExited(button);
				button.setColour(0xeeeeee);
				button.label.setColour(0xeeeeee);
			}

		});
		button.label.setColour(0xeeeeee);
		button.setText("Play Now");

		panel.addComponent(title);
		panel.addComponent(button);
		Game.getUI().addPanel(panel);
		addLayer(Game.getUI());
	}

	@Override
	public void tick(){
		super.tick();
	}

	@Override
	public void render(Screen screen){
		super.render(screen);
	}

}
