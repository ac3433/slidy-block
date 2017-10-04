package com.Board;

import com.Interaction.*;

public class SixByEight extends AbstractBoard {

	final int sizeX = 6, sizeY = 8;
	

	public SixByEight(AbstractInput input, AbstractOutput output) {
		super(input, output);
		super.initalizeBoard(sizeX, sizeY);
		
		initalizeBoarders();
		initalizeBlocks();
	}
	
	//setting up the boarder of the board that blocks cannot go to
	public void initalizeBoarders()
	{
		//going through the rows
		for(int i = 0; i < sizeX; i++)
		{
			//going through columns
			for(int j = 0; j < sizeY; j++)
			{
				
			}
		}
	}
	
	public void initalizeBlocks()
	{

	}
	
}
