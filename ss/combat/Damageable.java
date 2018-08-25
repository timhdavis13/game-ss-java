package com.thd.ss.combat;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public interface Damageable 
{
	void damage(int amount);
	
	void registerDamageListener(DamageListener listener);
	void unregisterDamageListener(DamageListener listener);
	void notifyDamageListeners(DamageEvent event);
}
