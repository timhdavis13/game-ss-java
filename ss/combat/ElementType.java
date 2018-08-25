package com.thd.ss.combat;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public enum ElementType 
{
	NON_ELEMENTAL("Non-Elemental", 0),
	METAL("Metal", 1),
	FIRE("Fire", 2),
	WATER("Water", 3),
	WIND("Wind", 4),
	LIGHTNING("Lightning", 5),
	EARTH("Earth", 6),
	PLANT("Plant", 7),
	ICE("Ice", 8),
	LIGHT("Light", 9),
	DARK("Dark", 10);
	
	String name;
	int index;
	
	ElementType(String elementName, int elementIndex)
	{
		name = elementName;
		index = elementIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getIndex()
	{
		return index;
	}
}
