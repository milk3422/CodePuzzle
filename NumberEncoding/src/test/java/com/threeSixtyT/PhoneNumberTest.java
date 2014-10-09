package com.threeSixtyT;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PhoneNumberTest {

	public static final String[] NUMBER_MAPPING = {
		"e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", "lop", "ghz"
	};
	
	public static final String[] GERMAN_DICTIONARY = { "an", "blau", "Bo\"",
			"Boot", "bo\"s", "da", "Fee", "fern", "Fest", "fort", "je",
			"jemand", "mir", "Mix", "Mixer", "Name", "neu", "o\"d", "Ort",
			"so", "Tor", "Torf", "Wasser" };

	public static final String[] PHONE_NUMBERS = { "112", "5624-82", "4824",
			"0721/608-4067", "10/783--5", "1078-913-5", "381482", "04824" };

	public static final String[] MAPPING = { "5624-82: mir Tor",
			"5624-82: Mix Tor", "4824: Torf", "4824: fort", "4824: Tor 4",
			"10/783--5: neu o\"d 5", "10/783--5: je bo\"s 5",
			"10/783--5: je Bo\" da", "381482: so 1 Tor", "04824: 0 Torf",
			"04824: 0 fort", "04824: 0 Tor 4" };

	/**
	 * Ensure that a newly initialized dictionary is empty
	 */
	@Test
	public void fullTest() {
		// Create a new dictionary
		PhoneNumberDictionary dictionary = new PhoneNumberDictionary(NUMBER_MAPPING);
		
		// Add words to the dictionary
		for (String currWord : GERMAN_DICTIONARY) {
			dictionary.put(currWord);
		}
		
		
		// Create the expected results
		Set<String> expected = new HashSet<String>(Arrays.asList(MAPPING));
		
		
		Set<String> actual = new HashSet<String>();
		
		// Initialize the phone number object
		PhoneNumber number = new PhoneNumber(dictionary);
		

		for (String currNumber : PHONE_NUMBERS) {
			for (String solution : number.decode(currNumber)) {
				actual.add(currNumber + ": " + solution);
			}
		}
		assertEquals(expected, actual);
		
	}
	
}
