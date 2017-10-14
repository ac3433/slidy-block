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
import com.Board.Block;
import com.Interaction.AbstractOutput;

public class BoardInteraction {
	
	AbstractOutput output;
	
	public BoardInteraction(AbstractOutput output)
	{
		this.output = output;
	}
	
	//display the board
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
	
	//check if there is a any negative value on the board which will be the win state and only block 2 can even move there
	public boolean checkWinState(AbstractBoard board)
	{
		try
		{
			//row
			for(int i = 0; i < board.getBoardSizeX(); i++)
			{
				//column
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
	
	//serialize the object to remove the reference and return it back after reading it
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
	
	//compare the board state
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
	
	//get the list of available move of a piece
	public List<String> getMoveSet(AbstractBoard board, int peice)
	{
		List<String> dir = null;
		
		if(board.getBoard() != null)
		{
			
			Block block = getBlockDimension(board, peice);
			
			if(block == null)
				return null;
			
			 dir = new ArrayList<String>();
			
			//check left side of block
			if(canMove(board.getBoard(), Direction.Left, block.getX1(), block.getX2(), block.getY1(), block.getY1()))
				dir.add("left");
			//check right side of block
			if(canMove(board.getBoard(), Direction.Right, block.getX1(), block.getX2(), block.getY2(), block.getY2()))
				dir.add("right");
			//check up side of block
			if(canMove(board.getBoard(), Direction.Up, block.getX1(), block.getX1(), block.getY1(), block.getY2()))
				dir.add("up");
			//check down side of block
			if(canMove(board.getBoard(), Direction.Down, block.getX2(), block.getX2(), block.getY1(), block.getY2()))
				dir.add("down");		
		}
			return dir;
	}
	
	//this search for the block and returns the block with their dimensions relative to the board
	private Block getBlockDimension(AbstractBoard board, int peice)
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
		
		return new Block(x1,y1,x2,y2);
		
	}
		
	//check the location if it's empty or the exit
	private boolean canMove(int[][] board, Direction dir,int x1, int x2, int y1, int y2)
	{
		boolean move = true;
		boolean specialBlock = false;
		
		//special condition check if it's the block that need to exit
		if(board[x1][y1] == 2)
			specialBlock = true;
		
		//change the value of the direction position it should be looking
		x1 += dir.dx();
		x2 += dir.dx();
		y1 += dir.dy();
		y2 += dir.dy();
		
		loop:
		for(int i = x1; i <= x2; i++)
		{
			for(int j = y1; j <= y2; j++)
			{
				if(specialBlock)
				{
					if(board[i][j] != 0 && board[i][j] != -1)
					{
						move = false;
						break loop;
					}
				}
				else
				{
					if(board[i][j] != 0)
					{
						move = false;
						break loop;
					}
				}
												
			}
		}
		
		
		return move;
	}
	
	//get all the list of moves for all pieces
	public Map<Integer, List<String>> getAllMoves(AbstractBoard board)
	{
		Map<Integer, List<String>> moveSets = new HashMap<Integer, List<String>>();
		
		
		//the loop help identify the pieces on the board
		//rows
		for(int i = 0; i < board.getBoardSizeX(); i++)
		{
			//column
			for(int j = 0; j < board.getBoardSizeY(); j++)
			{
				int positionValue = board.getBoard()[i][j];
				//don't include non-pieces
				if(positionValue != -1 && positionValue != 0 && positionValue != 1)
				{
					List<String> moves= getMoveSet(board, positionValue);
					
					if(moves.size() != 0)
						moveSets.put(positionValue, moves);
				}
			}
		}
		
		
		
		return moveSets;
		
		
	}
	
	//move the piece of the board
	public boolean applyMove(AbstractBoard board, String direction, int peice)
	{
		//convert the direction string to the enum Direction that has the coordinate of how to position
		Direction frontToMove = Direction.None.mapStringDirection(direction);
		
		if(board.getBoard() == null)
		{
			output.displayOutput("There is no board");
			return false;
		}
		
		//check if the move you wanted to do is valid
		if(!getMoveSet(board, peice).contains(direction.toLowerCase()))
		{
			return false;
		}
		
		Block block = getBlockDimension( board, peice);
		
		changeBlockPosition(board.getBoard(), frontToMove, peice, block); //change the new position with the value
		
		return true;
	}
	
	//this need to be rewritten in setting the positions
	//do sometime similar to getmoveset to get frontal and back positions to swap
	private void changeBlockPosition(int[][] board, Direction dir, int value , Block block)
	{
		int x1 = block.getX1();
		int x2 = block.getX2();
		int y1 = block.getY1();
		int y2 = block.getY2();
		
		//clear the area of the piece
		//terrible way of doing this!
		for(int i = x1; i <= x2; i++)
		{
			for(int j = y1; j <= y2; j++)
			{
				board[i][j] = 0;
			}
		}
		
		//readjust the new position
		x1 += dir.dx();
		x2 += dir.dx();
		y1 += dir.dy();
		y2 += dir.dy();
		
		for(int i = x1; i <= x2; i++)
		{
			for(int j = y1; j <= y2; j++)
			{
				board[i][j] = value;
			}
		}
		
		
	}
	
	//return a new state of the board with the move
	public AbstractBoard applyMoveCloning(AbstractBoard board, String dir, int piece)
	{
		AbstractBoard b = (AbstractBoard) clone(board);
		
		if(applyMove(b, dir, piece))
			return b;
		else 
			return null;

	}
	
	//change the order of the board from the top left to be lowest to highest bottom right of the board.
	public void normalizeBoard(AbstractBoard board)
	{
		int nextIdx = 3;
		
		for(int i = 0;i < board.getBoardSizeX();i++) {
		  for(int j = 0;j < board.getBoardSizeY();j++) {
		    if (board.getBoard()[i][j]==nextIdx) {
		      nextIdx++;
		    } else if (board.getBoard()[i][j] > nextIdx) {
		      swapIdx(board ,nextIdx,board.getBoard()[i][j]);
		      nextIdx++;
		    }
		  }
		}
	}
	
	private void swapIdx(AbstractBoard board, int idx1,int idx2) {
	  for(int i = 0;i < board.getBoardSizeX(); i++) {
	    for(int j = 0;j < board.getBoardSizeY();j++) {
	      if (board.getBoard()[i][j]==idx1) {
	        board.getBoard()[i][j]=idx2;
	      } else if (board.getBoard()[i][j]==idx2) {
	        board.getBoard()[i][j]=idx1;
	      }
	    }
	  }
	}
	
}
