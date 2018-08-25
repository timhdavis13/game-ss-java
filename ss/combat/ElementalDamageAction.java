package com.thd.ss.combat;

import com.thd.ss.actions.ActionParameters;
import com.thd.ss.actions.ActionRange;
import com.thd.ss.actions.Targetable;

public class ElementalDamageAction extends DamageAction implements Elemental
{
	// Private members:
	private Elemental elemental;

	// Constructor:
	public ElementalDamageAction(ActionRange range, int amount, Elemental elemental) 
	{
		super(range, amount);
		
		this.elemental = elemental;
	}
	
	// Implementations:

	@Override
	public ElementType getElementType() 
	{
		return this.elemental.getElementType();
	}
	
	// Main method:
	
	@Override
	public void activatePerTargetAction(Targetable target, ActionParameters parameters) 
	{
		if (target instanceof ElementDamageable)
		{
			System.out.println(parameters.getUser().getName() + " deals " + this.getAmount()
					+ " " + this.getElementType().getName() + "-type damage to " + target.getName() + "!"); // TODO: DEBUG ONLY.
			
			((ElementDamageable) target).damage(this.getAmount(), this);
		}
		else if (target instanceof Damageable)
		{
			super.activatePerTargetAction(target, parameters);
		}
	}

}
