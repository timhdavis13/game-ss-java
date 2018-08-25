package com.thd.ss.combat;

public interface Destroyable 
{
	void destroy();
	
	void registerDestroyListener(DestroyListener listener);
	void unregisterDestroyListener(DestroyListener listener);
	void notifyDestroyListeners();
}
