package com.thd.ss.ui;

import java.util.List;

import com.thd.ss.combat.battle.TurnAction;

public class TurnActionSelectorView // TODO: TESTING ONLY....
{
	// Private members:
	private List<TurnAction> turnActions;
	
	// Constructor:
	public TurnActionSelectorView(List<TurnAction> turnActions)
	{
		this.turnActions = turnActions;
	}
	
	// Other methods:
	
	public void addTurnAction(TurnAction turnAction)
	{
		this.turnActions.add(turnAction);
	}
	
	// TODO: TESTING ONLY....
	public void getTurnActions(int turnNumber)
	{
		if (turnNumber == 1)
		{
			
		}
	}
	
	
}
