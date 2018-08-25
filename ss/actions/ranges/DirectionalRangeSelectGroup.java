package com.thd.ss.actions.ranges;

import java.util.List;
import java.util.ArrayList;

import com.thd.ss.actions.TargetSelection;
import com.thd.ss.field.RelativeLocation;



public class DirectionalRangeSelectGroup extends BasicActionRange
{
	// Private members:
	private int distance;
	
	
	// Constructor:
	public DirectionalRangeSelectGroup(int distance)
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
		// Need four groups for each direction:
		selectionGroups.add(new ArrayList<TargetSelection>());
		selectionGroups.add(new ArrayList<TargetSelection>());
		selectionGroups.add(new ArrayList<TargetSelection>());
		selectionGroups.add(new ArrayList<TargetSelection>());
		
		/*
		 * Example:
		 * With distance = 2 and source location = S, target groups = A,B,C,D:
		 * 
		 *   A
		 *   A
		 * BBSDD
		 *   C
		 *   C
		 *   
		 */
		for (int distanceIndex = 1; distanceIndex <= this.distance; distanceIndex++)
		{
			selectionGroups.get(0).add(RelativeLocation.getUpLocation(distanceIndex));
			selectionGroups.get(1).add(RelativeLocation.getLeftLocation(distanceIndex));
			selectionGroups.get(2).add(RelativeLocation.getDownLocation(distanceIndex));
			selectionGroups.get(3).add(RelativeLocation.getRightLocation(distanceIndex));
		}
	}

}
