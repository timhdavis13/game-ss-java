package com.thd.ss.field;

import java.util.ArrayList;
import java.util.List;

import com.thd.ss.actions.Action;
import com.thd.ss.actions.TargetSelection;
import com.thd.ss.actions.Targetable;
import com.thd.ss.combat.Damageable;
import com.thd.ss.combat.ElementDamageable;
import com.thd.ss.combat.Elemental;

/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */

public class FieldLocation extends Coordinate2D implements TargetSelection, Targetable
{
	// Private members:
	private FieldOccupant occupant = null;
	private FieldMap map;
	
	// Constructor:
	public FieldLocation(FieldMap map, int x, int y)
	{
		super(x, y);
		
		this.map = map;
	}

	// Getters and setters:
	
	/**
	 * @return the occupant
	 */
	public FieldOccupant getOccupant() { return occupant; }

	/**
	 * @param occupant The occupant to set.
	 * 	Also sets the current location of the occupant to this location.
	 * @throws AlreadyOccupiedException if there is already an occupant.
	 */
	public void setOccupant(FieldOccupant occupant) throws AlreadyOccupiedException
	{
		if (this.isOccupied())
		{
			throw new AlreadyOccupiedException(this + " is already occupied.");
		}
		
		this.occupant = occupant;
		occupant.setCurrentLocation(this);
	}
	
	// Getter only:
	
	public FieldMap getMap()
	{
		return this.map;
	}
	
	// Other methods:
	
	public boolean isOccupied()
	{
		return (this.occupant != null);
	}
	
	public void removeOccupant()
	{
		if (isOccupied())
		{
			// Remove this location from the occupant's current location:
			this.occupant.setCurrentLocation(null);
			
			// Reset the occupant of this location:
			this.occupant = null;
		}
	}
	
	// Implementations:

	//// TargetSelection:
	
	@Override
	public List<Targetable> getTargets(Action action)
	{
		List<Targetable> targets = new ArrayList<Targetable>();
		
		if (isOccupied())
		{
			if (this.occupant instanceof Targetable)
			{
				targets.add((Targetable)occupant);
			}
		}
		
		// TODO: Check if any field location components on this FieldLoc are targetable... TODO
		// TODO....
		
		return targets;
	}
	
	@Override
	public String getName() 
	{
		return this.toString();
	}
	
	// General methods:
	
	@Override
	public String toString()
	{
		return "Field Location:(" + this.xCoordinate + "," + this.yCoordinate + "):occupant=[" + this.occupant + "]";
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
        if (!(other instanceof FieldLocation))
        {
            return false;
        }
         
        // Check if have same x and y values:
        FieldLocation otherFieldLocation = (FieldLocation) other;
          
        return (this.getX() == otherFieldLocation.getX()
        		&& this.getY() == otherFieldLocation.getY());
	}

	
	
	
}
