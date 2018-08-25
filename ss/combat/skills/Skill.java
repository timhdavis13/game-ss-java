package com.thd.ss.combat.skills;
import java.util.ArrayList;
import java.util.List;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.ActionUser;
import com.thd.ss.actions.TargetSelection;

public class Skill
{
	// Private members:
	private List<Action> actions;
	private String name;
	private SkillUser user;
	private SkillCost cost;
	private int speed = 0;
	
	// Constructor:
	public Skill()
	{
		this.actions = new ArrayList<Action>();
	}
	
	// Other methods:
	
	public void addAction(Action action)
	{
		this.actions.add(action);
	}
	
	// TODO: Use? Make useful?
	public List<List<TargetSelection>> getPotentialTargetGroups(int actionIndex)
	{
		return this.actions.get(actionIndex).getParameters().getActionRange().getTargetGroups();
	}
	
	// TODO: Use? Make useful?
	public void setTargetGroupSelected(int actionIndex, int groupIndex)
	{
		this.actions.get(actionIndex).getParameters().getActionRange().setSelectedGroup(groupIndex);
	}
	
	
	
	public void activate() // TODO: have 'skillUser' parameter....
	{
		if (getUser() == null)
		{
			throw new IllegalArgumentException("Skill user needs to be set before activiating a skill.");
		}
		
		if (!getUser().canPaySkillCost(this.cost))
		{
			throw new SkillUserCannotPaySkillCostException("Skill user cannot pay skill cost: " + this.cost);
		}
		
		// Make user pay skill cost:
		this.getUser().applySkillCost(this.cost);
		
		// Get Action user:
		ActionUser actionUser = null;
		if (this.user instanceof ActionUser)
		{
			actionUser = (ActionUser) this.user;
		}
		
		// Activate each action of the skill:
		for (Action action : this.actions)
		{
			action.getParameters().setUser(actionUser);
			
			// Setup targets based on the selected group indexes:
			action.getParameters().setupTargets();
			
			action.activate();
		}
	}

	// Getters and setters:
	
	public List<Action> getActions()
	{
		return this.actions;
	}
	
	/**
	 * @return the name
	 */
	public String getName() { return name; }

	/**
	 * @param name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public SkillUser getUser() { return user; }

	/**
	 * @param user the user to set
	 */
	public void setUser(SkillUser user) 
	{
		this.user = user;
	}

	/**
	 * @return the cost
	 */
	public SkillCost getCost() { return cost; }

	/**
	 * @param cost the cost to set
	 */
	public void setCost(SkillCost cost) 
	{
		this.cost = cost;
	}

	// General methods:
	
	public int getSpeed() { return speed; }

	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}

	@Override
	public String toString()
	{
		return this.name;
	}

}
