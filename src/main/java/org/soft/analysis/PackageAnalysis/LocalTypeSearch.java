package org.soft.analysis.PackageAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.*;

public class LocalTypeSearch {
	public static  ArrayList<String> TypeSearch(String folder)
	{
		ArrayList<String> LocalTypes = new ArrayList<String>();
		try{
			Files.walk(Paths.get(folder))
				.filter(Files::isRegularFile)
				.filter( (f)->{
					return (f.toString().endsWith("java"));
				})
				.forEach((f)->{
					try{

						BufferedReader br = new BufferedReader(new FileReader(f.toFile()));
						String fileName = f.toFile().getName();
						int pos = fileName.lastIndexOf(".");
						String className = fileName.substring(0,pos);
						String st;
						String packageName = "";
						Pattern packagePattern = Pattern.compile("package\\s+(([a-z]|[A-Z]|\\.)+)\\s*;");
						while ((st = br.readLine()) != null)
						{
							if(packageName=="")
							{
								Matcher m = packagePattern.matcher(st);
								boolean b = m.matches();
								if(b)
								{
									packageName = m.group(1);
									LocalTypes.add(packageName+"."+className);
									break;
								}
							}
						}
					}
					catch(Exception e){
							System.out.println(e.toString());
					}
				});
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return LocalTypes;
	}
}