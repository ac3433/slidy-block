package com.Board;

//help maintain the block dimension on the matrix
//can be use to find all possible position of the block
public class Block {

	private int x1, x2, y1, y2;
	
	public Block(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public int getX1() { return x1; }
	public int getX2() { return x2; }
	public int getY1() { return y1; }
	public int getY2() { return y2; }
}
