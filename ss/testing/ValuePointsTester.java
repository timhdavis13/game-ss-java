package com.thd.ss.testing;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.thd.ss.combat.ValuePoints;

/**
 * Created 06/12/2018
 * 
 * @author timhdavis.
 * 
 */

public class ValuePointsTester 
{

	private ValuePoints health;
	private static final int INITIAL_MAXIMUM = 100;
	
	@Before
	public void setUp() throws Exception
	{
		health = new ValuePoints(INITIAL_MAXIMUM);
	}
	
	// Test restore(amount) method:
	
	@Test
	public void testReduce() 
	{
		// Test:
		health.reduce(5);
		
		// Assert:
		int expectedValue = INITIAL_MAXIMUM - 5;
		assertEquals(health.getCurrentValue(), expectedValue);
	}
	
	@Test
	public void testReduceToZero() 
	{	
		// Test:
		health.reduce(INITIAL_MAXIMUM);
		
		// Assert: that current value will not go below 0:
		assertEquals(health.getCurrentValue(), 0);
	}
	
	@Test
	public void testReduceBelowZero() 
	{	
		// Test:
		health.reduce(INITIAL_MAXIMUM + 10);
		
		// Assert: that current value will not go below 0:
		assertEquals(health.getCurrentValue(), 0);
	}
	
	// Test restore(amount) method:
	
	@Test
	public void testRestore() 
	{	
		// Set up: Cannot test restore if already at maximum:
		int reduceValue = 5;
		health.reduce(reduceValue);
		
		// Test:
		int restoreValue = 2;
		health.restore(restoreValue);
		
		// Assert:
		int expectedValue = INITIAL_MAXIMUM - reduceValue + restoreValue;
		assertEquals(health.getCurrentValue(), expectedValue);
	}
	
	@Test
	public void testRestoreBeyondMaxWhenAtMax() 
	{
		// Test:
		health.restore(2);
		
		// Assert: that current value is not restored past maximum value: 
		assertEquals(health.getCurrentValue(), INITIAL_MAXIMUM);
	}
	
	@Test
	public void testRestoreBeyondMaxWhenBelowMax() 
	{	
		// Set up: Cannot test restore if already at maximum:
		health.reduce(5);
		
		// Test:
		health.restore(15);
		
		// Assert: that current value is not restored past maximum value:
		assertEquals(health.getCurrentValue(), INITIAL_MAXIMUM);
	}
	
	// Test setCurrentValue(newCurrentValue) method:
	
	@Test
	public void testSetCurrentValue() 
	{	
		// Set up:
		int newValue = 55;
		
		// Test:
		health.setCurrentValue(newValue);
		
		// Assert:
		assertEquals(health.getCurrentValue(), newValue);
	}
	
	@Test
	public void testSetCurrentValueToZero() 
	{	
		// Set up:
		int newValue = 0;
		
		// Test:
		health.setCurrentValue(newValue);
		
		// Assert:
		assertEquals(health.getCurrentValue(), newValue);
	}
	
	@Test
	public void testSetCurrentValueBelowZero() 
	{	
		// Set up:
		int newValue = -1;
		
		// Test:
		health.setCurrentValue(newValue);
		
		// Assert: that current value will not go below 0:
		assertEquals(health.getCurrentValue(), 0);
	}
	
	@Test
	public void testSetCurrentValueAboveMaximum() 
	{	
		// Set up:
		int newValue = INITIAL_MAXIMUM + 1;
		
		// Test:
		health.setCurrentValue(newValue);
		
		// Assert: that current value will not go above maximum value:
		assertEquals(health.getCurrentValue(), INITIAL_MAXIMUM);
	}
	
	// Test setMaximumValue(newMaximumValue) method:
	
	@Test
	public void testSetMaximumValue() 
	{	
		// Set up:
		int newValue = INITIAL_MAXIMUM - 10;
		
		// Test:
		health.setMaximumValue(newValue);
		
		// Assert:
		assertEquals(health.getMaximumValue(), newValue);
		
		// Also assert current value has been updated as well:
		assertEquals(health.getCurrentValue(), health.getMaximumValue());
	}
	
	@Test
	public void testSetMaximumValueWithCurrentValueBelowMaximim() 
	{	
		// Set up:
		int newCurrentValue = INITIAL_MAXIMUM - 30;
		health.setCurrentValue(newCurrentValue);
		
		int newMaximumValue = INITIAL_MAXIMUM - 10;
		
		// Test:
		health.setMaximumValue(newMaximumValue);
		
		// Assert:
		assertEquals(health.getMaximumValue(), newMaximumValue);
		
		// Also assert current value does not change:
		assertEquals(health.getCurrentValue(), newCurrentValue);
	}
	
	@Test
	public void testSetMaximumValueToZero() 
	{	
		// Set up:
		int newMaximumValue = 0;
		
		// Test:
		health.setMaximumValue(newMaximumValue);
		
		// Assert:
		assertEquals(health.getMaximumValue(), 0);
		
		// Also assert current value has been updated as well:
		assertEquals(health.getCurrentValue(), health.getMaximumValue());
	}
	
	@Test
	public void testSetMaximumValueBelowZero() 
	{	
		// Set up:
		int newMaximumValue = -1;
		
		// Test:
		health.setMaximumValue(newMaximumValue);
		
		// Assert: that maximum value will not go below 0:
		assertEquals(health.getMaximumValue(), 0);
		
		// Also assert current value has been updated as well:
		assertEquals(health.getCurrentValue(), health.getMaximumValue());
	}
	
	// Other testing:
	
	@Test
	public void testConsecutive() 
	{	
		System.out.println("HP = " + health);
		
		health.reduce(5);
		
		System.out.println("HP-5 = " + health);
		
		health.restore(4);
		
		System.out.println("HP+4 = " + health);
		
		health.restore(10);
		
		System.out.println("HP+10 = " + health);
		
		health.reduce(25);
		
		System.out.println("HP-25 = " + health);
		
		// Assert:
		int expectedValue = INITIAL_MAXIMUM - 25;
		assertEquals(health.getCurrentValue(), expectedValue);
	}

}
