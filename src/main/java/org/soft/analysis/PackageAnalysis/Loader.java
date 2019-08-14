package org.soft.analysis.PackageAnalysis;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.lang.reflect.Modifier;
import org.soft.analysis.CodeRepresentation.Clazz;

/**
 * Hello world!
 *
 */
public class Loader extends URLClassLoader {
	private String path;
	public Loader(String _path)
	{
		super(new URL[0]);
		path = _path;
	}
	public boolean test(){
		return true;
	}
	private static String getScope(int mod)
	{
		if(Modifier.isPublic(mod))
		{
			return "public";
		}
		else if (Modifier.isPrivate(mod)) {
			return "private";
		}
		else if (Modifier.isProtected(mod)) {
			return "protected";
		}
		else
			return "";
	}
	public List<Clazz> loadAndScanJar()
			throws ClassNotFoundException, ZipException, IOException {

		// Load the jar file into the JVM
		// You can remove this if the jar file already loaded.
		super.addURL(new File(path).toURI().toURL());
		List<Clazz> representations=new ArrayList<Clazz>();

		// Count the classes loaded
		// Your jar file
		File f = new File(path);

		JarFile jar = new JarFile(path);
		// Getting the files into the jar
		Enumeration<? extends JarEntry> enumeration = jar.entries();

		// Iterates into the files in the jar file
		while (enumeration.hasMoreElements()) {
			ZipEntry zipEntry = enumeration.nextElement();
			// Is this a class?
			if (zipEntry.getName().endsWith(".class")) {
				// Relative path of file into the jar.
				String className = zipEntry.getName();
				// Complete class name
				className = className.replace(".class", "").replace("/", ".");
				// Load class definition from JVM
				Class<?> clazz = this.loadClass(className);
				try {
						Clazz actual = new Clazz(clazz.getName());
						for (java.lang.reflect.Method m : clazz.getMethods()) {
							actual.addMethod(m.getReturnType().getName(), m.getName(), getScope(m.getModifiers()),
									Modifier.isAbstract(m.getModifiers()), Modifier.isStatic(m.getModifiers()));
						}
						for (java.lang.reflect.Field m : clazz.getFields()) {
							actual.addVariable(m.getType().getName(), m.getName(), getScope(m.getModifiers()),
									Modifier.isStatic(m.getModifiers()));
						}
						representations.add(actual);

				} catch (ClassCastException e) {
				}
			}
		}
		jar.close();
		return representations;
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
