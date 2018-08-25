package com.thd.ss.combat.skills;

public class SkillCost 
{
	// Private members:
	private int brawlPointsCost = 0;
	private int focusPointsCost = 0;
	
	
	// Constructor:
	public SkillCost(int brawlPointsCost, int focusPointsCost)
	{
		this.brawlPointsCost = brawlPointsCost;
		this.focusPointsCost = focusPointsCost;
	}

	// Getters and setters:

	/**
	 * @return the number of brawlPoints it costs
	 *  the user of this skill to use this skill.
	 */
	public int getBrawlPointsCost() { return brawlPointsCost; }


	/**
	 * @param brawlPointsCost the brawlPointsCost to set
	 */
	public void setBrawlPointsCost(int brawlPointsCost) 
	{
		this.brawlPointsCost = brawlPointsCost;
	}


	/**
	 * @return the number of focusPoints it costs
	 *  the user of this skill to use this skill.
	 */
	public int getFocusPointsCost() { return focusPointsCost; }


	/**
	 * @param focusPointsCost the focusPointsCost to set
	 */
	public void setFocusPointsCost(int focusPointsCost) 
	{
		this.focusPointsCost = focusPointsCost;
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "Cost:[BP=" + this.brawlPointsCost + ", SP=" + this.focusPointsCost + "]";
	}

}
