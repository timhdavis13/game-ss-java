package com.thd.ss.combat;

import java.util.Set;

import javax.swing.text.Highlighter;

public class ElementDamageCalculator 
{
	// Private members:
	private static double[][] elementMultiplierMatrix;
	
	private static int nonIndex = 0;
	private static int metalIndex = 1;
	private static int fireIndex = 2;
	private static int waterIndex = 3;
	private static int windIndex = 4;
	private static int lightningIndex = 5;
	private static int earthIndex = 6;
	private static int plantIndex = 7;
	private static int iceIndex = 8;
	private static int lightIndex = 9;
	private static int darkIndex = 10;
	
	// Constructor:
	
	// Static methods:
	
	
	
	private static double[][] getElementMultiplierMatrix()
	{
		if (elementMultiplierMatrix == null)
		{
			setupElementMultiplierMatrix();
		}
		
		return elementMultiplierMatrix;
	}
	
	public static double getElementMultiplier(ElementType attackElement, Set<ElementType> defenderBodyElements)
	{
		double sum = 0.0;
		int count = 0;
		
		for (ElementType defenderElement : defenderBodyElements)
		{
			sum = sum + getElementMultiplier(attackElement, defenderElement);
			count++;
		}
		
		if (count == 0)
		{
			return 1.0;
		}
		
		return (sum / ((double) count));
	}
	
	
	public static double getElementMultiplier(ElementType attackElement, ElementType defenderElement)
	{
		return getElementMultiplierMatrix()[attackElement.getIndex()][defenderElement.getIndex()];
	}
	
	
	
	
	private static void setupElementMultiplierMatrix()
	{
		int size = ElementType.values().length;
		elementMultiplierMatrix = new double[size][size];
		
		double normalDamage = 1.0;
		double lowerDamage = 0.6;
		double veryLowDamage = 0.2;
		double higherDamage = 1.4;
		double veryHighDamage = 1.8;
		
		// Setup Attack multipliers:
		
		//// Non-Elemental:
		///// All Non-Elemental attacks do x1.0 damage to all other types:
		for (int elementIndex = 0; elementIndex < size; elementIndex++)
		{
			elementMultiplierMatrix[nonIndex][elementIndex] = 1.0;
		}
		///// All Non-Elemental types take x1.0 damage from all other types:
		for (int elementIndex = 0; elementIndex < size; elementIndex++)
		{
			elementMultiplierMatrix[elementIndex][nonIndex] = 1.0;
		}
		
		//// Metal attacks:
		elementMultiplierMatrix[metalIndex][metalIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[metalIndex][fireIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[metalIndex][waterIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[metalIndex][windIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[metalIndex][lightningIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[metalIndex][earthIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[metalIndex][plantIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[metalIndex][iceIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[metalIndex][lightIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[metalIndex][darkIndex] = normalDamage; //1.0;
		
		//// Fire attacks:
		elementMultiplierMatrix[fireIndex][metalIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[fireIndex][fireIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[fireIndex][waterIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[fireIndex][windIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[fireIndex][lightningIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[fireIndex][earthIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[fireIndex][plantIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[fireIndex][iceIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[fireIndex][lightIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[fireIndex][darkIndex] = higherDamage; //1.5;
		
		//// Water attacks:
		elementMultiplierMatrix[waterIndex][metalIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[waterIndex][fireIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[waterIndex][waterIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[waterIndex][windIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[waterIndex][lightningIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[waterIndex][earthIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[waterIndex][plantIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[waterIndex][iceIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[waterIndex][lightIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[waterIndex][darkIndex] = normalDamage; //1.0;
		
		
		//// Wind attacks:
		elementMultiplierMatrix[windIndex][metalIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[windIndex][fireIndex] = higherDamage; //1.8;
		elementMultiplierMatrix[windIndex][waterIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[windIndex][windIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[windIndex][lightningIndex] = higherDamage; //1.2;
		elementMultiplierMatrix[windIndex][earthIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[windIndex][plantIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[windIndex][iceIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[windIndex][lightIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[windIndex][darkIndex] = normalDamage; //1.0;
		
		//// Lightning attacks:
		elementMultiplierMatrix[lightningIndex][metalIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[lightningIndex][fireIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[lightningIndex][waterIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[lightningIndex][windIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[lightningIndex][lightningIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[lightningIndex][earthIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[lightningIndex][plantIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[lightningIndex][iceIndex] = higherDamage; //1.2;
		elementMultiplierMatrix[lightningIndex][lightIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[lightningIndex][darkIndex] = higherDamage; //1.5;
		
		//// Earth attacks:
		elementMultiplierMatrix[earthIndex][metalIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[earthIndex][fireIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[earthIndex][waterIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[earthIndex][windIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[earthIndex][lightningIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[earthIndex][earthIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[earthIndex][plantIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[earthIndex][iceIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[earthIndex][lightIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[earthIndex][darkIndex] = normalDamage; //1.0;
		
		//// Plant attacks:
		elementMultiplierMatrix[plantIndex][metalIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[plantIndex][fireIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[plantIndex][waterIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[plantIndex][windIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[plantIndex][lightningIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[plantIndex][earthIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[plantIndex][plantIndex] = veryLowDamage; //0.2;
		elementMultiplierMatrix[plantIndex][iceIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[plantIndex][lightIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[plantIndex][darkIndex] = lowerDamage; //0.3;
		
		//// Ice attacks:
		elementMultiplierMatrix[iceIndex][metalIndex] = higherDamage; //1.2;
		elementMultiplierMatrix[iceIndex][fireIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[iceIndex][waterIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[iceIndex][windIndex] = higherDamage; //1.2;
		elementMultiplierMatrix[iceIndex][lightningIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[iceIndex][earthIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[iceIndex][plantIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[iceIndex][iceIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[iceIndex][lightIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[iceIndex][darkIndex] = lowerDamage; //0.2;
		
		//// Light attacks:
		elementMultiplierMatrix[lightIndex][metalIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[lightIndex][fireIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[lightIndex][waterIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[lightIndex][windIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[lightIndex][lightningIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[lightIndex][earthIndex] = lowerDamage; //0.5;
		elementMultiplierMatrix[lightIndex][plantIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[lightIndex][iceIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[lightIndex][lightIndex] = veryLowDamage; //0.1;
		elementMultiplierMatrix[lightIndex][darkIndex] = veryHighDamage; //2.0;
		
		//// Dark attacks:
		elementMultiplierMatrix[darkIndex][metalIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[darkIndex][fireIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[darkIndex][waterIndex] = lowerDamage; //0.2;
		elementMultiplierMatrix[darkIndex][windIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[darkIndex][lightningIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[darkIndex][earthIndex] = normalDamage; //1.0;
		elementMultiplierMatrix[darkIndex][plantIndex] = higherDamage; //1.5;
		elementMultiplierMatrix[darkIndex][iceIndex] = lowerDamage; //0.3;
		elementMultiplierMatrix[darkIndex][lightIndex] = veryHighDamage; //2.0;
		elementMultiplierMatrix[darkIndex][darkIndex] = veryLowDamage; //0.1;
		
	}
	
}
