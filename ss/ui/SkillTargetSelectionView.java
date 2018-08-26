package com.thd.ss.ui;

import java.util.List;

import com.thd.ss.actions.TargetSelection;
import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillUser;

public class SkillTargetSelectionView 
{
	public void showTargets(SkillUser skillUser, Skill selectedSkill)
	{	
		for(int actionIndex = 0; actionIndex < selectedSkill.getActions().size(); actionIndex++)
		{
			List<List<TargetSelection>> targetGroups = selectedSkill.getPotentialTargetGroups(actionIndex);
			
			if (targetGroups.size() > 1)
			{
				this.printMessage(selectedSkill.getName() + " Skill target selection options:");
				this.printMessage("For action: " + selectedSkill.getActions().get(actionIndex));
				
				this.printMessage("Choose target index (0-" + targetGroups.size() + "):");
				
				TextInputView inputView = new TextInputView();
				
				int groupIndexInput = inputView.getIntegerInput();
				
				this.printMessage("");
				
				selectedSkill.setTargetGroupSelected(actionIndex, groupIndexInput);
				
				this.printMessage("Selected target group index " + groupIndexInput);
				this.printMessage("");
			}
		}
	}
	
	public void printMessage(String message)
	{
		System.out.println(message);
	}
}
