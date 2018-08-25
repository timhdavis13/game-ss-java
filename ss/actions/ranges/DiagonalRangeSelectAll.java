package com.thd.ss.actions.ranges;

import java.util.List;

import com.thd.ss.actions.TargetSelection;
import com.thd.ss.field.RelativeLocation;

import java.util.ArrayList;

public class DiagonalRangeSelectAll extends SingleSelectionRange
{
	// Private members:
	private int distance;
	
	// Constructor:
	public DiagonalRangeSelectAll(int distance) 
	{
		if (distance < 0)
		{
			throw new IllegalArgumentException("Range distance must be a positive value.");
		}
		
		this.distance = distance;
		
		this.selectionGroups = new ArrayList<List<TargetSelection>>();
		
		setupRange();
	}
	
	// Getters:
	
	public int getDistance()
	{
		return this.distance;
	}
	
	// Implementations:
	
	protected void setupRange()
	{
		selectionGroups.add(new ArrayList<TargetSelection>());
		
		/*
		 * Example:
		 * With distance = 2 and source location = S, targets = X:
		 * 
		 * X   X
		 *  X X
		 *   S  
		 *  X X
		 * X   X
		 *   
		 */
		for (int distanceIndex = 1; distanceIndex <= this.distance; distanceIndex++)
		{
			selectionGroups.get(0).add(RelativeLocation.getUpperLeftLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getLowerLeftLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getUpperRightLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getLowerRightLocation(distanceIndex));
		}
	}
	
	

}
