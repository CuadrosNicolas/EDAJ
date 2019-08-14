package org.soft.analysis.SourceCodeAnalysis;

public class ImportStatement{
	public ImportType type;
	public String path;
	ImportStatement(ImportType t,String p)
	{
		type = t;
		path = p;
	}
	@Override
	public String toString()
	{
		String str_type = "";
		switch(type)
		{
			case Single:
				str_type = "Single";
				break;
			case Multiple:
				str_type = "Multiple";
				break;
		}
		return str_type+" "+this.path;
	}
}