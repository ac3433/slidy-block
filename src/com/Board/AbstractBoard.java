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
	
	

	

	
	

}
