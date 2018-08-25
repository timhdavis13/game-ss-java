package com.thd.ss.combat.skills;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.ActionRange;
import com.thd.ss.actions.ranges.DirectionalRangeSelectAll;
import com.thd.ss.actions.ranges.DirectionalRangeSelectGroup;
import com.thd.ss.actions.ranges.SelfRange;
import com.thd.ss.combat.DamageAction;
import com.thd.ss.combat.Element;
import com.thd.ss.combat.ElementType;
import com.thd.ss.combat.Elemental;
import com.thd.ss.combat.ElementalDamageAction;
import com.thd.ss.combat.HealAction;

public class SkillFactory 
{
	// Static methods:
	
	public static Skill getSkillByName(String skillName)
	{
		if (skillName.equals("Burst"))
		{
			Skill skill = new Skill();
			
			// Set name:
			skill.setName("Burst");
			
			// Setup actions:
			int rangeDistance = 1;
			ActionRange range = new DirectionalRangeSelectAll(rangeDistance);
			int damage = 5;
			Action damageAction = new DamageAction(range, damage);
			
			// Add actions to skill:
			skill.addAction(damageAction);
			
			// Setup skill cost:
			int brawlPointsCost = 1;
			int focusPointsCost = 2;
			skill.setCost(new SkillCost(brawlPointsCost, focusPointsCost));
			
			// Set skill speed:
			skill.setSpeed(100);
			
			// Return skill created:
			return skill;
		}
		else if (skillName.equals("Stab"))
		{
			Skill skill = new Skill();
			
			// Set name:
			skill.setName("Stab");
			
			// Setup actions:
			int rangeDistance = 1;
			ActionRange range = new DirectionalRangeSelectGroup(rangeDistance);
			int damage = 10;
			Elemental skillElement = new Element(ElementType.METAL);
			Action damageAction = new ElementalDamageAction(range, damage, skillElement);
			
			// Add actions to skill:
			skill.addAction(damageAction);
			
			// Setup skill cost:
			int brawlPointsCost = 4;
			int focusPointsCost = 0;
			skill.setCost(new SkillCost(brawlPointsCost, focusPointsCost));
			
			// Set skill speed:
			skill.setSpeed(60);
			
			// Return skill created:
			return skill;
		}
		else if (skillName.equals("Fire Breath"))
		{
			Skill skill = new Skill();
			
			// Set name:
			skill.setName("Fire Breath");
			
			// Setup actions:
			int rangeDistance = 3;
			ActionRange range = new DirectionalRangeSelectGroup(rangeDistance);
			int damage = 10;
			Elemental skillElement = new Element(ElementType.FIRE);
			Action damageAction = new ElementalDamageAction(range, damage, skillElement);
			
			// Add actions to skill:
			skill.addAction(damageAction);
			
			// Setup skill cost:
			int brawlPointsCost = 0;
			int focusPointsCost = 10;
			skill.setCost(new SkillCost(brawlPointsCost, focusPointsCost));
			
			// Set skill speed:
			skill.setSpeed(40);
			
			// Return skill created:
			return skill;
		}
		else if (skillName.equals("Heal Self"))
		{
			Skill skill = new Skill();
			
			// Set name:
			skill.setName("Heal Self");
			
			// Setup actions:
			ActionRange range = new SelfRange();
			int amount = 10;
			Action healAction = new HealAction(range, amount);
			
			// Add actions to skill:
			skill.addAction(healAction);
			
			// Setup skill cost:
			int brawlPointsCost = 0;
			int focusPointsCost = 5;
			skill.setCost(new SkillCost(brawlPointsCost, focusPointsCost));
			
			// Set skill speed:
			skill.setSpeed(120);
			
			// Return skill created:
			return skill;
		}
		else if (skillName.equals("Healing Burst"))
		{
			Skill skill = new Skill();
			
			// Set name:
			skill.setName("Healing Burst");
			
			//// Damage Action:
			
			// Setup actions:
			int rangeDistance = 3;
			ActionRange damageRange = new DirectionalRangeSelectAll(rangeDistance);
			int damage = 10;
			Elemental skillElement = new Element(ElementType.PLANT);
			Action damageAction = new ElementalDamageAction(damageRange, damage, skillElement);
			
			// Add actions to skill:
			skill.addAction(damageAction);
			
			//// Healing Action:
			
			// Setup actions:
			ActionRange healingRange = new SelfRange();
			int amount = 10;
			Action healAction = new HealAction(healingRange, amount);
			
			// Add actions to skill:
			skill.addAction(healAction);
			
			// Setup skill cost:
			int brawlPointsCost = 5;
			int focusPointsCost = 5;
			skill.setCost(new SkillCost(brawlPointsCost, focusPointsCost));
			
			// Set skill speed:
			skill.setSpeed(55);
			
			// Return skill created:
			return skill;
		}
		else
		{
			throw new IllegalArgumentException("SkillFactory: Skill not found with name = " + skillName);
		}
	}
	
}
