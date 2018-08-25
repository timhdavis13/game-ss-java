package com.thd.ss.combat.battle;

public interface TurnAction 
{
	int getPriority();
	
	void performAction();
}
