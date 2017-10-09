package com.Board;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.Interaction.*;

public abstract class AbstractBoard implements Serializable {

	private static final long serialVersionUID = 2179420091453226507L;
	private int[][] board;
	private int sizeX, sizeY;
	
	public AbstractInput input;
	public AbstractOutput output;
	
	public AbstractBoard(AbstractInput input, AbstractOutput output)
	{
		this.input = input;
		this.output = output;
	}
	
	public void initalizeBoard(int boardSizeX, int boardSizeY)
	{
		sizeX = boardSizeX;
		sizeY = boardSizeY;
		board = new int[sizeX][sizeY];
	}
	
	public void setBoardSizeX(int sizeX) { this.sizeX = sizeX; }
	public void setBoardSizeY(int sizeY) { this.sizeY = sizeY; }
		
	public void fillBlock(int x, int y, int blockValue)
	{
		board[x][y] = blockValue;
	}
	
	public void setBoard(int[][] board) {this.board = board; }
	
	public void printBoard()
	{
		if(board != null)
		{
			output.displayOutput(String.format("%s,%s,", sizeX, sizeY));
			
			for(int i = 0; i < sizeX; i++)
			{
				String line = "";
				
				for(int j = 0; j < sizeY; j++)
				{
					line = line.concat(String.format("%s, ", board[i][j]));
				}
				
				output.displayOutput(line);
			}
		}
		else
			output.displayOutput("Board is empty");
	}
	
	public boolean checkWinState()
	{
		try
		{
			for(int i = 0; i < sizeX; i++)
			{
				for(int j = 0; j < sizeY; j++)
				{
					if(board[i][j] < 0)
						return false;
				}
			}
			
			return true;
		}
		catch(NullPointerException e)
		{
			output.displayOutput("Board is empty");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			output.displayOutput("Index out of bound");
		}
		
		return false;
	}
	
	public abstract Object clone();
}
