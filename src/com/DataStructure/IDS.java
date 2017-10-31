package com.DataStructure;

import java.util.HashSet;

import com.Board.AbstractBoard;
import com.GameState.BoardInteraction;
import com.Interaction.AbstractOutput;

public class IDS extends AbstractSearch{

	
	public IDS(BoardInteraction interaction, AbstractOutput output) {
		super(interaction, output);
	}

	@Override
	public boolean findSolution(AbstractBoard board) {
		
		int depth = 0;
		boolean cont = true;
		
		int count = 0;
		while(cont)
		{
			char result = DepthLimtiedSearch(board, depth, count);
			if(result != 'c')
				return true;
			depth++;
		}
		
		return false;
	}

	
	private char DepthLimtiedSearch(AbstractBoard board, int limit, int count)
	{
		HashSet<AbstractBoard> visited = new HashSet<AbstractBoard>();
		Node root = new Node(board);
		root.addDepth(0);

		
		return DLS(root, visited, limit, count);
	}
	
	private char DLS(Node node, HashSet<AbstractBoard> visited, int limit ,int countNode)
	{
		char cutOff = 'f';
		if(bi.checkWinState(node.getBoard()))
		{
			String solution = getSolution(node);
			output.displayOutput(solution);
			bi.printBoard(node.getBoard());
			output.displayOutput("Node Explored: " + countNode);
			return 't';	
		}
		else if(node.getDepth() == limit) 
			return 'c';
		else if(isVisited(visited, node)) 
			return 'f';
		else
		{
			countNode++;
			populateChildNodes(node);
			visited.add(node.getBoard());
			
			for(Node child : node.getChildrens())
			{
				child.addDepth(child.getParent().getDepth() + 1);
				char result = DLS(child, visited, limit ,countNode);
				if(result == 'c') 
					cutOff = 't'; 
				else if (result != 'f') 
					return result;
			}
			
		}
		
		if(cutOff == 't') 
			return 'c';
		else 
			return 'f';
	}
}
