package com.thd.ss.ui;

import com.thd.ss.field.FieldLocation;
import com.thd.ss.field.FieldMap;
import com.thd.ss.field.FieldMapManager;

public class MapGridView 
{
	private static final int CELL_NAME_LENGTH = 4;
	
	public static void showMap()
	{
		System.out.println(getMapText());
	}
	
	private static String getMapText()
	{
		FieldMap map = FieldMapManager.getFieldMap();
		
		String text = "Map: " + map.getSizeX() + "x" + map.getSizeY() + ":\n";
		
		for (int x = 0; x < map.getSizeX(); x++)
		{
			for (int y = 0; y < map.getSizeY(); y++)
			{
				text += "[" + getOccupantName(map.getLocation(x, y)) + "]";
			}
			
			text += "\n"; // New line every row.
		}
		
		return text;
	}
	
	private static String getOccupantName(FieldLocation location)
	{
		String text = "";
		
		if (location.isOccupied())
		{
			text = padRight(location.getOccupant().getName(), CELL_NAME_LENGTH); // Ensures won't get index out of bounds.
			return text.substring(0, CELL_NAME_LENGTH);
		}
		else
		{
			String blankCharacter = "-";
			
			for (int i = 0; i < CELL_NAME_LENGTH; i++)
			{
				text += blankCharacter;
			}
			
			return text;
		}
	}
	
	public static String padRight(String s, int n) 
	{
	     return String.format("%1$-" + n + "s", s);  
	}
}
