package scibby.ui.components;

import java.awt.Color;

import scibby.graphics.Screen;
import scibby.ui.UIComponent;
import scibby.ui.UIPanel;
import scibby.util.MathsUtil;
import scibby.util.Vector2i;

public class UIProgressBar extends UIComponent{

	public final int MIN_VALUE, MAX_VALUE;

	private int value = 50;

	private int bColour = Color.RED.darker().getRGB();
	private int fColour = Color.GREEN.darker().getRGB();

	public UIProgressBar(Vector2i position, int width, int height, UIPanel parent, int min, int max){
		super(position, width, height, parent);
		this.MIN_VALUE = min;
		this.MAX_VALUE = max;
	}

	public UIProgressBar(int x, int y, int width, int height, UIPanel parent, int min, int max){
		this(new Vector2i(x, y), width, height, parent, min, max);
	}

	@Override
	public void tick(){
		value = MathsUtil.clamp(value, MIN_VALUE, MAX_VALUE);
	}

	@Override
	public void render(Screen screen){
		screen.fillRect(getAbsolutePosition().getX(), getAbsolutePosition().getY(), width, height, bColour, true);
		screen.fillRect(getAbsolutePosition().getX(), getAbsolutePosition().getY(), (int) getFill(), height, fColour, true);
	}

	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setBackgroundColour(int bColour){
		this.bColour = bColour;
	}
	
	public void setForegroundColour(int fColour){
		this.fColour = fColour;
	}

	private double getFill(){
		return (double) width / (double) MAX_VALUE * (double) value;
	}
	
}
