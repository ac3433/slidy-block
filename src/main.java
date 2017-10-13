import java.io.File;
import java.util.List;
import java.util.Map;

import com.Board.*;
import com.GameState.BoardInteraction;
import com.Interaction.*;

public class main {
	
	public static void main(String[] args) {
		
		AbstractInput input = new ConsoleInput();
		AbstractOutput output = new ConsoleOutput();
		
		try
		{
			if(args.length != 1)
			{
				output.displayOutput("Please enter a path in the argument");
				System.exit(0);
			}
			
			if(!new File(args[0]).exists())
			{
				output.displayOutput("File cannot be found");
				System.exit(0);
			}
			
			AbstractBoard board = new CustomBoard(input, output, args[0]);

			BoardInteraction bi = new BoardInteraction(output);
			AbstractBoard board1 = (AbstractBoard) bi.clone(board);
			board1.getBoard()[2][3] = 300;
			bi.printBoard(board);
			bi.printBoard(board1);
			
			Map<Integer, List<String>> data = bi.getAllMoves(board);
			//			
//			AbstractBoard board2 = (AbstractBoard) board.clone();
//			board2.fillBlock(2, 3, -2345);
			
			System.out.println("ets");
		}
		catch(Exception e)
		{
			output.displayOutput(String.format("Looks like there was an error somewhere that contain this information. \n%s", e.getMessage()));
		}
	}

}
