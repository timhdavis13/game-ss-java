package com.thd.ss.combat.battle;

import java.util.Comparator;

public class TurnActionByPriorityComparator implements Comparator<TurnAction>
{
	@Override
	public int compare(TurnAction turnAction1, TurnAction turnAction2) 
	{
		// Compare considers higher priority before lower priority:
		int higherOrder = -1; // negative comes before positive in compare.
		int lowerOrder = 1;
		
		if (turnAction1.getPriority() < turnAction2.getPriority()) return lowerOrder; 
        if (turnAction1.getPriority() > turnAction2.getPriority()) return higherOrder;
        else return 0;
	}
}
