package com.thd.ss.ui;

import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillUser;

public class SkillListMessageView extends MessageView
{
	// Private members:
	private SkillUser skillUser;
	
	// Constructor:
	public SkillListMessageView(SkillUser skillUser)
	{
		this.skillUser = skillUser;
	}
	
	// Other methods:
	
	public void showSkills()
	{
		this.printMessage(this.skillUser.getName() + "'s Skills:");
		
		for(int index = 0; index < this.skillUser.getSkills().size(); index++)
		{
			Skill skill = this.skillUser.getSkills().get(index);
			
			this.printMessage("-" + index + "--" + skill.getName() + 
					"\t" + "-- Cost: " + skill.getCost().getBrawlPointsCost() + " BP / "
					+ skill.getCost().getFocusPointsCost() + " SP");
		}
	}
	
	
}
