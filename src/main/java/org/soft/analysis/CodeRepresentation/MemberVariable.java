package org.soft.analysis.CodeRepresentation;

public class MemberVariable{

	protected String _type;
	protected String _name;
	protected ScopeType _scopeType;

	public MemberVariable(String type,String name,String scopeType)
	{
		_type = type;
		_name = name;
		_scopeType = ScopeType.getScope(scopeType);
	}

	public String type()
	{
		return _type;
	}

	public String name() {
		return _name;
	}

	public ScopeType scopeType() {
		return _scopeType;
	}

}