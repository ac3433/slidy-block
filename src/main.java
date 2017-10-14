import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.Board.*;
import com.GameState.BoardInteraction;
import com.Interaction.*;

public class main {
	
	public static void main(String[] args) {
		
		AbstractInput input = new ConsoleInput();
		AbstractOutput output = new ConsoleOutput();
		
		try
		{
			if(args.length != 2)
			{
				output.displayOutput("Please enter a path in the argument [filePath] [N size]");
				System.exit(0);
			}
			
			if(!new File(args[0]).exists())
			{
				output.displayOutput("File cannot be found");
				System.exit(0);
			}
			
			
			AbstractBoard board = new CustomBoard(input, output, args[0]);

			//this class provide all the interaction need to be done on the board
			BoardInteraction bi = new BoardInteraction(output);
	
			//saved the default board incase of reset
			AbstractBoard everChangingBoard = (AbstractBoard) bi.clone(board);
			
			Random rand = new Random();
			
			bi.printBoard(everChangingBoard);
			for(int i = 0; i < Integer.parseInt(args[1]); i++)
			{
				Map<Integer, List<String>> move = bi.getAllMoves(everChangingBoard);
				
				//randomly select a piece
				List<Integer> pieces = new ArrayList<Integer>(move.keySet());
				int piece = pieces.get(rand.nextInt(pieces.size()));
				
				//randomly select a direction from a given piece
				List<String> dir = move.get(piece);
				int dirVal = rand.nextInt(dir.size());
				String direction = dir.get(dirVal);
				
				//get a new state with the move
				everChangingBoard = bi.applyMoveCloning(everChangingBoard, direction, piece);
				bi.normalizeBoard(everChangingBoard);
				
				output.displayOutput(String.format("(%s, %s)", piece, direction));
				bi.printBoard(everChangingBoard);
				
				if(bi.checkWinState(everChangingBoard))
					break;
				
			}

		}
		catch(NumberFormatException e)
		{
			output.displayOutput("Cannot convert number!! Ensure the argument is a valid number");
			System.exit(0);
		}
		catch(Exception e)
		{
			output.displayOutput(String.format("Looks like there was an error somewhere that contain this information. \n%s", e.getMessage()));
			System.exit(0);
		}
	}

}
