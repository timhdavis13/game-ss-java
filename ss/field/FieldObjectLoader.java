package com.thd.ss.field;

import com.thd.ss.combat.BreakableFieldObstacle;
import com.thd.ss.ui.DamageMessageView;

public class FieldObjectLoader 
{
	public static FieldObstacle loadObjectFromName(String objectName)
	{
		if(objectName.equals("tree"))
		{
			FieldObstacle tree = new FieldObstacle("tree");
			
			return tree;
		}
		else if(objectName.equals("bush"))
		{
			BreakableFieldObstacle bush = new BreakableFieldObstacle("bush", 10);
			
			DamageMessageView theBushsDamageView = new DamageMessageView("The " + bush.getName());
			bush.registerDamageListener(theBushsDamageView);
			
			return bush;
		}
		else
		{
			// TODO: throw error
			throw new IllegalArgumentException("FieldObjectLoader: objectName=[" + 
					objectName + "] is not a valid objectName.");
		}
	}
}
