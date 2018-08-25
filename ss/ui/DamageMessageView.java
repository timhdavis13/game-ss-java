package com.thd.ss.ui;

import com.thd.ss.combat.DamageEvent;
import com.thd.ss.combat.DamageListener;

public class DamageMessageView extends MessageView implements DamageListener
{
	// Private members:
	private String targetName;
	
	// Constructor:
	public DamageMessageView(String targetName)
	{
		this.targetName = targetName;
	}
	
	@Override
	public void onTargetDamaged(DamageEvent event) 
	{
		this.printMessage(this.targetName + " takes " + event.getActualDamage() + " damage!");
	}
	
}
