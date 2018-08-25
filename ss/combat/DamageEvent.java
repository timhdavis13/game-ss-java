package com.thd.ss.combat;


public class DamageEvent 
{
	// Private members:
	private int plannedDamage;
	private int actualDamageReceived;
	
	// Constructor:
	public DamageEvent(int plannedDamage, int actualDamageReceived)
	{
		this.plannedDamage = plannedDamage;
		this.actualDamageReceived = actualDamageReceived;
	}
	
	// Methods:
	
	public int getPlannedDamage()
	{
		return this.plannedDamage;
	}
	
	public int getActualDamage()
	{
		return this.actualDamageReceived;
	}
}
