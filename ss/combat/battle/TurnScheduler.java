package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnScheduler 
{
	// Private members:
	private List<TurnAction> pendingTurnActions;
	private List<TurnAction> completedTurnActions;
	private boolean isSorted = false;
	
	// Constructor:
	
	public TurnScheduler()
	{
		this.pendingTurnActions = new ArrayList<TurnAction>(); // TODO: change to a PriorityQueue...
		this.completedTurnActions = new ArrayList<TurnAction>();
		
		this.isSorted = true;
	}
	
	// Other methods:
	
	public void addTurnAction(TurnAction turnAction)
	{
		this.pendingTurnActions.add(turnAction);
		
		this.isSorted = false;
	}
	
	public void sortTurnActions()
	{
		Collections.sort(this.pendingTurnActions, new TurnActionByPriorityComparator());
	}
	
	
	public boolean hasNextTurnAction()
	{
		return (this.pendingTurnActions != null && !(this.pendingTurnActions.isEmpty()));
	}
	
	public TurnAction getNextTurnAction()
	{
		// Return null if no pending turn actions yet:
		if (!hasNextTurnAction())
		{
			return null;
		}
		
		// Make sure list of pending turn actions is sorted:
		if (!this.isSorted)
		{
			this.sortTurnActions();
		}
		
		// Get the next turn action:
		TurnAction nextTurnAction = pendingTurnActions.get(0);
		
		// Move the turn action from pending to completed:
		
		completedTurnActions.add(nextTurnAction);
		pendingTurnActions.remove(nextTurnAction);
		
		// Return the turn action:
		return nextTurnAction;
	}
	
	public int getNumberTurnActionsPending()
	{
		return ((this.pendingTurnActions == null) ? 0 : this.pendingTurnActions.size());
	}
	
	public int getNumberTurnActionsComplete()
	{
		return ((this.completedTurnActions == null) ? 0 : this.completedTurnActions.size());
	}
}
