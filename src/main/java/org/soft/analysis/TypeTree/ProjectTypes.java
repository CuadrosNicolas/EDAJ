package org.soft.analysis.TypeTree;

public class ProjectTypes extends PackageNode
{
	public ProjectTypes()
	{
		super("");
	}

	public void addType(String name,String source)
	{
		AddWalker adder=new AddWalker(source, name);
		this.accept(adder);
	}
	public Node get(String path)
	{
		GetWalker getter = new GetWalker(path);
		return this.accept(getter);
	}
	@Override
	public String toString()
	{
		PrintWalker walker = new PrintWalker();
		return this.accept(walker);
	}
	public void merge(Node n) {
		MergeWalker merger = new MergeWalker(this);
		n.accept(merger);
	}
	public void ShallowImport(String path,ProjectTypes from)
	{
		PackageNode pack = ((PackageNode)from.get(path)).getTerminal();
		this.merge(pack);
	}
	public void DirectImport(String path,ProjectTypes from)
	{
		this.merge(from.get(path));
	}
	public void DeepImport(String path,ProjectTypes from)
	{
		this.merge(from.get(path));
	}
}