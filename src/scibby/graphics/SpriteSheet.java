package scibby.graphics;

import java.awt.image.BufferedImage;

import scibby.util.ResourceLoader;

public class SpriteSheet{

	private int width;
	private int height;
	
	private BufferedImage image;
	
	private int[] pixels;

	public SpriteSheet(String path){
		this.image = new ResourceLoader().loadImage(path);
		this.width = image.getWidth();
		this.height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	}

	public Sprite getSprite(int x, int y, int width, int height){
		int[] spritePixels = new int[width * height];

		for(int yy = 0; yy < height; yy++){
			for(int xx = 0; xx < width; xx++){
				spritePixels[xx + yy * width] = pixels[(xx + (x * width)) + (yy + (y * height)) * this.width];
			}
		}

		Sprite sprite = new Sprite(width, height, spritePixels);

		return sprite;
	}

}