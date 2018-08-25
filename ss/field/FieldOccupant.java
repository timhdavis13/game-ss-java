package com.thd.ss.field;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */

public interface FieldOccupant
{
	String getName();
	FieldLocation getCurrentLocation();
	void setCurrentLocation(FieldLocation currentLocation);
}
