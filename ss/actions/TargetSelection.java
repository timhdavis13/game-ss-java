package com.thd.ss.actions;

import java.util.List;

/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */

public interface TargetSelection
{
	List<Targetable> getTargets(Action action);
}
