package org.soft.analysis.TypeTree;

public class Node{
	protected String _ID;
	public <T>  T accept(TypeTreeWalker<T> walker)
	{
		return walker.visit(this);
	}

	public Node clone() {
		return new Node();
	}
	public Boolean isTerminal()
	{
		return true;
	}
	public String getID()
	{
		return _ID;
	}

}