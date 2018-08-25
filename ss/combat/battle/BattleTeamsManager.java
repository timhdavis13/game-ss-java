package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.combat.FieldCharacter;

// Singleton Pattern:
public class BattleTeamsManager 
{
	// Singleton instance:
	private static BattleTeamsManager instance;
	
	// Private Constructor for Singleton Pattern:
	private BattleTeamsManager()
	{
		this.playerTeamMembers = new ArrayList<FieldCharacter>();
		this.enemyTeamMembers = new ArrayList<FieldCharacter>();
	}
	
	// Get instance:
	public static BattleTeamsManager getInstance()
	{
		if (instance == null)
		{
			// Thread-safe checks (check null before to reduce overhead):
			synchronized (BattleTeamsManager.class)
			{
				if (instance == null) 
				{
					instance = new BattleTeamsManager();
				}
			}
		}
		
		return instance;
	}
	
	// Private instance members:
	private List<FieldCharacter> playerTeamMembers;
	private List<FieldCharacter> enemyTeamMembers;
	
	// Other static members:
	
	//// Player Team:
	
	public static List<FieldCharacter> getPlayerCharacters()
	{
		return getInstance().playerTeamMembers;
	}
	
	public static void addPlayerCharacter(FieldCharacter playerCharacter)
	{
		getInstance().playerTeamMembers.add(playerCharacter);
	}
	
	public static void removePlayerCharacter(FieldCharacter playerCharacter)
	{
		getInstance().playerTeamMembers.remove(playerCharacter);
	}
	
	//// Enemy Team:
	
	public static List<FieldCharacter> getEnemyCharacters()
	{
		return getInstance().enemyTeamMembers;
	}
	
	public static void addEnemyCharacter(FieldCharacter enemyCharacter)
	{
		getInstance().enemyTeamMembers.add(enemyCharacter);
	}
	
	public static void removeEnemyCharacter(FieldCharacter enemyCharacter)
	{
		getInstance().enemyTeamMembers.remove(enemyCharacter);
	}
	
}
