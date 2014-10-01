package com.threeSixtyT;

import java.util.TreeMap;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Hello World");
		
		// New Word Tree
		WordTree wt = new WordTree();
		
		wt.add("abba");
		wt.add("aa");
		
		String s1 = "aaa";
		
		if (wt.contains(s1)) {
			System.out.println(s1 + " in Word Tree");
		} else {
			System.out.println(s1 + " not in Word Tree");
		}
		
//		displayString(0, "Abba");

	}

	public static void displayString(int currChar, String value) {
		if(currChar < value.length()) {
			
			// Make sure the character is lower case
			char curr = Character.toLowerCase(value.charAt(currChar));
			
			if(curr < 64) {
				
			}
			
			System.out.print(value.charAt(currChar));
			displayString(++currChar, value);
		}
	}
}
