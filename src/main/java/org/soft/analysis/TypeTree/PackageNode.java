package org.soft.analysis.TypeTree;

import java.util.HashMap;
import java.util.Map;

public class PackageNode extends Node{
	protected HashMap<String,Node> _elements;
	PackageNode(String name)
	{
		_ID =  name;
		_elements = new HashMap<String,Node>();
	}
	public PackageNode clone()
	{
		PackageNode newNode = new PackageNode(_ID);
		for(String k : _elements.keySet())
		{
			newNode._elements.put(k,this._elements.get(k).clone());
		}
		return newNode;
	}
	public <T> T accept(TypeTreeWalker<T> walker) {
		return walker.visit(this);
	}
	public void add(String source,String name)
	 {
		 _elements.put(name,new TypeNode(source,name));
	 }
	 public Node access(String name)
	 {
		if(_elements.get(name)==null)
			_elements.put(name,new PackageNode(name));
		return _elements.get(name);
	 }
	 
	public Boolean isTerminal() {
		return false;
	}

	public PackageNode getTerminal()
	{
		PackageNode temp = new PackageNode(_ID);
		for (Map.Entry<String, Node> entry : _elements.entrySet()) {
			if(entry.getValue().isTerminal())
				temp._elements.put(entry.getKey(),entry.getValue());
		}
		return temp;
	}
	public HashMap<String,Node> getElement()
	{
		return _elements;
	}
	public void mergeWith(PackageNode n)
	{

	}
}