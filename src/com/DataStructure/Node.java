package com.DataStructure;

import java.util.HashSet;
import java.util.List;

import com.Board.AbstractBoard;
import com.GameState.Direction;

public class Node {

	private AbstractBoard board;
	private Node parent;
	private HashSet<Node> children;
	private Direction dir;
	private int peice;
	private int depth;
	
	public Node(AbstractBoard board)
	{
		this.board = board;
		children = new HashSet<Node>();
	}
	
	public Node(AbstractBoard board, Node parent, Direction dir, int peice)
	{
		this.board = board;
		this.parent = parent;
		this.dir = dir;
		this.peice = peice;
		children = new HashSet<Node>();
	}
	
	//this could be part of the constructor, but did not want to add requirement to bfs and dfs
	public void addDepth(int depth)
	{
		this.depth = depth;
	}
	
	public void addChild(Node child)
	{
		children.add(child);
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public AbstractBoard getBoard()
	{
		return board;
	}
	
	
	public HashSet<Node> getChildrens()
	{
		return children;
	}
	
	public int getPeice()
	{
		return peice;
	}
	
	public String getDirection()
	{
		return dir.name();
	}
	
	public int getDepth()
	{
		return depth;
	}
}

