package com.threeSixtyT;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Words {

	public static List<List<String>> retrievePossibleNumbers(String value, PhoneNumberDictionary dictionary) {
		
		// Return an empty list if the dictionary is empty
		if (dictionary.isEmpty() || value.isEmpty()) {
			return new ArrayList<List<String>>();
		}
		
		// Hold all of the new possible combinations
		LinkedList<Map.Entry<List<String>, String>> possibleCombinations = new LinkedList<Map.Entry<List<String>, String>>();
	
		//TODO Checkout validitiy of regex
		// Remove all non-digit characters
//		value.replaceAll("[^0-9]", "");
		
		int leadingZero = 0;
		int currPosition = 0;
		
		// Add the first character to the 
		for (currPosition = 0; currPosition < value.length() && possibleCombinations.isEmpty(); currPosition++) {
			// Get the current character
			char currChar = value.charAt(currPosition);
			
			// Only process digits
			if (Character.isDigit(currChar)) {

				if (value.charAt(currPosition) != '0') {
					possibleCombinations.add(new AbstractMap.SimpleEntry<List<String>, String>(new LinkedList<String>(), 
							String.valueOf(currChar)));
				} else {
					leadingZero++;
				}
			}
		}
		
		// Loop through the 
		do {
			
		} while(++currPosition < value.length());
		
		
		
		return null;
	}
	
}
