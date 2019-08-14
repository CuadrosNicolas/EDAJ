package org.soft.analysis.SourceCodeAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MethodAnalysisStructure
{
	public String path;
	public String methodName;
	public Integer AnalyzedDeclaration=0;
	public Integer Skipped=0;
	public HashMap<String,Integer> ExternalTypeDeclarations;
	public MethodAnalysisStructure(ArrayList<String> externalLib,String _path,String _methodName)
	 {
		 ExternalTypeDeclarations = new HashMap<String,Integer>();
		 path = _path;
		 methodName = _methodName;
		for(String n : externalLib)
		{
			ExternalTypeDeclarations.put(n,0);
		}
	 }
	 public void incSkipped()
	 {
		Skipped++;
		AnalyzedDeclaration++;
	 }
	 public void inc(String libraryName)
	 {
		 ExternalTypeDeclarations.put(libraryName,ExternalTypeDeclarations.get(libraryName)+1);
		 AnalyzedDeclaration++;
	 }
	 @Override
	 public String toString()
	 {
		String out = path+" "+methodName+" "+AnalyzedDeclaration.toString()+" "+Skipped.toString();
		for (Map.Entry<String, Integer> entry : ExternalTypeDeclarations.entrySet()) {
			out += " "+entry.getValue().toString();
		}
		return out;
	 }
}

