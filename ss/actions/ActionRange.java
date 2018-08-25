package com.thd.ss.actions;
import java.util.List;

/**
 * 
 * Goals: 
 * - sets up parameters.
 * - define potential targets.
 * - define selection options.
 * 
 * Created 06/14/2018
 * @author timhdavis.
 * 
 */
public interface ActionRange
{
	List<List<TargetSelection>> getTargetGroups();
	
	List<TargetSelection> getSelectedGroup();
	int getSelectedGroupIndex();
	void setSelectedGroup(int groupIndex);
	
	
}
