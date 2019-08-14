package org.soft.analysis.CodeRepresentation;

public enum ScopeType {
	Public(), Private(),Package(),Protected();

	public static ScopeType getScope(String s)
	{
		switch(s)
		{
			case("public"):
				return ScopeType.Public;
			case("private"):
				return ScopeType.Private;
			case("protected"):
				return ScopeType.Protected;
			default:
				return ScopeType.Package;
		}
	}

}