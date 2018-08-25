package com.thd.ss.combat.battle;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.field.FieldMapManager;

public class TurnManager 
{
	// Private members:
	
	private int currentTurnNumber = 0;
	private Turn currentTurn;
	
	private List<TurnBeginListener> turnBeginListeners;
	private List<TurnEndListener> turnEndListeners;
	
	// Constructor:
	public TurnManager()
	{
		this.currentTurnNumber = 0;
		
		this.turnBeginListeners = new ArrayList<TurnBeginListener>();
		this.turnEndListeners = new ArrayList<TurnEndListener>();
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
			
			// TODO: TESTING ONLY:
			FieldMapManager.drawMap();
			// TODO: create a notification for OnTurnActionComplete(TurnAction)....
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
	
	
}
