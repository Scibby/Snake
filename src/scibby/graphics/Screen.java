package scibby.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import scibby.util.MathsUtil;

public class Screen{

	private int width;
	private int height;

	private int xOffset;
	private int yOffset;

	private int[] pixels;

	private int[] lightMapPixels;

	public Screen(int width, int height, int[] pixels){
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public void fillRect(int x, int y, int width, int height, int colour, boolean fixed){
		if(!fixed){
			x -= xOffset;
			y -= yOffset;
		}

		for(int yy = 0; yy < height; yy++){
			int yo = y + yy;
			if(yo < 0 || yo >= this.height) continue;
			for(int xx = 0; xx < width; xx++){
				int xo = x + xx;
				if(xo < 0 || xo >= this.width) continue;
				pixels[xo + yo * this.width] = colour + (0xff << 24);
			}
		}
	}

	public void renderSprite(int x, int y, Sprite sprite){
		x -= xOffset;
		y -= yOffset;
		if(sprite == null) return;
		for(int yy = 0; yy < sprite.height; yy++){
			for(int xx = 0; xx < sprite.width; xx++){
				if(xx + x < 0 || xx + x >= this.width || yy + y < 0 || yy + y >= this.height) continue;
				pixels[(xx + x) + (yy + y) * this.width] = sprite.pixels[xx + yy * sprite.width] + (0xff << 24);
			}
		}
	}

	public void renderLight(int x, int y, int width, int height, int alpha){
		x -= xOffset;
		y -= yOffset;
		alpha = MathsUtil.clamp(alpha, 0, 0xff);
		for(int yy = 0; yy < height; yy++){
			for(int xx = 0; xx < width; xx++){
				if(xx + x < 0 || xx + x >= this.width || yy + y < 0 || yy + y >= this.height) continue;
				lightMapPixels[(xx + x) + (yy + y) * this.width] = alpha << 24;
			}
		}
	}

	public void renderSprite(double x, double y, Sprite sprite){
		renderSprite((int) x, (int) y, sprite);
	}

	public void renderAnimatedSprite(int x, int y, AnimatedSprite animSprite){
		renderSprite(x, y, animSprite.getCurrentSprite());
	}

	public void renderAnimatedSprite(double x, double y, AnimatedSprite animSprite){
		renderSprite((int) x, (int) y, animSprite.getCurrentSprite());
	}

	public void drawString(String text, int x, int y, Font font, int colour){
		Graphics2D g = Display.getG();

		g.setFont(font);
		g.setColor(new Color(colour));

		g.drawString(text, x, y);
	}

	public void setOffsets(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void setLightMapPixels(int[] lightMapPixels){
		this.lightMapPixels = lightMapPixels;
	}

}
