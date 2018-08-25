package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.List;

public class TurnManager 
{
	// Private members:
	
	private int currentTurnNumber = 0;
	private Turn currentTurn;
	
	private List<TurnBeginListener> turnBeginListeners;
	private List<TurnEndListener> turnEndListeners;
	
	private List<TurnActionCompleteListener> turnActionCompleteListeners;
	
	// Constructor:
	public TurnManager()
	{
		this.currentTurnNumber = 0;
		
		this.turnBeginListeners = new ArrayList<TurnBeginListener>();
		this.turnEndListeners = new ArrayList<TurnEndListener>();
		
		this.turnActionCompleteListeners = new ArrayList<TurnActionCompleteListener>();
	}
	
	// Getters:
	
	public int getCurrentTurnNumber()
	{
		return this.currentTurnNumber;
	}
	
	// Other methods:
	
	public void doNextTurn(List<TurnAction> turnActions)
	{
		this.currentTurnNumber++;
		
		this.currentTurn = new Turn(currentTurnNumber);
		
		this.currentTurn.begin(turnActions);
		
		notifyTurnHasBegun();
		
		while (!this.currentTurn.isOver())
		{
			this.currentTurn.performNextTurnAction();
			
			this.notifyTurnActionCompleted();
		}
		
		this.currentTurn.end();
		
		notifyTurnHasEnded();
	}
	
	// Listener methods:
	
	//// Turn Begin Listeners:
	
	public void registerTurnBeginListener(TurnBeginListener listener)
	{
		this.turnBeginListeners.add(listener);
	}
	
	public void unregisterTurnBeginListener(TurnBeginListener listener)
	{
		this.turnBeginListeners.remove(listener);
	}
	
	public void notifyTurnHasBegun()
	{
		for (TurnBeginListener listener : this.turnBeginListeners)
		{
			listener.onTurnBegun();
		}
	}
	
	//// Turn End Listeners:
	
	public void registerTurnEndListener(TurnEndListener listener)
	{
		this.turnEndListeners.add(listener);
	}
	
	public void unregisterTurnEndListener(TurnEndListener listener)
	{
		this.turnEndListeners.remove(listener);
	}
	
	public void notifyTurnHasEnded()
	{
		for (TurnEndListener listener : this.turnEndListeners)
		{
			listener.onTurnEnd();
		}
	}
	
	//// TurnAction Complete Listeners:
	
	public void registerTurnActionCompleteListener(TurnActionCompleteListener listener)
	{
		this.turnActionCompleteListeners.add(listener);
	}
	
	public void unregisterTurnActionCompleteListener(TurnActionCompleteListener listener)
	{
		this.turnActionCompleteListeners.remove(listener);
	}
	
	public void notifyTurnActionCompleted()
	{
		for (TurnActionCompleteListener listener : this.turnActionCompleteListeners)
		{
			listener.onTurnActionComplete();
		}
	}
	
	
}
