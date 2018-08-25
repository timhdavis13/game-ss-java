package com.thd.ss.actions.ranges;
import java.util.List;

import com.thd.ss.actions.ActionRange;
import com.thd.ss.actions.TargetSelection;

import java.util.ArrayList;

public abstract class BasicActionRange implements ActionRange
{
	// Protected members:
	protected int selectedGroupIndex = 0;
	protected List<List<TargetSelection>> selectionGroups;
	
	
	// Constructor:
	public BasicActionRange() 
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
		this.selectedGroupIndex = groupIndex;
	}

	@Override
	public List<TargetSelection> getSelectedGroup() 
	{
		return this.selectionGroups.get(this.selectedGroupIndex); // only one group to select.
	}

	@Override
	public int getSelectedGroupIndex() 
	{
		return this.selectedGroupIndex;
	}

}
