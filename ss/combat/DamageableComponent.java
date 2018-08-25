package com.thd.ss.combat;

import java.util.ArrayList;
import java.util.List;

public class DamageableComponent implements Damageable
{
	// Private methods:
	private ValuePoints healthPoints;
	private Destroyable destroyable;
	private List<DamageListener> damageListeners;
	
	// Constructor:
	public DamageableComponent(ValuePoints healthPoints, Destroyable destroyable)
	{
		this.healthPoints = healthPoints;
		
		this.destroyable = destroyable;
		
		this.damageListeners = new ArrayList<DamageListener>();
	}
	
	// Protected methods:
	
	protected void dealActualDamage(int plannedDamage, int actualDamage)
	{
		this.healthPoints.reduce(actualDamage);
		
		// Notify Damage Event listeners:
		DamageEvent event = new DamageEvent(plannedDamage, actualDamage);
		this.notifyDamageListeners(event);
		
		// Destroy if health is below zero:
		if (this.healthPoints.getCurrentValue() == 0)
		{
			this.destroyable.destroy();
		}
	}
	
	// Implementations:
	
	//// Damageable implementations:

	@Override
	public void damage(int amount) 
	{
		this.dealActualDamage(amount, amount);
	}
	
	@Override
	public void registerDamageListener(DamageListener listener) 
	{
		this.damageListeners.add(listener);
	}

	@Override
	public void unregisterDamageListener(DamageListener listener) 
	{
		this.damageListeners.remove(listener);
	}

	@Override
	public void notifyDamageListeners(DamageEvent event) 
	{
		for (DamageListener listener : this.damageListeners)
		{
			listener.onTargetDamaged(event);
		}
	}
	
	
}
