package com.thd.ss.ui;

import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillUser;

public class SkillSelectionView 
{
	// Private members:
	private SkillUser skillUser;
	
	// Constructor:
	public SkillSelectionView(SkillUser skillUser)
	{
		this.skillUser = skillUser;
	}
	
	// Other methods:
	
	public Skill promptSkillSelection()
	{
		SkillListMessageView skillListView = new SkillListMessageView(this.skillUser);
		
		skillListView.showSkills();
		
		TextInputView inputView = new TextInputView();
		
		int skillIndexInput = inputView.getIntegerInput();
		
		System.out.println();
		
		// Set chosen skill:
		
		Skill selectedSkill = this.skillUser.getSkills().get(skillIndexInput);
		
		System.out.println("Selected skill: " + selectedSkill);
		System.out.println();
		
		// then ask for range selection for skill ...
		
		// TODO ... just for now:
//		for (int i = 0; i < selectedSkill.getActions().size(); i++)
//		{
//			selectedSkill.setTargetGroupSelected(i, 0); // TODO: Let user select this ...
//		}
		
		
		// then set chosen range selection ...
		
		// then set this skill with this range selection as the Turn Action ...
		
		SkillTargetSelectionView targetSelectionView = new SkillTargetSelectionView();
		targetSelectionView.showTargets(skillUser, selectedSkill); // Lets user pick target.
		
		return selectedSkill;
	}
	
	
	
}
