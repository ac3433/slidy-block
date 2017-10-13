package com.GameState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Board.AbstractBoard;
import com.Interaction.AbstractOutput;

public class BoardInteraction {
	
	AbstractOutput output;
	
	public BoardInteraction(AbstractOutput output)
	{
		this.output = output;
	}
	
	public void printBoard(AbstractBoard board)
	{
		int[][] b = board.getBoard();
		if(b != null)
		{
			output.displayOutput(String.format("%s,%s,", board.getBoardSizeX(), board.getBoardSizeY()));
			
			for(int i = 0; i < board.getBoardSizeX(); i++)
			{
				String line = "";
				
				for(int j = 0; j < board.getBoardSizeY(); j++)
				{
					line = line.concat(String.format("%s, ", b[i][j]));
				}
				
				output.displayOutput(line);
			}
		}
		else
			output.displayOutput("Board is empty");
	}
	
	
	public boolean checkWinState(AbstractBoard board)
	{
		try
		{
			for(int i = 0; i < board.getBoardSizeX(); i++)
			{
				for(int j = 0; j < board.getBoardSizeY(); j++)
				{
					if(board.getBoard()[i][j] < 0)
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
	
	public Object clone(AbstractBoard board) {
		AbstractBoard b = null;
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(board);
			oos.flush();
			
			ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bin);
			b = (AbstractBoard) ois.readObject();
		}
		catch(Exception e)
		{
			output.displayOutput("Failed in cloning");
		}

		return b;
		
	}
	
	
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
	
	public enum Direction{
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
	}
	
	public List<String> getMoveSet(AbstractBoard board, int peice)
	{
		List<String> dir = null;
		
		if(board.getBoard() != null)
		{
			int x1 = -1, y1 = -1; //top left position
			int x2 = -1, y2 = -1; //bottom right position
			
			//These nested loop help find the points of the corner to help get all the possible block point location
			//go through the row of the board
			for(int i = 0; i < board.getBoardSizeX(); i++)
			{
				//go through the column of the board
				for(int j = 0; j < board.getBoardSizeY(); j++)
				{
					//check if it found the peice in the array
					if(board.getBoard()[i][j] == peice)
					{
						//store the first top left corner of the block
						//store the last block
						if(x1 == -1 && y1 == -1)
						{
							x1 = i;
							y1 = j;
							x2 = i;
							y2 = j;
						}
						else
						{
							//update the last found bottom right corner
							x2 = i;
							y2 = j;
						}
					}
				}
				
				//if no piece is found on the board
				if(x1 == -1 && i == board.getBoardSizeX() -1 )
				{
					return null;
				}
					
			}
			 dir = new ArrayList<String>();
			
			//Check direction
			//check left side of block
			if(canMove(board.getBoard(), Direction.Left, x1, x2, y1, y1))
				dir.add("left");
			//check right side of block
			if(canMove(board.getBoard(), Direction.Right, x1, x2, y2, y2))
				dir.add("right");
			//check up side of block
			if(canMove(board.getBoard(), Direction.Up, x1, x1, y1, y2))
				dir.add("up");
			//check down side of block
			if(canMove(board.getBoard(), Direction.Down, x2, x2, y1, y2))
				dir.add("down");		
		}
			return dir;
	}
	
	//check the location if it's empty or the exit
	private boolean canMove(int[][] board, Direction dir,int x1, int x2, int y1, int y2)
	{
		boolean move = true;
		
		x1 += dir.dx;
		x2 += dir.dx;
		y1 += dir.dy;
		y2 += dir.dy;
		
		loop:
		for(int i = x1; i <= x2; i++)
		{
			for(int j = y1; j <= y2; j++)
			{
				if(board[i][j] != 0 && board[i][j] != -1)
				{
					move = false;
					break loop;
				}
			}
		}
		
		
		return move;
	}
	
	public Map<Integer, List<String>> getAllMoves(AbstractBoard board)
	{
		Map<Integer, List<String>> moveSets = new HashMap<Integer, List<String>>();
		
		for(int i = 0; i < board.getBoardSizeX(); i++)
		{
			for(int j = 0; j < board.getBoardSizeY(); j++)
			{
				int positionValue = board.getBoard()[i][j];
				if(positionValue != -1 && positionValue != 0 && positionValue != 1)
				{
					moveSets.put(positionValue, getMoveSet(board, positionValue));
				}
			}
		}
		
		return moveSets;
		
		
	}
	
	public void applyMove(AbstractBoard board, String direction, int peice)
	{
		Direction dirToMove;
	
		
		if(direction.toLowerCase().equals("left"))
		{
			dirToMove = Direction.Left;
		}
		else if(direction.toLowerCase().equals("right"))
		{
			dirToMove = Direction.Right;
		}
		else if(direction.toLowerCase().equals("up"))
		{
			dirToMove = Direction.Up;
		}
		else if(direction.toLowerCase().equals("down"))
		{
			dirToMove = Direction.Down;
		}
			
	}
}
