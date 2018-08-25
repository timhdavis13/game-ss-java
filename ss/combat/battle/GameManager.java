package com.thd.ss.combat.battle;

import com.thd.ss.field.MapLoader;

//Singleton Pattern:
public class GameManager 
{
	// Singleton instance:
	private static GameManager instance;
	
	// Private Constructor for Singleton Pattern:
	private GameManager()
	{
		// Nothing to do.
	}
	
	// Get instance:
	public static GameManager getInstance()
	{
		if (instance == null)
		{
			// Thread-safe checks (check null before to reduce overhead):
			synchronized (GameManager.class)
			{
				if (instance == null) 
				{
					instance = new GameManager();
				}
			}
		}
		
		return instance;
	}
	
	// Other methods:
	
	public static void loadMap(String mapID)
	{
		MapLoader.loadMapFromMapID(mapID);
	}
	
	public static void startBattle()
	{
		BattleManager.beginBattle();
	}
	
	
}
