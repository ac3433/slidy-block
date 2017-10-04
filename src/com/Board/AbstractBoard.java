package com.Board;

public abstract class AbstractBoard {

	private int[][] board;
	
	//need input and output class
	public AbstractBoard()
	{
		
	}
	
	protected void initalizeBoard(int boardSizeX, int boardSizeY)
	{
		board = new int[boardSizeX][boardSizeY];
	}
	
	public void fillBlock(int x, int y, int blockValue)
	{
		
	}
	
	
}
