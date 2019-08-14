package org.soft.analysis.SourceCodeAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.soft.analysis.TypeTree.ProjectTypes;
import org.soft.antlr.*;
import org.soft.antlr.Java8Parser.InterfaceTypeContext;
import org.soft.antlr.Java8Parser.SingleStaticImportDeclarationContext;
import org.soft.antlr.Java8Parser.SingleTypeImportDeclarationContext;
import org.soft.antlr.Java8Parser.StaticImportOnDemandDeclarationContext;
import org.soft.antlr.Java8Parser.SuperclassContext;
import org.soft.antlr.Java8Parser.SuperinterfacesContext;
import org.soft.antlr.Java8Parser.TypeImportOnDemandDeclarationContext;
import org.antlr.v4.runtime.Token;

import org.soft.analysis.TypeTree.*;

public class TypeAnalyser extends Java8BaseListener {

	// ... getter for errors
	/**
	 * Import related stuff
	 */
	protected ProjectTypes _classTypes;
	protected ProjectTypes _projectTypes;
	protected String _path;
	protected Stack<MethodAnalysisStructure> BlockMemory;
	protected ArrayList<MethodAnalysisStructure> results;
	protected ArrayList<String> externalLib;
	public TypeAnalyser(ProjectTypes projectTypes,String path,ArrayList<String> _externalLib)
	{
		_classTypes = new ProjectTypes();
		_path = path;
		externalLib = _externalLib;
		_projectTypes = projectTypes;
		results = new ArrayList<MethodAnalysisStructure>();
		BlockMemory = new Stack<MethodAnalysisStructure>();
		BlockMemory.push(new MethodAnalysisStructure(externalLib, _path,"@MEMBERS"));
	}
	public ArrayList<MethodAnalysisStructure> getResults()
	{
		return results;
	}
	@Override
	public void enterImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {
		//System.out.println("\t\tIMPORT STATEMENT : "+ctx.getText());
		try {

			SingleTypeImportDeclarationContext node = ctx.singleTypeImportDeclaration();
			if(!(node.typeName().getText().startsWith("java")))
			{
				//System.out.println("\t\tIMPORTING : "+node.typeName().getText());
				_classTypes.DirectImport(node.typeName().getText(), _projectTypes);
			}
		} catch (Exception e) {
			try {

				TypeImportOnDemandDeclarationContext node = ctx.typeImportOnDemandDeclaration();
				//System.out.println(node.getText().replace("import", "").replace(".*;", ""));
				if (!(node.getText().replace("import", "").replace(".*;", "").startsWith("java"))) {
					//System.out.println("\t\tIMPORTING * : " + node.getText().replace("import", "").replace(".*;", ""));
					_classTypes.ShallowImport(node.getText().replace("import", "").replace(".*;", ""), _projectTypes);
				}

			} catch (Exception x) {
				// System.out.println(e.toString()+"AGHHHHH");
			}
		}
	}

	@Override
	public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
		if(!ctx.packageName().getText().startsWith("java"))
		{
			//System.out.println("\t\tIMPORTING PACKAGE : " + ctx.packageName().getText());
			_classTypes.ShallowImport(ctx.packageName().getText(), _projectTypes);
		}
	}

	/**
	 * Method entering management
	 */
	@Override
	public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx)
	{
		//System.out.println("\t\tENTERING : "+ctx.methodHeader().methodDeclarator().Identifier().getText());
		MethodAnalysisStructure r = new MethodAnalysisStructure(externalLib, _path,  ctx.methodHeader().methodDeclarator().Identifier().getText());
		BlockMemory.push(r);
		results.add(r);
	}
	@Override
	public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx)
	{
		//System.out.println("\t\tQUITTING.");
		BlockMemory.pop();
	}
	@Override
	public 	void enterUnannType(Java8Parser.UnannTypeContext ctx)
	{
			try{
				String type = ((TypeNode)_classTypes.get(ctx.getText())).get();
				if(type!=null)
				{
					//System.out.println("\t\t\tFOUND "+ ctx.getText() + " "+type);
					BlockMemory.peek().inc(type);
				}
				else{
					//System.out.println("\t\t\tSKIPPED");
				}
			}
			catch(Exception e)
			{
				BlockMemory.peek().incSkipped();

			}
	}
	@Override
	public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
		//Name
		//Token _name=ctx.Identifier().getSymbol();
		//Token _super=null;
		//ArrayList<Token> _interfaces = new ArrayList<>();
		//Super
		try{

			//_super = ctx.superclass().classType().getStart();
		}
		catch(Exception e)
		{

		}
		//Interfaces
		try{

			//for(InterfaceTypeContext i : ctx.superinterfaces().interfaceTypeList().interfaceType())
			//{
				//_interfaces.add(i.getStart());
			//}
		}catch(Exception e)
		{

		}
	}
	/**
	 * Method chaining
	 */
	@Override
	public void enterMethodName(Java8Parser.MethodNameContext ctx){
		//System.out.println(ctx.getText());
	}
	@Override
	public 	void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx)
	{
		//System.out.println(ctx.Identifier().getText());
	}

	@Override
	public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx)
	{
		//System.out.println(ctx.Identifier().getText());
	}
	@Override
	public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx){
		//System.out.println(ctx.Identifier().getText());
	}
	/**
	 * Field access Chaining
	 */

	 /**
	  * Add StatementExpression enter
		  Sort by token start
		  Then analyse
	  */

	/**
	 * Variable declaration
	 * FOR INTERFACE
	 */
	@Override
	public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx)
	{
		for(Java8Parser.VariableDeclaratorContext a : ctx.variableDeclaratorList().variableDeclarator())
		{
			//System.out.println("Variable declaration : "+a.variableDeclaratorId().getText());
		}
	}
	@Override
	public void enterLocalVariableDeclarationStatement(Java8Parser.LocalVariableDeclarationStatementContext ctx){

		for(Java8Parser.VariableDeclaratorContext a : ctx.localVariableDeclaration().variableDeclaratorList().variableDeclarator())
		{
			//System.out.println("Variable declaration : "+a.variableDeclaratorId().getText());
		}
	}



	@Override
	public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {

		//System.out.println("Declaring method : "+ctx.Identifier().getText());
	}

}