package com.DataStructure;

import java.util.HashSet;
import java.util.LinkedList;

import com.Board.AbstractBoard;
import com.GameState.BoardInteraction;
import com.Interaction.AbstractOutput;

public class BFS extends AbstractSearch{

	int i = 0;
	public BFS(BoardInteraction interaction, AbstractOutput output) {
		super(interaction, output);

	}

	@Override
	public boolean findSolution(AbstractBoard board) {
		HashSet<AbstractBoard> visited = new HashSet<AbstractBoard>();
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		
		//initalize the source tree
		Node root = new Node(board);
		nextToVisit.add(root);
		
		while(!nextToVisit.isEmpty())
		{
			Node node = nextToVisit.remove();
			//check if the board have the goal
			if(bi.checkWinState(node.getBoard()))
			{
				String solution = getSolution(node);
				output.displayOutput(solution);
				bi.printBoard(node.getBoard());
				
				return true;	
			}	
			//if the node game state is what we have been skip it
			if(isVisited(visited, node))
				continue;
			
			//add the node we have visited
			visited.add(node.getBoard());
			
			//this is to expand on the node of the children
			populateChildNodes(node);
			
			//add all the child from the current node to be search next
			for(Node child : node.getChildrens())
			{
				nextToVisit.add(child);
			}
		}
		
		return false;
	}

}
