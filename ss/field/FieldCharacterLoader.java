package com.thd.ss.field;

import com.thd.ss.combat.ElementType;
import com.thd.ss.combat.FieldCharacter;
import com.thd.ss.combat.battle.BattleTeamsManager;
import com.thd.ss.combat.skills.SkillFactory;
import com.thd.ss.ui.DamageMessageView;

public class FieldCharacterLoader 
{
	public static FieldCharacter loadCharacterFromName(String characterName)
	{
		if(characterName.equals("joe"))
		{
			FieldCharacter character = new FieldCharacter("Joe", 100, 20, 30);
			
			// Body Elements:
			character.addBodyElement(ElementType.PLANT);
			character.addBodyElement(ElementType.NON_ELEMENTAL);
			
			// Skills:
			character.addSkill(SkillFactory.getSkillByName("Burst"));
			character.addSkill(SkillFactory.getSkillByName("Stab"));
			character.addSkill(SkillFactory.getSkillByName("Fire Breath"));
			
			setDamageMessageView(character);
			
			BattleTeamsManager.addPlayerCharacter(character);
			
			return character;
		}
		else if(characterName.equals("bob"))
		{
			FieldCharacter character = new FieldCharacter("Bob", 100, 50, 50);
			
			// Body Elements:
			character.addBodyElement(ElementType.NON_ELEMENTAL);
			
			// Skills:
			character.addSkill(SkillFactory.getSkillByName("Burst"));
			character.addSkill(SkillFactory.getSkillByName("Heal Self"));
			character.addSkill(SkillFactory.getSkillByName("Fire Breath"));
			
			setDamageMessageView(character);
			
			BattleTeamsManager.addPlayerCharacter(character);
			
			return character;
		}
		else
		{
			// TODO: throw error
			throw new IllegalArgumentException("FieldCharacterLoader: characterName=[" + 
					characterName + "] is not a valid characterName.");
		}
	}
	
	// Private methods:
	
	private static void setDamageMessageView(FieldCharacter character)
	{
		DamageMessageView characterDamageView = new DamageMessageView(character.getName());
		character.registerDamageListener(characterDamageView);
	}
}
