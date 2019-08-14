package org.soft.analysis.TypeTree;

public class TypeNode extends Node {
	protected String _source;
	public TypeNode(String source,String name) {
		_source = source;
		_ID = name;
	}
	
	public <T> T accept(TypeTreeWalker<T> walker) {
		return walker.visit(this);
	}
	public String get()
	{
		return _source;
	}
	
	public TypeNode clone() {
		return new TypeNode(_source,_ID);
	}
	
	public Boolean isTerminal() {
		return true;
	}
}