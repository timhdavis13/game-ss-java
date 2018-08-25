package com.thd.ss.combat;

import com.thd.ss.combat.battle.TurnAction;
import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillUser;

public class SkillTurnAction implements TurnAction
{
	// Private members:
	private Skill skill;
	private SkillUser user;
	
	// Constructor:
	public SkillTurnAction(SkillUser user, Skill skill)
	{
		this.setUser(user);
		this.setSkill(skill);
	}
	
	// Implementations:
	
	@Override
	public int getPriority() 
	{
		// TODO : Factor in player base speed...
		return user.getSkillSpeed(this.skill);
	}

	@Override
	public void performAction() 
	{
		// TODO: notify listeners (UI) that using skill...
		
		System.out.println("===> SkillTurnAction: performAction(): " + this); // TODO: TESTING ONLY.
		
		this.user.useSkill(this.skill);
	}
	
	// Getters and setters:

	public Skill getSkill() { return skill; }

	public void setSkill(Skill skill) 
	{
		this.skill = skill;
	}

	public SkillUser getUser() { return user; }

	public void setUser(SkillUser user) 
	{
		this.user = user;
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "[SkillTurnAction: priority=" + this.getPriority() + 
				"; skillUser=" + this.user.getName() + "; skill=" + this.skill.getName() + "]";
	}
	
}
