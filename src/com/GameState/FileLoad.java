package com.GameState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.Interaction.AbstractOutput;

public class FileLoad {
	
	private AbstractOutput output;
	private int[][] board;
	private int sizeX, sizeY;
	
	public void setBoardFromFile(String filePath)
	{
		try( BufferedReader in = new BufferedReader(new FileReader(filePath)))
		{
			String[] size = in.readLine().split(",");
			
			sizeX = Integer.parseInt(size[1]);
			sizeY = Integer.parseInt(size[0]);
			
			board = new int[sizeX][sizeY];
			
			int linePosition = 0;
			
			for(String line = in.readLine(); line != null; line = in.readLine())
			{
				String[] values = line.split(",");
				for(int i = 0; i < values.length; i++)
				{
					board[linePosition][i] = Integer.parseInt(values[i]);
				}
				
				linePosition++;
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
	
	public int getBoardSizeX() { return sizeX; }
	public int getBoardSizeY() { return sizeY; }
	public int[][] getBoard() {return board; }
}
