package org.soft.analysis.TypeTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;

import org.abego.treelayout.internal.util.java.lang.string.StringUtil;

public class PrintWalker extends TypeTreeWalker<String> {

	protected Integer counter =0;
	public PrintWalker() {

	}

	public String visit(Node n) {
		return null;
	}

	public String visit(PackageNode n) {
		String out = "";
		out += String.join("", Collections.nCopies(counter, "\t")) + n.getID()+"\n";
		counter++;
		for (Map.Entry<String, Node> entry : n.getElement().entrySet()) {
			out += entry.getValue().accept(this);
		}
		counter--;
		return out;

	}

	public String visit(TypeNode n) {
		return String.join("", Collections.nCopies(counter, "\t")) + n.getID() + "\n";
	}
}