package com.Board;

import com.Interaction.*;
import com.GameState.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CustomBoard extends AbstractBoard{

	
	private static final long serialVersionUID = 2179420091453226507L;

	public CustomBoard(AbstractInput input, AbstractOutput output, String filePath) {
		super(input, output);
		loadBoard(filePath);
	}
	
	private void loadBoard(String path)
	{
		FileLoad board = new FileLoad();
		
		board.setBoardFromFile(path);
		setBoardSizeX(board.getBoardSizeX());
		setBoardSizeY(board.getBoardSizeY());
		setBoard(board.getBoard());
	}

	@Override
	public Object clone() {
		AbstractBoard board = null;
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			oos.flush();
			
			ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bin);
			board = (CustomBoard) ois.readObject();
		}
		catch(Exception e)
		{
			output.displayOutput("Failed in cloning");
		}
		finally
		{
			return board;
		}
		
	}
	


}
