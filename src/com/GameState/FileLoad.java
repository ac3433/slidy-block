package com.GameState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.Interaction.AbstractOutput;

public class FileLoad {

	private int sizeX, sizeY;
	private int[][] board;
	
	private AbstractOutput output;
	
	public void load(String filePath)
	{
		try( BufferedReader in = new BufferedReader(new FileReader(filePath)))
		{
			String[] size = in.readLine().split(",");
			
			sizeX = Integer.parseInt(size[0]);
			sizeY = Integer.parseInt(size[1]);
			
			board = new int[sizeX][sizeY];
			
			String[] row;
			
			while( (row = in.readLine().split(",")) != null )
			{
				if(row.length != sizeX)
					throw new Exception("Size is not the same as indicated");
				
				for(int i = 0; i < row.length; i++)
				{
					
				}
				
			}
			
		} 
		catch (NumberFormatException e)
		{
			output.displayOutput("Error: Cannot parse number.");
		}
		catch (FileNotFoundException e) 
		{
			output.displayOutput("Error: Cannot find file");
		} 
		catch (Exception e)
		{
			output.displayOutput(String.format("Error: Cannot load this file path %s", filePath));
		}

	}
}
