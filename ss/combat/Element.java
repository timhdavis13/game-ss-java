package com.thd.ss.combat;

public class Element implements Elemental
{
	// Private members:
	private ElementType elementType;
	
	// Constructors:
	public Element(ElementType elementType)
	{
		this.elementType = elementType;
	}

	@Override
	public ElementType getElementType() 
	{
		return this.elementType;
	}

}
