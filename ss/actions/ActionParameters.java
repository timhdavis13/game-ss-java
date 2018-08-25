package com.thd.ss.actions;
import java.util.Collection;
import java.util.Iterator;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */
public interface ActionParameters 
{
	ActionUser getUser();
	void setUser(ActionUser user);
	
	Collection<TargetSelection> getTargetSelections();
	Iterator<TargetSelection> getTargetSelectionIterator();
	int getTargetCount();
	void addTarget(TargetSelection targetSelection);
	
	ActionRange getActionRange();
	void setActionRange(ActionRange range);
	
	void setupTargets();
}
