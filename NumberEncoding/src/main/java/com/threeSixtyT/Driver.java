package com.threeSixtyT;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Hello World");
		displayString(0, "Abba");
	
	}
	
	
	public static void displayString(int currChar, String value) {
		if(currChar < value.length()) {
			
			
			
			System.out.print(value.charAt(currChar));
			displayString(++currChar, value);
		}
	}

}
