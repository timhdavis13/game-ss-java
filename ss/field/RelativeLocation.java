package com.thd.ss.field;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.ActionUser;
import com.thd.ss.actions.TargetSelection;
import com.thd.ss.actions.Targetable;

public class RelativeLocation extends Coordinate2D implements TargetSelection
{
	// Public Static members:
	public static final RelativeLocation UP = new RelativeLocation(0, 1);
	public static final RelativeLocation DOWN = new RelativeLocation(0, -1);
	public static final RelativeLocation LEFT = new RelativeLocation(-1, 0);
	public static final RelativeLocation RIGHT = new RelativeLocation(1, 0);
	
	public static final RelativeLocation SOURCE = new RelativeLocation(0, 0);
	
	// Constructor:
	public RelativeLocation(int x, int y) 
	{
		super(x, y);
	}
	
	// Static methods:
	
	
	//// Directional:
	
	public static RelativeLocation getUpLocation(int numLocationsUp)
	{
		return new RelativeLocation(0, numLocationsUp); // Up: (+0,+1x)
	}
	
	public static RelativeLocation getDownLocation(int numLocationsDown)
	{
		return new RelativeLocation(0, (-1 * numLocationsDown)); // Down: (+0,-1x)
	}
	
	public static RelativeLocation getLeftLocation(int numLocationsLeft)
	{
		return new RelativeLocation((-1 * numLocationsLeft), 0); // Left: (-1x,+0)
	}
	
	public static RelativeLocation getRightLocation(int numLocationsRight)
	{
		return new RelativeLocation(numLocationsRight, 0); // Right: (+1x,+0)
	}
	
	//// Diagonal:
	
	public static RelativeLocation getUpperLeftLocation(int numLocations)
	{
		return new RelativeLocation((-1 * numLocations), numLocations); // Left&Up: (-1x,+1x)
	}
	
	public static RelativeLocation getLowerLeftLocation(int numLocations)
	{
		return new RelativeLocation((-1 * numLocations), (-1 * numLocations)); // Left&Down: (-1x,-1x)
	}
	
	public static RelativeLocation getUpperRightLocation(int numLocations)
	{
		return new RelativeLocation(numLocations, numLocations); // Right&Up: (+1x,+1x)
	}
	
	public static RelativeLocation getLowerRightLocation(int numLocations)
	{
		return new RelativeLocation(numLocations, (-1 * numLocations)); // Right&Down: (+1x,-1x)
	}
	
	// Other methods:
	
	public FieldLocation getAbsoluteLocation(FieldLocation sourceLocation)
	{
		return sourceLocation.getMap().getLocation(sourceLocation.getX() + this.getX(), sourceLocation.getY() + this.getY());
	}

	// Implementations:
	
	////TargetSelection:
	
	@Override
	public List<Targetable> getTargets(Action action) 
	{
		List<Targetable> targets = new ArrayList<Targetable>();
		
		// Returns the actual FieldLocation relative to the action user's source location:
		
		ActionUser user = action.getParameters().getUser();
		
		if (user instanceof FieldOccupant)
		{
			FieldLocation userLocation = ((FieldOccupant) user).getCurrentLocation();
			
			if (userLocation == null) { return null; }
			
			try
			{
				FieldLocation targetLocation = this.getAbsoluteLocation(userLocation);
				
				targets = targetLocation.getTargets(action);
			}
			catch (PositionOutOfMapBoundsException exception)
			{
				// If actual location relative to source is out of bounds, return null:
				// Don't add any targets.
			}
		}
		
		return targets;
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "Relative Location:(" + this.xCoordinate + "," + this.yCoordinate + ")";
	}
	
	@Override
	public boolean equals(Object other)
	{
		// First check if same object reference:
        if (other == this) 
        {
            return true;
        }
 
        // Check if is an instance of this class:
        if (!(other instanceof RelativeLocation))
        {
            return false;
        }
         
        // Check if have same x and y values:
        RelativeLocation otherRelativeLocation = (RelativeLocation) other;
          
        return (this.getX() == otherRelativeLocation.getX()
        		&& this.getY() == otherRelativeLocation.getY());
		
	}

}
