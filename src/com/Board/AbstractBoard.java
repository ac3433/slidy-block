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
	
	protected void initalizeBoard(int boardSizeX, int boardSizeY)
	{
		board = new int[boardSizeX][boardSizeY];
	}
	
	public abstract void initalizeBoarders();
	public abstract void initalizeBlocks();
	
	public void fillBlock(int x, int y, int blockValue)
	{
		board[x][y] = blockValue;
	}
	
	
	
}
