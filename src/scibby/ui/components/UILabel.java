package scibby.ui.components;

import java.awt.Font;
import java.awt.FontMetrics;

import scibby.graphics.Display;
import scibby.graphics.Screen;
import scibby.ui.UIComponent;
import scibby.ui.UIPanel;
import scibby.util.Vector2i;

public class UILabel extends UIComponent{

	public String text;

	private Font font;

	private UIButton parentButton;
	
	private int colour = 0x0000bb;
	
	public UILabel(Vector2i position, int width, int height, UIPanel parent, String text, Font font){
		super(position, width, height, parent);
		this.text = text;
		this.font = font;
	}

	public UILabel(int x, int y, int width, int height, UIPanel parent, String text, Font font){
		this(new Vector2i(x, y), width, height, parent, text, font);
	}

	public UILabel(Vector2i position, int width, int height, UIPanel parent, String text, Font font, UIButton parentButton){
		this(position, width, height, parent, text, font);
		this.parentButton = parentButton;
	}

	public UILabel(int x, int y, int width, int height, UIPanel parent, String text, Font font, UIButton parentButton){
		this(new Vector2i(x, y), width, height, parent, text, font, parentButton);
	}
	
	@Override
	public void render(Screen screen){
		super.render(screen);

		FontMetrics fm;

		fm = Display.getG().getFontMetrics(font);

		if(text == null) return;
		else if(parentButton != null){
			screen.drawString(text, parentButton.getAbsolutePosition().getX() + parentButton.width / 2 - (fm.stringWidth(text) / 2),
					parentButton.getAbsolutePosition().getY() + parentButton.height / 2 + 12, font, colour);
		}else{
			screen.drawString(text, getAbsolutePosition().getX(), getAbsolutePosition().getY(), font,  colour);
		}
		
	}
	
	public void setColour(int colour){
		this.colour = colour;
	}

}
