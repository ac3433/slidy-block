package com.Board;
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
	public int getBoardSizeX() { return sizeX; }
	public int getBoardSizeY() { return sizeY; }
	public int[][] getBoard() { return board; }
	public void setBoard(int[][] board) {this.board = board; }
	
<<<<<<< Updated upstream
	
=======
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
>>>>>>> Stashed changes

	

	
<<<<<<< Updated upstream
	

=======
	public boolean compareBoardState(int[][] board1 , int[][] board2)

	
	{
		try
		{
			if(board1.length != board2.length)
				return false;
			
			for(int i = 0; i < board1.length; i++)
			{
				if(board1[i].length != board2[i].length)
					return false;
				
				for(int j = 0; j < board1[i].length; j++)
				{
					if(board1[i][j] != board2[i][j])
						return false;
				}
			}
		}
		catch(Exception e)
		{
			output.displayOutput("Error: Comparing state");
		}
		
		return true;
	}
>>>>>>> Stashed changes
}
