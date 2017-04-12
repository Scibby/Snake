package game.entities;

public enum Direction{
	Up(0), Down(1), Left(2), Right(3);

	public final int DIR;
	
	private Direction(int dir){
		this.DIR = dir;
	}
}
