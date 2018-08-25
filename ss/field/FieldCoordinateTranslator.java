package com.thd.ss.field;

public class FieldCoordinateTranslator 
{
	public static FieldLocation getAbsoluteLocation(FieldLocation sourceLocation, RelativeLocation relativeLocation)
	{
		return sourceLocation.getMap().
				getLocation(sourceLocation.getX() + relativeLocation.getX(), 
				sourceLocation.getX() + relativeLocation.getX());
	}

}
