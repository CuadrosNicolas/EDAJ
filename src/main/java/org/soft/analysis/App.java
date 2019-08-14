package org.soft.analysis;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.soft.analysis.CodeRepresentation.Clazz;
import org.soft.analysis.PackageAnalysis.*;
import org.soft.analysis.SourceCodeAnalysis.MethodAnalysisStructure;
import org.soft.analysis.SourceCodeAnalysis.TypeExtractor;
import org.soft.analysis.TypeTree.Node;
import org.soft.analysis.TypeTree.ProjectTypes;
import org.soft.antlr.*;

/**
 * Hello world!
 *
 */
public class App
{
        public static void main(String[] args)
    {
        System.out.println("LOOKING FOR DEPENDENCIES");
        PomLoader test = new PomLoader("/pom.xml");
        ArrayList<PomDependency> dep = test.load();
        ArrayList<String> externalLib = new ArrayList<String>();
        ProjectTypes types = new ProjectTypes();
        ArrayList<MethodAnalysisStructure> results = new ArrayList<>();
        for(PomDependency p : dep){
            System.out.println("\t ANALYSING : "+p.toString());
            List<Clazz> r = p.load();
            externalLib.add(p.toString());
            for(Clazz c : r)
            {
                types.addType(c.name(),p.toString());
            }
        }
        System.out.println("EXTRACTING PROJECT CLASSES");
        ArrayList<String> t = LocalTypeSearch.TypeSearch("./src");
        externalLib.add("local");
        for(String s : t)
        {
            System.out.println("\t ANALYSING : " + s);
            types.addType(s, "local");
        }
        try{
            String folder = args[0]+"/src";
            System.out.println("ANALYSING PROJECT");
             Files.walk(Paths.get(folder))
             .filter(Files::isRegularFile)
             .filter( (f)->{
                return (f.toString().endsWith("java"));
            })
            .forEach((f)->{
                try{
                    System.out.println("\tANALYSING FILE : "+f.toFile().getAbsolutePath());
                    results.addAll(TypeExtractor.parseJava(f.toFile(),types,externalLib));
                }
                catch(Exception e)
                {

                }
            });
            FileWriter fileWriter = new FileWriter("./output.csv");
            String header = "FILE_PATH METHOD_NAME NUMBER_OF_ANALYSED_FILE SKIPPED_IDENTIFICATION";
            for(String s :externalLib)
            {
                header += " "+s;
            }
            fileWriter.write(header+"\n");
            System.out.println(header);
            results.forEach(r->{
                try{
                    fileWriter.write(r.toString()+"\n");
                }
                catch(Exception e)
                {

                }
                System.out.println(r.toString());
            });
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        System.out.println(args[0]);

    }
}
