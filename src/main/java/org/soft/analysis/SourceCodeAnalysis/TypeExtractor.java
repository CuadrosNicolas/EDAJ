package org.soft.analysis.SourceCodeAnalysis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.soft.analysis.TypeTree.ProjectTypes;
import org.soft.antlr.*;

public class TypeExtractor {


	public static ArrayList<MethodAnalysisStructure> parseJava(File file, ProjectTypes projectTypes, ArrayList<String> _externalLib) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		String code = new String(encoded, Charset.forName("UTF-8"));
		Java8Lexer lexer = new Java8Lexer(new ANTLRInputStream(code));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Java8Parser parser = new Java8Parser(tokens);
		ParseTree tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker();
		TypeAnalyser listener = new TypeAnalyser(projectTypes,file.getAbsolutePath(),_externalLib);
		walker.walk(listener, tree);

		return listener.getResults();
		//return Objects.requireNonNull(parser.classDeclaration());
	}
}