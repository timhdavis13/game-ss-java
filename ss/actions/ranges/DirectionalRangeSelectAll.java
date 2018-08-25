package com.thd.ss.actions.ranges;

import java.util.List;

import com.thd.ss.actions.TargetSelection;
import com.thd.ss.field.RelativeLocation;

import java.util.ArrayList;

public class DirectionalRangeSelectAll extends SingleSelectionRange
{
	// Private members:
	private int distance;
	
	
	// Constructor:
	public DirectionalRangeSelectAll(int distance)
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
		 *   X
		 *   X
		 * XXSXX
		 *   X
		 *   X
		 *   
		 */
		for (int distanceIndex = 1; distanceIndex <= this.distance; distanceIndex++)
		{
			selectionGroups.get(0).add(RelativeLocation.getUpLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getDownLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getLeftLocation(distanceIndex));
			selectionGroups.get(0).add(RelativeLocation.getRightLocation(distanceIndex));
		}
	}

}
