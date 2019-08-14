package org.soft.analysis.TypeTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MergeWalker extends TypeTreeWalker<Void> {


	protected PackageNode _receiver;
	protected PackageNode actual;
	protected String path = "";
	public MergeWalker(PackageNode receiver) {
		_receiver = receiver;
		actual = receiver;
	}

	public Void visit(Node n) {
		return null;
	}

	public Void visit(PackageNode n) {
		String lastPath = new String(path);
		PackageNode previous = actual;
		if(path!="")
		{
			if(actual.getElement().get(path)==null){
				PackageNode newNode = new PackageNode(path);
				actual.getElement().put(path,newNode);
			}
			actual = (PackageNode)actual.getElement().get(path) ;
		}
		for (Map.Entry<String, Node> entry : n.getElement().entrySet()) {
			path = entry.getKey();
			entry.getValue().accept(this);
		}
		path = lastPath;
		actual = previous;
		return null;
	}

	public Void visit(TypeNode n) {
		actual._elements.put(n.getID(), n.clone());
		return null;
	}
}