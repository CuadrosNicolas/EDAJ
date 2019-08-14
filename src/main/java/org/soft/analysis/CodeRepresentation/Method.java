package org.soft.analysis.CodeRepresentation;

public class Method {

	protected String _type;
	protected String _name;
	protected ScopeType _scopeType;
	protected Boolean _isVirtual;

	public Method(String returnType, String name, String scopeType, Boolean isVirtual) {
		_type = returnType;
		_name = name;
		_scopeType = ScopeType.getScope(scopeType);
		_isVirtual = isVirtual;
	}

	public String returnType() {
		return _type;
	}

	public String name() {
		return _name;
	}

	public ScopeType scopeType() {
		return _scopeType;
	}

	public Boolean isVirtual() {
		return _isVirtual;
	}

}