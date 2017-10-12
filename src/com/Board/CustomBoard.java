package com.Board;

import com.Interaction.*;
import com.GameState.*;

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



}
