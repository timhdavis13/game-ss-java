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
	
	public static List<FieldCharacter> getLivingPlayerTeamMembers()
	{
		List<FieldCharacter> livingPlayerTeamMembers = new ArrayList<FieldCharacter>();
		
		for (FieldCharacter character : getInstance().playerTeamMembers)
		{
			if (character.isAlive())
			{
				livingPlayerTeamMembers.add(character);
			}
		}
		
		return livingPlayerTeamMembers;
	}
	
	public static List<FieldCharacter> getDefeatedPlayerTeamMember()
	{
		List<FieldCharacter> defeatedPlayerTeamMembers = new ArrayList<FieldCharacter>();
		
		for (FieldCharacter character : getInstance().playerTeamMembers)
		{
			if (character.isDefeated())
			{
				defeatedPlayerTeamMembers.add(character);
			}
		}
		
		return defeatedPlayerTeamMembers;
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
	
	public static List<FieldCharacter> getLivingEnemyTeamMembers()
	{
		List<FieldCharacter> livingEnemyTeamMembers = new ArrayList<FieldCharacter>();
		
		for (FieldCharacter character : getInstance().enemyTeamMembers)
		{
			if (character.isAlive())
			{
				livingEnemyTeamMembers.add(character);
			}
		}
		
		return livingEnemyTeamMembers;
	}
	
	public static List<FieldCharacter> getDefeatedEnemyTeamMembers()
	{
		List<FieldCharacter> defeatedEnemyTeamMembers = new ArrayList<FieldCharacter>();
		
		for (FieldCharacter character : getInstance().enemyTeamMembers)
		{
			if (character.isDefeated())
			{
				defeatedEnemyTeamMembers.add(character);
			}
		}
		
		return defeatedEnemyTeamMembers;
	}
	
}
