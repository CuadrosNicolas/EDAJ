package org.soft.analysis.TypeTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AddWalker extends TypeTreeWalker<Void> {

	protected String _source;
	protected String _path;
	protected ArrayList<String> queue;
	public AddWalker(String source,String path) {
		_source = source;
		_path = path;
	 	queue = new ArrayList<String>(Arrays.asList(path.split("\\.")));
	}

	public Void visit(Node n) {
		return null;
	}

	public Void visit(PackageNode n) {
		String act = queue.get(0);
		if(act!=null && queue.size()==1)
		{
			n.add(_source,act);
		}else if(queue.size() > 1){
			queue.remove(0);
			n.access(act).accept(this);
		}
		return null;
	}

	public Void visit(TypeNode n) {
		return null;
	}
}