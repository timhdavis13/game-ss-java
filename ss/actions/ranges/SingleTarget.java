package com.thd.ss.actions.ranges;

import java.util.List;

import com.thd.ss.actions.TargetSelection;

import java.util.ArrayList;

public class SingleTarget extends SingleSelectionRange
{
	// Private members:
	private TargetSelection targetSelection;
	
	
	// Constructor:
	public SingleTarget(TargetSelection targetSelection) 
	{
		this.targetSelection = targetSelection;
		
		this.selectionGroups = new ArrayList<List<TargetSelection>>();
		
		setupRange();
	}
	
	// Implementations:
	
	protected void setupRange()
	{
		selectionGroups.add(new ArrayList<TargetSelection>());
		
		selectionGroups.get(0).add(this.targetSelection);
	}
	
}
