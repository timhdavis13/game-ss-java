package com.thd.ss.field;

public class Coordinate2D 
{
	// Protected members:
	protected int xCoordinate;
	protected int yCoordinate;
	
	// Constructor:
	public Coordinate2D(int x, int y)
	{
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	// Getters and setters:
	
	/**
	 * @return the xCoordinate
	 */
	public int getX() { return xCoordinate; }
	
	/**
	 * @return the yCoordinate
	 */
	public int getY() { return yCoordinate; }
	
	// General methods:
	
	@Override
	public boolean equals(Object other)
	{
		// First check if same object reference:
        if (other == this) 
        {
            return true;
        }
 
        // Check if is an instance of this class:
        if (!(other instanceof Coordinate2D))
        {
            return false;
        }
         
        // Check if have same x and y values:
        Coordinate2D otherCoordinate2D = (Coordinate2D) other;
          
        return (this.getX() == otherCoordinate2D.getX()
        		&& this.getY() == otherCoordinate2D.getY());
	}
}
