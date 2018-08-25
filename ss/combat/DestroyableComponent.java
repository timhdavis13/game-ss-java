package com.thd.ss.combat;

import java.util.ArrayList;
import java.util.List;

public class DestroyableComponent implements Destroyable
{
	// Private methods:
	private Destroyable destroyable;
	private List<DestroyListener> destroyListeners;
	
	// Constructor:
	public DestroyableComponent(Destroyable destroyable)
	{
		this.destroyable = destroyable;
		
		this.destroyListeners = new ArrayList<DestroyListener>();
	}
	
	// Implementations:
	
	//// Destroyable implementations:

	@Override
	public void destroy() 
	{
		notifyDestroyListeners();
	}

	@Override
	public void registerDestroyListener(DestroyListener listener) 
	{
		this.destroyListeners.add(listener);
	}

	@Override
	public void unregisterDestroyListener(DestroyListener listener) 
	{
		this.destroyListeners.remove(listener);
	}

	@Override
	public void notifyDestroyListeners() 
	{
		for (DestroyListener listener : this.destroyListeners)
		{
			listener.onDestroyed(this.destroyable);
		}
	}
	
	
}
