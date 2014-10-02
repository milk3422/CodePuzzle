package com.threeSixtyT;


public class Driver {

	public static void main(String[] args) {

		System.out.println("Hello World");
		
		// New Word Tree
		WordTree wt = new WordTree();
		
		wt.put("Abba");
		wt.put("AA");
		
		String s1 = "abba";
		
		if (wt.contains(s1)) {
			System.out.println(s1 + " in Word Tree");
			System.out.println(wt.get(s1));
		} else {
			System.out.println(s1 + " not in Word Tree");
		}
		
		
		System.out.println(wt.get("Aa"));
		
		
		
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
