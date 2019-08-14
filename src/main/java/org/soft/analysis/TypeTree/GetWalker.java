package org.soft.analysis.TypeTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class GetWalker extends TypeTreeWalker<Node> {

	protected String _path;
	protected ArrayList<String> queue;
	public GetWalker(String path) {
		_path = path;
	 	queue = new ArrayList<String>(Arrays.asList(path.split("\\.")));
	}

	public Node visit(Node n) {
		return null;
	}

	public Node visit(PackageNode n) {
		String act = queue.get(0);
		if(queue.size()>0)
			queue.remove(0);
		Node next = n.access(act);
		if(next!=null && queue.size()==0)
		{
			return next;
		}
		else if(next!=null)
		{
			return next.accept(this);
		}
		return null;
	}

	public Node visit(TypeNode n) {
		return n;
	}
}