import java.io.File;

import com.Board.*;
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
//			
//			AbstractBoard board2 = (AbstractBoard) board.clone();
//			board2.fillBlock(2, 3, -2345);
			board.printBoard();
//			board2.printBoard();
		}
		catch(Exception e)
		{
			output.displayOutput(String.format("Looks like there was an error somewhere that contain this information. \n%s", e.getMessage()));
		}
	}

}
