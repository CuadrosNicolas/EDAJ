package org.soft.analysis.SourceCodeAnalysis;

import java.util.ArrayList;

import org.soft.analysis.CodeRepresentation.Clazz;



public class AnalysedFile {
	protected ArrayList<ImportStatement> imports;
	protected String path;
	protected Clazz clazz;
	public AnalysedFile(String p)
	{
		path = p;
	}
}