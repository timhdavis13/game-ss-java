package com.thd.ss.combat;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.TargetSelection;
import com.thd.ss.actions.Targetable;
import com.thd.ss.field.FieldObstacle;

public class BreakableFieldObstacle extends FieldObstacle implements TargetSelection, Targetable, Damageable, Destroyable
{
	// Additional private members:
	protected ValuePoints hitPoints;
	
	private DamageableComponent damageableComponent;
	
	// Constructor:
	public BreakableFieldObstacle(String name, int maxDamage)
	{
		super(name);
		
		this.hitPoints = new ValuePoints(maxDamage);
		
		this.damageableComponent = new DamageableComponent(this.hitPoints, this);
	}
	
	// Getters:
	
	/**
	 * @return the hitPoints
	 */
	public ValuePoints getHitPoints() { return hitPoints; }
	
	/**
	 * @return the current value of hitPoints
	 */
	public int getCurrentHitPoints() { return hitPoints.getCurrentValue(); }
	
	/**
	 * @return the maximum value of hitPoints
	 */
	public int getCurrentMaximumHitPoints() { return hitPoints.getMaximumValue(); }
	
	
	
	// Implementations:

	//// TargetSelection:
	
	@Override 
	public List<Targetable> getTargets(Action action) 
	{
		List<Targetable> targets = new ArrayList<Targetable>();
		targets.add(this);
		
		return targets;
	}
	
	//// Damageable:
	
	@Override
	public void damage(int amount) 
	{
		this.damageableComponent.damage(amount);
	}
	
	@Override
	public void destroy() 
	{
		// TODO:
		System.out.println("The " + getName() + " is destroyed!"); // TODO: DEBUG ONLY.
		
		// Remove self from current location:
		if (getCurrentLocation() != null)
		{
			getCurrentLocation().removeOccupant();
		}
	}

	@Override
	public void registerDamageListener(DamageListener listener) 
	{
		damageableComponent.registerDamageListener(listener);
	}

	@Override
	public void unregisterDamageListener(DamageListener listener) 
	{
		damageableComponent.unregisterDamageListener(listener);
	}

	@Override
	public void notifyDamageListeners(DamageEvent event) 
	{
		damageableComponent.notifyDamageListeners(event);
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "[Breakable: " + this.name
				+ ", hit points:" + this.hitPoints
				+"]";
	}

	

	

	
}
