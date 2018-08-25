package com.thd.ss.combat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.ActionUser;
import com.thd.ss.actions.TargetSelection;
import com.thd.ss.actions.Targetable;
import com.thd.ss.combat.skills.Skill;
import com.thd.ss.combat.skills.SkillCost;
import com.thd.ss.combat.skills.SkillUser;
import com.thd.ss.field.FieldLocation;
import com.thd.ss.field.FieldOccupant;

/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public class FieldCharacter implements FieldOccupant, 
	SkillUser, ActionUser, 
	TargetSelection, Targetable,
	ElementalBodied,
	Damageable, ElementDamageable,
	Healable,
	Destroyable
{
	// Private members:
	
	private String name;
	
	private ValuePoints healthPoints;
	private ValuePoints brawlPoints;
	private ValuePoints focusPoints;
	
	private int baseSpeed = 0;
	
	private FieldLocation currentLocation;
	
	private List<Skill> skills;
	
	private Set<ElementType> bodyElements;
	private ElementType primaryBodyElement;
	
	private ElementDamageableComponent damageableComponent;
	
	// Constructor:
	public FieldCharacter(String name, int maximumHealthPoints, int maximumBrawlPoints, int maximumFocusPoints)
	{
		this.name = name;
		
		this.healthPoints = new ValuePoints(maximumHealthPoints);
		this.brawlPoints = new ValuePoints(maximumBrawlPoints);
		this.focusPoints = new ValuePoints(maximumFocusPoints);
		
		this.skills = new ArrayList<Skill>();
		this.bodyElements = new HashSet<ElementType>();
		
		this.damageableComponent = new ElementDamageableComponent(healthPoints, this, this);
	}
	
	// Getters and setters:

	/**
	 * @return the healthPoints
	 */
	public ValuePoints getHealthPoints() { return healthPoints; }

	/**
	 * @return the brawlPoints
	 */
	public ValuePoints getBrawlPoints() { return brawlPoints; }

	/**
	 * @return the focusPoints
	 */
	public ValuePoints getFocusPoints() { return focusPoints; }
	
	/**
	 * @return the name
	 */
	public String getName() { return this.name; }
	
	/**
	 * @return the currentLocation
	 */
	public FieldLocation getCurrentLocation() { return currentLocation; }

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(FieldLocation currentLocation) 
	{
		this.currentLocation = currentLocation;
	}
	
	
	/**
	 * @return the baseSpeed
	 */
	public int getBaseSpeed() { return baseSpeed; }

	/**
	 * @param baseSpeed the baseSpeed to set
	 */
	public void setBaseSpeed(int baseSpeed) 
	{
		this.baseSpeed = baseSpeed;
	}
	
	// Implementations:
		
	//// ActionUser implementations:
	
	//// TargetSelection:
	@Override
	public List<Targetable> getTargets(Action action) 
	{
		List<Targetable> targets = new ArrayList<Targetable>();
		targets.add(this);
		
		return targets;
	}
	
	@Override
	public void performAction(Action action) 
	{
		action.getParameters().setUser(this);
		
		// Setup targets based on the selected group indexes:
		action.getParameters().setupTargets();
		
		action.activate();
	}
	
	//// SkillUser implementations:

	@Override
	public void applySkillCost(SkillCost cost) 
	{
		this.brawlPoints.reduce(cost.getBrawlPointsCost());
		
		this.focusPoints.reduce(cost.getFocusPointsCost());
	}

	@Override
	public boolean canPaySkillCost(SkillCost cost) 
	{
		return (this.brawlPoints.getCurrentValue() >= cost.getBrawlPointsCost()
				&& this.focusPoints.getCurrentValue() >= cost.getFocusPointsCost());
	}
	
	/**
	 * @return the skills the user knows.
	 */
	public List<Skill> getSkills() 
	{
		return skills;
	}

	/**
	 * @param skill The skill to add to the user's list of skills.
	 */
	public void addSkill(Skill skill) 
	{
		// Set this user as the skill user:
		skill.setUser(this);
		
		this.skills.add(skill);
	}
	
	/**
	 * @param skillIndex The index of the skill to activate.
	 */
	public void useSkill(int skillIndex) 
	{
		Skill skillToUse = this.skills.get(skillIndex);
		
		System.out.println(this.name + " uses skill: " + skillToUse.getName() + "!"); // TODO: Remove. DEBUG ONLY.
		
		// TODO: check if user can pay skill cost first...
		
		skillToUse.activate();
	}
	
	/**
	 * @param skillIndex The index of the skill to activate.
	 */
	@Override
	public void useSkill(Skill skillToUse) 
	{
		System.out.println(this.name + " uses skill: " + skillToUse.getName() + "!"); // TODO: Remove. DEBUG ONLY.
		
		// TODO: check if user can pay skill cost first...
		
		skillToUse.setUser(this);
		
		skillToUse.activate();
	}
	
	@Override
	public int getSkillSpeed(Skill skill) 
	{
		// TODO: perform real calculation:
		int actionSpeed = skill.getSpeed() + this.getBaseSpeed();
		
		return actionSpeed;
	}
	
	//// Healable:
	
	@Override
	public void heal(int amount) 
	{
		this.healthPoints.restore(amount);
		
		System.out.println(this.name + " recovers " + amount + " health!"); // TODO: DEBUG ONLY.
	}
	
	//// Damageable implementations:

	@Override
	public void damage(int amount) 
	{
		this.damageableComponent.damage(amount);
	}
	
	@Override
	public void registerDamageListener(DamageListener listener) 
	{
		this.damageableComponent.registerDamageListener(listener);
	}

	@Override
	public void unregisterDamageListener(DamageListener listener) 
	{
		this.damageableComponent.unregisterDamageListener(listener);
	}

	@Override
	public void notifyDamageListeners(DamageEvent event) 
	{
		this.damageableComponent.notifyDamageListeners(event);
	}
	
	//// ElementDamageable:
	
	@Override
	public void damage(int amount, Elemental element) 
	{
		this.damageableComponent.damage(amount, element);
	}
	
	//// ElementBodied:

	@Override
	public ElementType getPrimaryBodyElement() 
	{
		return this.primaryBodyElement;
	}
	
	public void setPrimaryBodyElement(ElementType elementType) 
	{
		this.primaryBodyElement = elementType;
	}

	@Override
	public Set<ElementType> getBodyElements() 
	{
		return this.bodyElements;
	}
	
	public void addBodyElement(ElementType elementType)
	{
		// If this is the first-added element, make it the primary body element:
		if (this.bodyElements.isEmpty())
		{
			setPrimaryBodyElement(elementType);
		}
		
		this.bodyElements.add(elementType);
	}
	
	////Destroyable:

	@Override
	public void destroy() 
	{
		this.defeat();
	}
	
	
	// Character methods:
	
	public void defeat()
	{
		// TODO: Actually destroy this character.
		
		System.out.println(this.name + " is defeated!"); // TODO: Remove. DEBUG ONLY.
		
		// Remove self from current location:
		if (currentLocation != null)
		{
			currentLocation.removeOccupant();
		}
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "[Char: " + this.name 
				+ ", HP:" + healthPoints
				+ ", BP:" + brawlPoints
				+ ", SP:" + focusPoints
				+"]";
	}

	

	
	
	

	
	
}
