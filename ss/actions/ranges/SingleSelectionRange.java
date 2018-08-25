package com.thd.ss.actions.ranges;
import java.util.List;

import com.thd.ss.actions.ActionRange;
import com.thd.ss.actions.TargetSelection;

import java.util.ArrayList;

public abstract class SingleSelectionRange implements ActionRange
{
	// Protected members:
	protected List<List<TargetSelection>> selectionGroups;
	
	
	// Constructor:
	public SingleSelectionRange() 
	{
		this.selectionGroups = new ArrayList<List<TargetSelection>>();
		
		setupRange();
	}
	
	// Abstract methods:
	
	protected abstract void setupRange();
	
	
	// Implementations:

	@Override
	public List<List<TargetSelection>> getTargetGroups() 
	{
		return this.selectionGroups;
	}

	@Override
	public void setSelectedGroup(int groupIndex) 
	{
		// No selection options.
	}

	@Override
	public List<TargetSelection> getSelectedGroup() 
	{
		return this.selectionGroups.get(0); // only one group to select.
	}

	@Override
	public int getSelectedGroupIndex() 
	{
		return 0; // only one group.
	}

}
