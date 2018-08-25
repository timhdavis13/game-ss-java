package com.thd.ss.actions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public class BasicActionParameters implements ActionParameters
{
	// Private members:
	private ActionUser user;
	private ActionRange range;
	private List<TargetSelection> targets;

	// Constructor:
	public BasicActionParameters()
	{	
		this.targets = new ArrayList<TargetSelection>();
	}
	
	// Implementations:
	
	@Override
	public ActionUser getUser() 
	{
		return this.user;
	}
	
	public void addTarget(TargetSelection target)
	{
		targets.add(target);
	}
	
	
	public void setUser(ActionUser user) 
	{
		this.user = user;
	}

	@Override
	public Collection<TargetSelection> getTargetSelections() 
	{
		return targets;
	}

	@Override
	public Iterator<TargetSelection> getTargetSelectionIterator() 
	{
		return targets.iterator();
	}

	@Override
	public int getTargetCount() 
	{
		return targets.size();
	}

	@Override
	public ActionRange getActionRange() 
	{
		return this.range;
	}

	@Override
	public void setActionRange(ActionRange range) 
	{
		this.range = range;
	}

	@Override
	public void setupTargets() 
	{
		this.targets.clear();
		
		for (TargetSelection targetSelection : this.range.getSelectedGroup())
		{
			this.addTarget(targetSelection);
		}
		
	}
	
}
