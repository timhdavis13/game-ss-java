package com.thd.ss.combat;

import com.thd.ss.actions.ActionParameters;
import com.thd.ss.actions.ActionRange;
import com.thd.ss.actions.BasicAction;
import com.thd.ss.actions.Targetable;

/**
 * Created 06/22/2018
 * 
 * @author timhdavis.
 * 
 */
public class HealAction extends BasicAction
{
	// Private members:
	private int amount;
	
	// Constructor:
	public HealAction(ActionRange range, int amount)
	{
		super(range);
		
		this.setAmount(amount);
	}
	
	// Getters and setters:
	
	/**
	 * @return the amount
	 */
	public int getAmount() { return amount; }

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	// Main method:
	
	@Override
	public void activatePerTargetAction(Targetable target, ActionParameters parameters) 
	{
		if (target instanceof Healable)
		{
			System.out.println(parameters.getUser().getName() + " will heal " + this.amount + " health to " + target.getName() + "!"); // TODO: DEBUG ONLY.
			
			((Healable) target).heal(this.amount);
		}
	}

	
	
}
