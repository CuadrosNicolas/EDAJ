package org.soft.analysis.PackageAnalysis;

import java.util.ArrayList;
import java.util.List;

import org.soft.analysis.CodeRepresentation.Clazz;

public class PomDependency {
	public String groupId;
	public String artifactId;
	public String version;
	List<Clazz> representations=new ArrayList<Clazz>();
	public PomDependency(String _groupId,String _artifactId,String _version)
	{
		groupId = _groupId;
		artifactId = _artifactId;
		version = _version;
	}

	@Override
	public String toString()
	{
			return groupId+"."+artifactId+"."+version;
	}
	public List<Clazz> load()
	{
		Loader l = new Loader("/home/cuacua/.m2/repository/"+String.join("/", groupId.split("\\."))+"/"+ String.join("/", artifactId.split("\\."))+"/"+version+"/"+artifactId+"-"+version+".jar");
		try{

			representations = l.loadAndScanJar();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return representations;
	}
}