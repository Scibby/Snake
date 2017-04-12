package scibby.graphics;

public class AnimatedSprite{

	private int speed;
	private int frames;

	private int index = 0;
	private int count = 0;

	private Sprite currentSprite;
	private Sprite[] sprites;

	public AnimatedSprite(int speed, Sprite[] sprites){
		this.speed = speed;
		this.sprites = sprites;

		frames = sprites.length;
	}

	public void runAnimation(){
		index++;

		if(index > speed){
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame(){
		for(int i = 0; i < frames; i++){
			if(count == i){
				currentSprite = sprites[i];
			}
		}

		count++;

		if(count > frames){
			count = 0;
		}
	}
	
	public Sprite getCurrentSprite(){
		return currentSprite;
	}

}