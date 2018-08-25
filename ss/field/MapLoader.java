package com.thd.ss.field;

import com.thd.ss.combat.FieldCharacter;

public class MapLoader 
{
	public static void loadMapFromMapID(String mapID)
	{
		if (mapID.equals("MAP1"))
		{
			FieldMapManager.createMap(5, 5);
			
			FieldMap fieldMap = FieldMapManager.getFieldMap();
			
			FieldCharacter joe = FieldCharacterLoader.loadCharacterFromName("joe");
			FieldCharacter bob = FieldCharacterLoader.loadCharacterFromName("bob");
			FieldCharacter foe = FieldCharacterLoader.loadCharacterFromName("foe");
			
			FieldObstacle tree = FieldObjectLoader.loadObjectFromName("tree");
			
			FieldObstacle bush = FieldObjectLoader.loadObjectFromName("bush");
			
			try
			{
				fieldMap.addOccupant(joe, 1, 2);
				
				fieldMap.addOccupant(bob, 0, 2);
				
				fieldMap.addOccupant(tree, 1, 0);
				
				fieldMap.addOccupant(bush, 1, 1);
				
				fieldMap.addOccupant(foe, 2, 2);
			} 
			catch (AlreadyOccupiedException e)
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			// TODO: throw error
			throw new IllegalArgumentException("MapLoader: mapID=[" + mapID + "] is not a valid mapID.");
		}
		
	}
}
