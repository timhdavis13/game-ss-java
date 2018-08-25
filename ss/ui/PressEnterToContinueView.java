package com.thd.ss.ui;

import java.io.IOException;

public class PressEnterToContinueView 
{
	public static void waitForContinue()
	{
		System.out.println("[Press Enter to continue.]");
		
		try 
		{
			System.in.read(); // Waits for enter.
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
}
