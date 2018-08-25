package com.thd.ss.combat;

public class ElementDamageableComponent extends DamageableComponent implements ElementDamageable
{
	// Private members:
	private ElementalBodied target;
	
	// Constructor:
	public ElementDamageableComponent(ValuePoints healthPoints, Destroyable destroyable, ElementalBodied target) 
	{
		super(healthPoints, destroyable);
		
		this.target = target;
	}
	
	////ElementDamageable:
	
	@Override
	public void damage(int amount, Elemental element) 
	{
		double damageMultiplier = 1.0;
		
		damageMultiplier = ElementDamageCalculator.getElementMultiplier(element.getElementType(), this.target.getBodyElements());
		
		int actualDamageAmount = (int)(amount * damageMultiplier);
		
		this.dealActualDamage(amount, actualDamageAmount);
	}

}
