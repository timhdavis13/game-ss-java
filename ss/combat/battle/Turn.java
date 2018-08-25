package com.thd.ss.combat.battle;

import java.util.List;

public class Turn
{
	// Private members:
	private int turnNumber = 0;
	private TurnScheduler turnScheduler;
	
	// Constructor:
	public Turn(int turnNumber)
	{
		this.turnNumber = turnNumber;
	}
	
	// Other methods:
	
	public int getNumber()
	{
		return this.turnNumber;
	}
	
	// Performs setup for when this turn begins.
	public void begin(List<TurnAction> turnActions)
	{
		this.turnScheduler = new TurnScheduler();
		
		for (TurnAction turnAction : turnActions)
		{
			this.turnScheduler.addTurnAction(turnAction);
		}
		
		System.out.println("===> Turn: begin(...): turnScheduler.getNumberTurnActionsPending() == "
				+ this.turnScheduler.getNumberTurnActionsPending()); // TODO: TESTING ONLY.
		
	}
	
	
	public void end()
	{
		// TODO: any necessary cleanup.
		System.out.println("===> Turn: end(): Turn has ended."); // TODO: TESTING ONLY.
		
	}
	
	
	public boolean isOver()
	{
		if (this.turnScheduler == null)
		{
			throw new IllegalStateException("Turn: isOver(): must call begin method before checking if turn is over.");
		}
		
		// Turn is over (returns true) if there is not a next turn action:
		return (!(this.turnScheduler.hasNextTurnAction()));
	}
	
	
	public void performNextTurnAction()
	{
		if (this.isOver())
		{
			throw new IllegalStateException("Turn: performNextTurnAction(): " + 
					"cannot perform turn actions after turn has ended.");
		}
		
		if (this.turnScheduler == null)
		{
			throw new IllegalStateException("Turn: performNextTurnAction(): " + 
					"must call begin method before attempting to perform next turn action.");
		}
		
		if (!this.turnScheduler.hasNextTurnAction())
		{
			throw new IllegalStateException("Turn: performNextTurnAction(): " + 
					"cannot perform turn actions when there are no more pending turn actions.");
		}
		
		TurnAction turnAction = this.turnScheduler.getNextTurnAction();
		
		System.out.println("===> Turn: performNextTurnAction(): About to perform TurnAction: " + turnAction); // TODO: TESTING ONLY.
		
		turnAction.performAction();
	}
}
