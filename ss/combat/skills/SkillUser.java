package com.thd.ss.combat.skills;

import java.util.List;

public interface SkillUser 
{
	void applySkillCost(SkillCost cost);
	
	boolean canPaySkillCost(SkillCost cost);
	
	List<Skill> getSkills();
	
	String getName();
	
	void useSkill(Skill skill);
	
	int getSkillSpeed(Skill skill);
}
