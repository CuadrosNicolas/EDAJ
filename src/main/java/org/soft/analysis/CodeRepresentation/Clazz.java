package org.soft.analysis.CodeRepresentation;

import java.util.ArrayList;

public class Clazz{

	protected ArrayList<MemberVariable> _MemberVariables;
	protected ArrayList<MemberVariable> _StaticMemberVariables;

	protected ArrayList<Method> _Methods;
	protected ArrayList<Method> _StaticMethods;

	protected String _name;
	protected ScopeType _scopeType;


	public Clazz(String name)
	{
		_name = name;
		_MemberVariables = new ArrayList<>();
		_StaticMemberVariables = new ArrayList<>();
		_Methods = new ArrayList<>();
		_StaticMethods = new ArrayList<>();
	}
	public void  addVariable(String type,String name,String scopeType,Boolean statiz)
	{
		if(statiz)
		{
			_StaticMemberVariables.add(new MemberVariable(type,name,scopeType));
		}
		else{
			_MemberVariables.add(new MemberVariable(type, name, scopeType));

		}
	}
	
	public void addMethod(String type, String name, String scopeType,Boolean isVirtual, Boolean statiz) {
		if (statiz) {
			_StaticMethods.add(new Method(type, name, scopeType,isVirtual));
		} else {
			_Methods.add(new Method(type, name, scopeType,isVirtual));

		}
	}

	public String name()
	{
		return _name;
	}
}