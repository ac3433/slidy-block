package com.Board;
import com.Interaction.*;

public abstract class AbstractBoard {

	private int[][] board;
	
	public AbstractInput input;
	public AbstractOutput output;
	
	public AbstractBoard(AbstractInput input, AbstractOutput output)
	{
		this.input = input;
		this.output = output;
	}
	
	public void initalizeBoard(int boardSizeX, int boardSizeY)
	{
		board = new int[boardSizeX][boardSizeY];
	}
		
	public void fillBlock(int x, int y, int blockValue)
	{
		board[x][y] = blockValue;
	}
	
	
	
}
