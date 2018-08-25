package com.thd.ss.field;

/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */

public class FieldMap 
{
	// Private members:
	private int xSize;
	private int ySize;
	
	private FieldLocation [][] locations;
	
	// Constructor:
	public FieldMap(int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		
		initializeMap();
	}
	
	private void initializeMap()
	{
		locations = new FieldLocation[xSize][ySize];
		
		for (int x = 0; x < this.xSize; x++)
		{
			for (int y = 0; y < this.ySize; y++)
			{
				locations[x][y] = new FieldLocation(this, x, y);
			}
		}
	}
	
	// Getters:

	/**
	 * @return the xSize
	 */
	public int getSizeX() { return xSize; }
	
	/**
	 * @return the ySize
	 */
	public int getSizeY() { return ySize; }
	
	/**
	 * @return the FieldLocation at position (x,y).
	 */
	public FieldLocation getLocation(int x, int y) throws PositionOutOfMapBoundsException
	{
		if (!isInBounds(x, y))
		{
			throw new PositionOutOfMapBoundsException("Position (" + x + "," + y + ") is out of the map boundaries."
					+ " Map dimensions: " + this.xSize + "x" + this.ySize + ".");
		}
		
		return locations[x][y];
	}
	
	// Other methods:
	
	public boolean isInBounds(int x, int y)
	{
		return (x >= 0 && y >= 0 &&
				x < this.xSize && y < this.ySize);
	}
	
	public void addOccupant(FieldOccupant occupant, int x, int y) throws AlreadyOccupiedException
	{
		getLocation(x, y).setOccupant(occupant);
	}
	
	public boolean isOccupied(int x, int y)
	{
		return getLocation(x, y).isOccupied();
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		String text = "Map: " + this.xSize + "x" + this.ySize + ":\n";
		
		for (int x = 0; x < this.xSize; x++)
		{
			for (int y = 0; y < this.ySize; y++)
			{
				text += locations[x][y] + "\n";
			}
		}
		
		return text;				
	}
}
