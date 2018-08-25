package com.thd.ss.combat;
/**
 * Created 06/12/2018
 * 
 * Guarantees:
 * - maximumValue will never be less than 0.
 * - currentValue will never be less than 0.
 * - currentValue will never be greater than maximumValue.
 * 
 * @author timhdavis
 *
 * 
 */
public class ValuePoints 
{
	// Constants:
	private static int MINIMUM_VALUE = 0;
	
	// Private members:
	private int maximumValue;
	private int currentValue;
	
	// Constructor:
	public ValuePoints(int maximumValue) 
	{
		this.maximumValue = maximumValue;
		
		// Current value begins at maximum value:
		this.currentValue = maximumValue;
	}
	
	// Getters:
	
	/**
	 * @return The current value.
	 */
	public int getCurrentValue() { return currentValue; }
	
	/**
	 * @return The maximum value.
	 */
	public int getMaximumValue() { return maximumValue; }
	
	// Setters:
	
	/**
	 * Updates current value.
	 * @param newCurrentValue The updated value of currentValue.
	 *  If newCurrentValue is less than 0, the updated currentValue
	 *   will be set to 0.
	 *  If newCurrentValue is greater than maximumValue, the updated currentValue
	 *   will be set to the value of maximumValue.
	 */
	public void setCurrentValue(int newCurrentValue)
	{				
		this.currentValue = newCurrentValue;
		
		// Current value cannot be less than MINIMUM_VALUE:
		if (this.currentValue < MINIMUM_VALUE) 
		{
			this.currentValue = MINIMUM_VALUE;
		}
		
		// Change current value so that is not greater than maximum value:
		else if (this.currentValue > this.maximumValue) 
		{
			this.currentValue = this.maximumValue;
		}
	}
	
	/**
	 * Updates maximum value.
	 * @param newMaximumValue The updated value of maximumValue.
	 *  If newMaximumValue is less than 0, the updated maximumValue
	 *   will be set to 0.
	 *  If newMaximumValue is less than the value of currentValue, 
	 *   currentValue will also be updated and will be set to the 
	 *   value of the updated maximumValue.
	 */
	public void setMaximumValue(int newMaximumValue)
	{
		this.maximumValue = newMaximumValue;
		
		// Maximum value cannot be less than 0:
		if (this.maximumValue < MINIMUM_VALUE)
		{
			this.maximumValue = MINIMUM_VALUE;
		}
		
		// Change current value so that is not greater than maximum value: 
		if (this.maximumValue < this.currentValue)
		{
			this.currentValue = this.maximumValue;
		}
	}
	
	// Other modifiers:
	
	/**
	 * Increases current value by amount.
	 * @param amount The amount to increase currentValue by.
	 *  If amount is greater than the difference between
	 *   maximumValue and currentValue, then currentValue
	 *   will be set to the value of maximumValue.
	 */
	public void restore(int amount)
	{
		setCurrentValue(this.currentValue + amount);
	}
	
	
	/**
	 * Decreases current value by amount.
	 * @param amount The amount to decrease currentValue by.
	 *  If amount is greater than currentValue, then currentValue
	 *   will be set to 0.
	 */
	public void reduce(int amount)
	{
		setCurrentValue(this.currentValue - amount);
	}
	
	/**
	 * Increases maximum value by amount.
	 * @param amount The amount to increase maximumValue by.
	 *  If newMaximumValue is less than the value of currentValue, 
	 *   currentValue will also be updated and will be set to the 
	 *   value of the updated maximumValue.
	 *   If newMaximumValue is less than 0, the updated maximumValue
	 *   will be set to 0.
	 */
	public void increaseMaximum(int amount)
	{
		setMaximumValue(this.maximumValue + amount);
	}
	
	/**
	 * Decreases maximum value by amount.
	 * @param amount The amount to decrease maximumValue by.
	 *  If newMaximumValue is less than the value of currentValue, 
	 *   currentValue will also be updated and will be set to the 
	 *   value of the updated maximumValue.
	 *   If newMaximumValue is less than 0, the updated maximumValue
	 *   will be set to 0.
	 */
	public void decreaseMaximum(int amount)
	{
		setMaximumValue(this.maximumValue - amount);
	}
	
	// Other methods:
	
	@Override
	public String toString()
	{
		return this.currentValue + "/" + this.maximumValue;
	}

}
