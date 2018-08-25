package com.thd.ss.ui;

import java.util.Scanner;

public class TextInputView 
{
	// Constructor:
	public TextInputView()
	{
		// TODO ...
	}
	
	// Other methods:
	
	public int getIntegerInput()
	{
		System.out.print(":");
		
		Scanner keyboard = new Scanner(System.in);
		
		int input = keyboard.nextInt();
		
		return input;
	}
}
