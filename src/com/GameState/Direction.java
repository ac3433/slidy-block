package com.GameState;

public enum Direction {
	Up(-1,0),
	Down(1,0),
	Left(0, -1),
	Right(0,1),
	None(0,0); //none is to be use if it is uncertain of the direction... 
	
	private int dx, dy;
	
	private Direction(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public int dx(){ return dx;}
	public int dy(){ return dy;}
	
	//this should be moved out... just leaving it here
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
		else if(d.equals("none"))
			return Direction.None;
		else
			return null;
	}
}
