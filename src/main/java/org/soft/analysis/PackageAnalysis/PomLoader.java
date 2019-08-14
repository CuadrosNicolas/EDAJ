package org.soft.analysis.PackageAnalysis;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PomLoader {
	private String filePath;
	public PomLoader(String _filePath)
	{
		filePath = _filePath;

	}
	public ArrayList<PomDependency> load()
	{
		ArrayList<PomDependency> dependencies = new ArrayList<PomDependency>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document pom = builder.parse(new File(filePath));
			final NodeList dependenciesNode = pom.getElementsByTagName("dependency");
			for(int i = 0;i< dependenciesNode.getLength();i++)
			{
				final Element dependency =(Element) dependenciesNode.item(i);
				String groupId = ((Element) (dependency.getElementsByTagName("groupId").item(0))).getTextContent();
				String artifactId = ((Element) (dependency.getElementsByTagName("artifactId").item(0))).getTextContent();
				String version = ((Element) (dependency.getElementsByTagName("version").item(0))).getTextContent();
				dependencies.add(new PomDependency(groupId,artifactId,version));
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return dependencies;
	}
}