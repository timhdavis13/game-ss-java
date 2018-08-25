package com.thd.ss.actions;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public interface Action
{
	void activate();
	
	ActionParameters getParameters();
}
