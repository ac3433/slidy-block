package com.DataStructure;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.Board.AbstractBoard;
import com.GameState.BoardInteraction;
import com.GameState.Direction;
import com.Interaction.AbstractOutput;

public abstract class AbstractSearch {

	AbstractBoard board;
	BoardInteraction bi;
	AbstractOutput output;
	
	public AbstractSearch(BoardInteraction interaction, AbstractOutput output)
	{
		this.bi = interaction;
		this.output = output;
	}
	
	public abstract boolean findSolution(AbstractBoard board);
	
	//expend the node with their child
	protected void populateChildNodes(Node currentNode)
	{
		Map<Integer, List<String>> moves = bi.getAllMoves(currentNode.getBoard());
		
		for(int peice : moves.keySet())
		{
			for(String dir : moves.get(peice))
			{
				AbstractBoard newApplyBoard = bi.applyMoveCloning(currentNode.getBoard(), dir, peice);
				bi.normalizeBoard(newApplyBoard);
				Node n = new Node(newApplyBoard, currentNode, Direction.None.mapStringDirection(dir), peice);
				currentNode.addChild(n);
			}
			
		}
	}
	
	//now that we found the node that solves the puzzle
	//gotta track all the way back to the root node path and record the steps that was taken
	protected String getSolution(Node lastNode)
	{
		//now that we got the node, time to reverse all to the root node to get all the direction was made
		LinkedList<Node> getNodesToRoot = new LinkedList<Node>();
		getNodesToRoot.add(lastNode);
		
		int lengthToRoot = 0;
		String solution = "";
		
		while(!getNodesToRoot.isEmpty())
		{
			Node n = getNodesToRoot.remove();
			
			if(n.getParent() != null)
			{
				getNodesToRoot.add(n.getParent());
				solution = String.format("(%d, %s)\n", n.getPeice(), n.getDirection()) + solution;
				lengthToRoot++;
			}
		}
		
		output.displayOutput("Length of solution: " + lengthToRoot);
		
		return solution;
	}
	
	//check if the game state was made previously
	//this help avoid looping in duplicated data
	protected boolean isVisited(HashSet<AbstractBoard> visited, Node node)
	{
		for(AbstractBoard b : visited)
		{
			if(bi.compareBoardState(b.getBoard(), node.getBoard().getBoard()))
				return true;
		}
		
		return false;
	}
}
