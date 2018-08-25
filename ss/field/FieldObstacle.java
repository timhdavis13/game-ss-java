package com.thd.ss.field;

public class FieldObstacle implements FieldOccupant
{
	// Protected members:
	protected String name;
	protected FieldLocation location;
	
	// Constructor:
	public FieldObstacle(String name)
	{
		this.name = name;
	}
	
	// Implementations:
	
	/**
	 * @return the currentLocation
	 */
	@Override
	public FieldLocation getCurrentLocation() { return location; }

	/**
	 * @param currentLocation the currentLocation to set
	 */
	@Override
	public void setCurrentLocation(FieldLocation currentLocation) 
	{
		this.location = currentLocation;
	}
	
	public String getName() 
	{
		return name;
	}
	
	// General methods:
	@Override
	public String toString()
	{
		return this.name;
	}
	
}
