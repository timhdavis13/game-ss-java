package com.thd.ss.field;

// Singleton Pattern:
public class FieldMapManager 
{
	// Singleton instance:
	private static FieldMapManager instance;
	
	// Private Constructor for Singleton Pattern:
	private FieldMapManager()
	{
		// Nothing to do.
	}
	
	// Get instance:
	public static FieldMapManager getInstance()
	{
		if (instance == null)
		{
			// Thread-safe checks (check null before to reduce overhead):
			synchronized (FieldMapManager.class)
			{
				if (instance == null) 
				{
					instance = new FieldMapManager();
				}
			}
		}
		
		return instance;
	}
	
	// Public Static methods:
	
	public static void createMap(int xSize, int ySize)
	{
		getInstance().map = new FieldMap(xSize, ySize);
	}
	
	public static FieldMap getFieldMap()
	{
		return getInstance().getMap();
	}
	
	public static void drawMap()
	{
		// TODO: change to graphical view...
		System.out.println(getInstance().map.toString());
	}
	
	// Instance members:
	
	private FieldMap map;
	
	// Getters:
	
	public FieldMap getMap()
	{
		return this.map;
	}
	
	

}
