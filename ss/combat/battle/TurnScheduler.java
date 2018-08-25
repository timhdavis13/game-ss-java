package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thd.ss.combat.DestroyListener;
import com.thd.ss.combat.Destroyable;

public class TurnScheduler implements DestroyListener
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
		
		if (turnAction.getPerformer() instanceof Destroyable)
		{
			System.out.println("************DEBUG: Listening to destroyable: " + 
					turnAction.getPerformer().getName() + " ************");
			
			Destroyable destroyable = (Destroyable) turnAction.getPerformer();
			
			destroyable.registerDestroyListener(this);
		}
		
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
	
	
	public void removeAllActionsByPerformer(TurnActionPerformer performer)
	{
		List<TurnAction> performersPendingTurnActions = new ArrayList<TurnAction>();
		
		// Find all (pending) turn actions by this turn action performer:
		for (TurnAction turnAction : this.pendingTurnActions)
		{
			if (turnAction.getPerformer() == performer)
			{
				performersPendingTurnActions.add(turnAction);
			}
		}
		
		// Remove all the pending turn actions by this performer from the list of pending turn actions:
		for (TurnAction performersPendingTurnAction : performersPendingTurnActions)
		{
			System.out.println("************DEBUG: Removing action: " + performersPendingTurnAction + "************");
			this.pendingTurnActions.remove(performersPendingTurnAction);
		}
	}
	
	//// DestroyListener implementation:

	@Override
	public void onDestroyed(Destroyable destroyable) 
	{
		System.out.println("************DEBUG: Destoryable was DESTROYED************");
		if (destroyable instanceof TurnActionPerformer)
		{
			TurnActionPerformer performer = (TurnActionPerformer) destroyable;
			
			removeAllActionsByPerformer(performer);
		}
	}
}
