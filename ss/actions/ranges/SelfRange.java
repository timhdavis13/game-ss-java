package com.thd.ss.actions.ranges;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.actions.TargetSelection;
import com.thd.ss.field.RelativeLocation;

public class SelfRange extends SingleSelectionRange
{
	// Private members:
	
	// Constructor:
	public SelfRange() 
	{	
		this.selectionGroups = new ArrayList<List<TargetSelection>>();
		
		setupRange();
	}
	
	// Getters:
	
	// Implementations:
	
	protected void setupRange()
	{
		selectionGroups.add(new ArrayList<TargetSelection>());
		
		selectionGroups.get(0).add(RelativeLocation.SOURCE);
	}
	
	


}
