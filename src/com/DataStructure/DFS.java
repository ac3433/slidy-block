package com.DataStructure;

import java.util.HashSet;

import com.Board.AbstractBoard;
import com.GameState.BoardInteraction;
import com.Interaction.AbstractOutput;

public class DFS extends AbstractSearch{

	public DFS(BoardInteraction interaction, AbstractOutput output) {
		super(interaction, output);
	}

	@Override
	public boolean findSolution(AbstractBoard board) {
		HashSet<AbstractBoard> visited = new HashSet<AbstractBoard>();
		
		Node root = new Node(board);
		int nodeCount = 0;
		return findDFS(root, visited, nodeCount);
	}

	//recursively find the solution
	private boolean findDFS(Node node, HashSet<AbstractBoard> visited, int nodeCount)
	{
		if(isVisited(visited, node))
			return false;
		
		visited.add(node.getBoard());
		nodeCount++;
		if(bi.checkWinState(node.getBoard()))
		{
			//since it is recursive going back, i didn't need to get getSolution to trace back
			//could remove this and just directly access the node
			String solution = getSolution(node);
			output.displayOutput(solution);
			bi.printBoard(node.getBoard());
			output.displayOutput("Node count: " + nodeCount);
			return true;	
		}
		
		//if the solution is not there, then populate the children to be looked at
		populateChildNodes(node);
		
		for(Node child : node.getChildrens())
		{
			if(findDFS(child, visited, nodeCount))
				return true;
		}
		
		return false;
		
	}
}
