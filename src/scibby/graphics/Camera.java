package scibby.graphics;

import scibby.entities.Mob;

public class Camera{

	public int camX;
	public int camY;

	public final int viewSizeX;
	public final int viewSizeY;

	public final int worldSizeX;
	public final int worldSizeY;

	public boolean useCamera;
	
	public int offsetMaxX;
	public int offsetMaxY;
	public int offsetMinX;
	public int offsetMinY;

	public Camera(int viewSizeX, int viewSizeY, int worldSizeX, int worldSizeY){
		this.viewSizeX = viewSizeX;
		this.viewSizeY = viewSizeY;
		this.worldSizeX = worldSizeX;
		this.worldSizeY = worldSizeY;
		
		offsetMaxX = worldSizeX - viewSizeX;
		offsetMaxY = worldSizeY - viewSizeY;
		offsetMinX = 0;
		offsetMinY = 0;
	}

	public void tick(Mob player){
		if(useCamera){
			camX = (int) (player.x + (player.width / 2)) - (viewSizeX / 2);
			camY = (int) (player.y + (player.height / 2)) - (viewSizeY / 2);
		}
		
		if(camX > offsetMaxX) camX = offsetMaxX;
		else if(camX < offsetMinX) camX = offsetMinX;
		
		if(camY > offsetMaxY) camY = offsetMaxY;
		else if(camY < offsetMinY) camY = offsetMinY;
	}
}
