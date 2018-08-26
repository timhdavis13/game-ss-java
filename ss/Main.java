package com.thd.ss;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.ranges.SingleTarget;
import com.thd.ss.combat.BreakableFieldObstacle;
import com.thd.ss.combat.DamageAction;
import com.thd.ss.combat.ElementDamageCalculator;
import com.thd.ss.combat.ElementType;
import com.thd.ss.combat.FieldCharacter;
import com.thd.ss.combat.SkillTurnAction;
import com.thd.ss.combat.battle.BattleManager;
import com.thd.ss.combat.battle.BattleTeamsManager;
import com.thd.ss.combat.battle.GameManager;
import com.thd.ss.combat.battle.TurnAction;
import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillFactory;
import com.thd.ss.combat.skills.SkillUser;
import com.thd.ss.field.AlreadyOccupiedException;
import com.thd.ss.field.FieldLocation;
import com.thd.ss.field.FieldMap;
import com.thd.ss.field.FieldMapManager;
import com.thd.ss.field.FieldObstacle;
import com.thd.ss.ui.DamageMessageView;
import com.thd.ss.ui.SkillListMessageView;
import com.thd.ss.ui.SkillSelectionView;

/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//showSimpleBattle();
		
		//showSimpleFieldBattle();
		
		//showSimpleFieldBattleWithRelativeLocations();
		
		showGame();
	}

	
	private static void showSimpleBattle()
	{
		FieldCharacter joe = new FieldCharacter("Joe", 100, 20, 30);
		FieldCharacter bob = new FieldCharacter("Bob", 40, 10, 12);
		
		System.out.println(joe);
		System.out.println(bob);
		
		Action damageAction = new DamageAction(new SingleTarget(bob), 30);
		
		joe.performAction(damageAction);
		
		System.out.println(joe);
		System.out.println(bob);
		
		joe.performAction(damageAction);
		
		System.out.println(joe);
		System.out.println(bob);
	}
	
	// ==========================================================================================
	
	private static void showSimpleFieldBattle()
	{
		FieldMap fieldMap = new FieldMap(3, 3);
		
		FieldCharacter joe = new FieldCharacter("Joe", 100, 20, 30);
		FieldCharacter bob = new FieldCharacter("Bob", 40, 10, 12);
		
		FieldObstacle tree = new FieldObstacle("tree");
		
		FieldObstacle bush = new BreakableFieldObstacle("bush", 10);
		
		try 
		{
			fieldMap.addOccupant(joe, 0, 0);
			
			fieldMap.addOccupant(bob, 0, 1);
			
			fieldMap.addOccupant(tree, 1, 0);
			
			fieldMap.addOccupant(bush, 1, 1);
		} 
		catch (AlreadyOccupiedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(fieldMap);
		System.out.println();
		
		FieldLocation locationOfBob = fieldMap.getLocation(0, 1);
		Action damageAction1 = new DamageAction(new SingleTarget(locationOfBob), 5);
		
		joe.performAction(damageAction1);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		joe.performAction(damageAction1);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		// Attacking non-breakable field obstacle:
		
		FieldLocation locationOfTree = fieldMap.getLocation(1, 0);
		Action damageAction2 = new DamageAction(new SingleTarget(locationOfTree), 5);
		
		joe.performAction(damageAction2);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		
		// Attacking breakable field obstacle:
		
		FieldLocation locationOfBush = fieldMap.getLocation(1, 1);
		Action damageAction3 = new DamageAction(new SingleTarget(locationOfBush), 5);
		
		joe.performAction(damageAction3);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		joe.performAction(damageAction3);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
	}
	
	// ==========================================================================================
	
	private static void showSimpleFieldBattleWithRelativeLocations()
	{
		FieldMap fieldMap = new FieldMap(5, 5);
		
		FieldCharacter joe = new FieldCharacter("Joe", 100, 20, 30);
		FieldCharacter bob = new FieldCharacter("Bob", 100, 50, 50);
		
		FieldObstacle tree = new FieldObstacle("tree");
		
		BreakableFieldObstacle bush = new BreakableFieldObstacle("bush", 10);
		
		try 
		{
			fieldMap.addOccupant(joe, 1, 2);
			
			fieldMap.addOccupant(bob, 0, 2);
			
			fieldMap.addOccupant(tree, 1, 0);
			
			fieldMap.addOccupant(bush, 1, 1);
		} 
		catch (AlreadyOccupiedException e)
		{
			e.printStackTrace();
		}
		
		// UI Setup:
		
		DamageMessageView joesDamageView = new DamageMessageView(joe.getName());
		joe.registerDamageListener(joesDamageView);
		
		DamageMessageView bobsDamageView = new DamageMessageView(bob.getName());
		bob.registerDamageListener(bobsDamageView);
		
		DamageMessageView theBushsDamageView = new DamageMessageView("The " + bush.getName());
		bush.registerDamageListener(theBushsDamageView);
		
		
		// Start:
		
		System.out.println(fieldMap);
		System.out.println();
		
		// As a Skill:
		
		joe.addSkill(SkillFactory.getSkillByName("Burst"));
		
		joe.getSkills().get(0).setTargetGroupSelected(0, 0);
		joe.useSkill(0);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		
		// TODO: Next: Add selections to skills. Example: select 1 of 4 options:
		

		joe.addSkill(SkillFactory.getSkillByName("Stab"));
		
		int upIndex = 0;
		int leftIndex = 1;
		int downIndex = 2;
		int rightIndex = 3;
		
		joe.getSkills().get(1).setTargetGroupSelected(0, leftIndex);
		joe.useSkill(1);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		joe.addSkill(SkillFactory.getSkillByName("Fire Breath"));
		
		joe.getSkills().get(2).setTargetGroupSelected(0, leftIndex);
		joe.useSkill(2);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		
		// Calculate Elemental damage multiplier:
		
		ElementType attackElement = ElementType.LIGHTNING;
		ElementType defenderElement = ElementType.PLANT;
		double multiplier = ElementDamageCalculator.getElementMultiplier(attackElement, defenderElement);
		
		System.out.println("Multiplier for: Attacker=" + attackElement.getName() + 
				" vs Defender=" + defenderElement.getName() + "; multiplier = " + multiplier);
		
		// check with body elements:
		
		joe.addBodyElement(ElementType.PLANT);
		joe.addBodyElement(ElementType.NON_ELEMENTAL);
		
		double multiplierOnBody = ElementDamageCalculator.getElementMultiplier(attackElement, joe.getBodyElements());
		
		System.out.println("Multiplier for: Attacker=" + attackElement.getName() + 
				" vs Defender=" + joe.getName() + "; multiplier = " + multiplierOnBody);
		
		System.out.println();
		System.out.println();
		
		// Against an element-bodied character:
		
		bob.addSkill(SkillFactory.getSkillByName("Fire Breath"));
		
		bob.getSkills().get(0).setTargetGroupSelected(0, rightIndex);
		bob.useSkill(0);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		// Healing:
		
		bob.addBodyElement(ElementType.NON_ELEMENTAL);
		
		bob.addSkill(SkillFactory.getSkillByName("Heal Self"));
		
		bob.getSkills().get(1).setTargetGroupSelected(0, 0);
		bob.useSkill(1);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		// 2 Action (Healing+Damage) Skills:
		
		bob.addSkill(SkillFactory.getSkillByName("Healing Burst"));
		
		bob.getSkills().get(2).setTargetGroupSelected(0, 0);
		bob.getSkills().get(2).setTargetGroupSelected(1, 0);
		bob.useSkill(2);
		
		System.out.println();
		System.out.println(fieldMap);
		System.out.println();
		
		// Show Skill List:
		
		SkillListMessageView skillListView = new SkillListMessageView(bob);
		skillListView.showSkills();
		
		System.out.println();
		
		new SkillListMessageView(joe).showSkills();
	}
	
	
	// ==========================================================================================
	
	private static void showGame()
	{
		GameManager.loadMap("MAP1"); // Load TESTING map.
		
		FieldMapManager.drawMap(); // TESTING ...
		
		// ~~~~~~~~~~~ TESTING:
		
		System.out.println("------ BATTLE BEGINS ----");
		
		GameManager.startBattle();
		
		System.out.println("------ BATTLE ENDS ----");
		
		GameManager.cleanupBattle();
		
		FieldMapManager.drawMap(); // TESTING ...
		
	}
	
	
}


/*
 private static void showSimpleBattle()
{
	FieldCharacter joe = new FieldCharacter("Joe", 100, 20, 30);
	FieldCharacter bob = new FieldCharacter("Bob", 40, 10, 12);
	
	System.out.println(joe);
	System.out.println(bob);
	
	Action damageAction = new DamageAction(30);
	
	ActionParameters params = new BasicActionParameters(joe);
	params.addTarget(bob);
	
	joe.performAction(damageAction, params);
	
	System.out.println(joe);
	System.out.println(bob);
	
	joe.performAction(damageAction, params);
	
	System.out.println(joe);
	System.out.println(bob);
}

private static void showSimpleFieldBattle()
{
	FieldMap fieldMap = new FieldMap(3, 3);
	
	FieldCharacter joe = new FieldCharacter("Joe", 100, 20, 30);
	FieldCharacter bob = new FieldCharacter("Bob", 40, 10, 12);
	
	FieldObstacle tree = new FieldObstacle("tree");
	
	FieldObstacle bush = new BreakableFieldObstacle("bush", 10);
	
	try 
	{
		fieldMap.addOccupant(joe, 0, 0);
		
		fieldMap.addOccupant(bob, 0, 1);
		
		fieldMap.addOccupant(tree, 1, 0);
		
		fieldMap.addOccupant(bush, 1, 1);
	} 
	catch (AlreadyOccupiedException e)
	{
		e.printStackTrace();
	}
	
	System.out.println(fieldMap);
	System.out.println();
	
	Action damageAction = new DamageAction(5);
	
	ActionParameters params = new BasicActionParameters(joe);
	params.addTarget(fieldMap.getLocation(0, 1)); // location of bob.
	
	joe.performAction(damageAction, params);
	
	System.out.println();
	System.out.println(fieldMap);
	System.out.println();
	
	joe.performAction(damageAction, params);
	
	System.out.println();
	System.out.println(fieldMap);
	System.out.println();
	
	// Attacking non-breakable field obstacle:
	
	ActionParameters params1 = new BasicActionParameters(joe);
	params1.addTarget(fieldMap.getLocation(1, 0)); // location of tree.
	
	joe.performAction(damageAction, params1);
	
	System.out.println();
	System.out.println(fieldMap);
	System.out.println();
	
	
	// Attacking breakable field obstacle:
	
	ActionParameters params2 = new BasicActionParameters(joe);
	params2.addTarget(fieldMap.getLocation(1, 1)); // location of bush.
	
	joe.performAction(damageAction, params2);
	
	System.out.println();
	System.out.println(fieldMap);
	System.out.println();
	
	joe.performAction(damageAction, params2);
	
	System.out.println();
	System.out.println(fieldMap);
	System.out.println();
	
}
 */


