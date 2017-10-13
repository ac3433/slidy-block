package com.GameState;

public enum Direction {
	Up(-1,0),
	Down(1,0),
	Left(0, -1),
	Right(0,1);
	
	private int dx, dy;
	
	private Direction(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public int dx(){ return dx;}
	public int dy(){ return dy;}
	
	public Direction mapStringDirection(String dir)
	{
		String d = dir.toLowerCase();
		if(d.equals("left"))
			return Direction.Left;
		else if(d.equals("right"))
			return Direction.Right;
		else if(d.equals("up"))
			return Direction.Up;
		else if(d.equals("down"))
			return Direction.Down;
		else
			return null;
	}
}
