package com.thd.ss.ui;

import com.thd.ss.combat.FieldCharacter;
import com.thd.ss.combat.battle.BattleTeamsManager;

public class TeamListView 
{
	public static void showTeams()
	{
		System.out.println("Teams:");
		
		System.out.println("---Players:");
		
		for (FieldCharacter character : BattleTeamsManager.getPlayerCharacters())
		{
			System.out.println(character);
		}
		
		System.out.println("---Enemies:");
		
		for (FieldCharacter character : BattleTeamsManager.getEnemyCharacters())
		{
			System.out.println(character);
		}
	}
}
