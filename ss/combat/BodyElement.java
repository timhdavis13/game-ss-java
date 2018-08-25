package com.thd.ss.combat;

public class BodyElement implements Elemental
{
	// Private members:
	private ElementalBodied elementalBodied;
	
	// Constructors:
	public BodyElement(ElementalBodied elementalBodied)
	{
		this.elementalBodied = elementalBodied;
	}

	@Override
	public ElementType getElementType()
	{
		return this.elementalBodied.getPrimaryBodyElement();
	}
	

}
