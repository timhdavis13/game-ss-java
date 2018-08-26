package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.RandomGenerator;
import com.thd.ss.combat.FieldCharacter;
import com.thd.ss.combat.SkillTurnAction;
import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillUser;
import com.thd.ss.ui.MapGridView;
import com.thd.ss.ui.PressEnterToContinueView;
import com.thd.ss.ui.SkillSelectionView;
import com.thd.ss.ui.TeamListView;

// Singleton Pattern:
public class BattleManager implements TurnBeginListener, TurnEndListener, TurnActionCompleteListener // TODO: create a Battle class that this class uses...
{
	// Private members:
	private List<TurnAction> nextTurnActions;
	private TurnManager turnManager;
	
	// Singleton instance:
	private static BattleManager instance;
	
	// Private Constructor for Singleton Pattern:
	private BattleManager()
	{
		this.nextTurnActions = new ArrayList<TurnAction>();
		
		this.turnManager = new TurnManager();
	}
	
	// Get instance:
	public static BattleManager getInstance()
	{
		if (instance == null)
		{
			// Thread-safe checks (check null before to reduce overhead):
			synchronized (BattleManager.class)
			{
				if (instance == null) 
				{
					instance = new BattleManager();
				}
			}
		}
		
		return instance;
	}
	
	
	// Other methods:
	
	public static void addTurnAction(TurnAction turnAction)
	{
		getInstance().nextTurnActions.add(turnAction);
	}
	
	
	public static void beginBattle()
	{
		// TODO battle setup...
		
		getInstance().setupBattle();
		
		getInstance().getNextTurnActions();
	}
	
	
	public static void endBattle()
	{
		// TODO battle cleanup...
		System.out.println("====> Battle Manager: endBattle() called."); // TODO: TESTING ONLY.
		
	}
	
	public void setupBattle()
	{
		this.turnManager.registerTurnBeginListener(this);
		this.turnManager.registerTurnEndListener(this);
		
		this.turnManager.registerTurnActionCompleteListener(this);
	}
	
	public void cleanupBattle()
	{
		// NOTE: sTo avoid ConcurrentModificationException, cannot unregister from OnTurnEnd().
		this.turnManager.unregisterTurnBeginListener(this);
		this.turnManager.unregisterTurnEndListener(this);
		
		this.turnManager.unregisterTurnActionCompleteListener(this);
	}
	
	public boolean isBattleOver()
	{
		// Battle is over if all enemies or all players are defeated:
		
		System.out.println("LivingEnemyTeamMembers().size() == " + BattleTeamsManager.getLivingEnemyTeamMembers().size());
		System.out.println("LivingPlayerTeamMembers().size() == " + BattleTeamsManager.getLivingPlayerTeamMembers().size());
		
		return (BattleTeamsManager.getLivingEnemyTeamMembers().size() < 1
		|| BattleTeamsManager.getLivingPlayerTeamMembers().size() < 1);
	}
	
	
	public void doNextTurn()
	{
		System.out.println("====> BattleManager: doNextTurn(): nextTurnActions=" + nextTurnActions); // TODO: TESTING ONLY.
		
		if (this.nextTurnActions == null || this.nextTurnActions.isEmpty())
		{
			// TODO: check error
			
			System.out.println("====> BattleManager: doNextTurn(): nextTurnActions == null || nextTurnActions.isEmpty()"); // TODO: TESTING ONLY.
			
			endBattle();
			
			return;
		}
		
		this.turnManager.doNextTurn(nextTurnActions);
	}
	
	
	public void getNextTurnActions()
	{
		// Send event to TurnActionSelector to notify can start showing UI to pick actions... TODO
		
		System.out.println("====> BattleManager: getNextTurnActions(): Waiting for next turn actions...."); // TODO: TESTING ONLY.
		System.out.println();
		
		// TODO ... Fix up ...
		for (FieldCharacter fieldCharacter : BattleTeamsManager.getPlayerCharacters())
		{
			if (fieldCharacter.getHealthPoints().getCurrentValue() > 0)
			{
				SkillUser skillUser = (SkillUser) fieldCharacter;
				TurnActionPerformer performer = (TurnActionPerformer) fieldCharacter;

				SkillSelectionView skillSelectionView = new SkillSelectionView(skillUser);
				
				Skill selectedSkill = skillSelectionView.promptSkillSelection(); // TODO: Let user pick range...
				
				TurnAction skillTurnAction = new SkillTurnAction(performer, skillUser, selectedSkill);
				
				BattleManager.addTurnAction(skillTurnAction);
			}
		}
		
		for (FieldCharacter fieldCharacter : BattleTeamsManager.getEnemyCharacters())
		{
			if (fieldCharacter.getHealthPoints().getCurrentValue() > 0)
			{
				SkillUser skillUser = (SkillUser) fieldCharacter;
				TurnActionPerformer performer = (TurnActionPerformer) fieldCharacter;
				
				int randomSkillIndex = RandomGenerator.randomNumber(0, skillUser.getSkills().size() - 1);
				Skill selectedSkill = skillUser.getSkills().get(randomSkillIndex); // TODO: Pick random range...
				
				TurnAction skillTurnAction = new SkillTurnAction(performer, skillUser, selectedSkill);
				
				BattleManager.addTurnAction(skillTurnAction);
			}
		}
		
		readyForNextTurn(); // TODO: change to a notification ...
	}
	
	public void readyForNextTurn() // TODO: change to a notification ...
	{
		doNextTurn();
	}

	@Override
	public void onTurnEnd() 
	{
		System.out.println("====> BattleManager: onTurnEnd(): Turn has ended...."
				+ " and the battle is over? == " + isBattleOver()); // TODO: TESTING ONLY.
		
		this.nextTurnActions.clear();
		
		// Check if turn is over:
		if (isBattleOver())
		{
			endBattle();
		}
		else
		{
			getNextTurnActions();
		}
	}

	@Override
	public void onTurnBegun() 
	{
		// TODO Auto-generated method stub
		System.out.println("====> BattleManager: onTurnBegun(): Turn has begun...."); // TODO: TESTING ONLY.
	}

	@Override
	public void onTurnActionComplete() 
	{
		MapGridView.showMap();
		
		TeamListView.showTeams();
		
		PressEnterToContinueView.waitForContinue();
	}
	
	// Events:
	
	// OnTurnActionsDecided
	
	
	
}
