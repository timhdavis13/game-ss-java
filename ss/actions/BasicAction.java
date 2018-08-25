package com.thd.ss.actions;
import java.util.Iterator;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public abstract class BasicAction implements Action
{
	// Private members:
	ActionParameters parameters;
	
	// Constructor:
	public BasicAction(ActionRange range)
	{
		this.parameters = new BasicActionParameters();
		
		this.parameters.setActionRange(range);
	}
	
	// Implementations:
	
	@Override
	public ActionParameters getParameters() 
	{
		return this.parameters;
	}
	
	@Override
	public void activate() 
	{
		Iterator<TargetSelection> targetIterator = parameters.getTargetSelectionIterator();
		
		while (targetIterator.hasNext())
		{
			// Activate this action on every target:
			for (Targetable target : targetIterator.next().getTargets(this))
			{
				this.activatePerTargetAction(target, parameters);
			}
		}
	}
	
	public abstract void activatePerTargetAction(Targetable target, ActionParameters parameters);
	
}
