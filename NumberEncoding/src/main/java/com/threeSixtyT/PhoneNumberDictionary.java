package com.threeSixtyT;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A {@code dictionary} designed to hold mappings between numbers and words.
 * 
 * <p>This implementation guarantees a O(1) cost for insertion and retrieval.
 * 
 * <p>The current implementation <b>does not</b> support the removal of words. 
 * 
 * @author Zachary Radtka
 */
public class PhoneNumberDictionary {
	
	/** The required amount of numbers that contain mappings */
	private final int NUM_COUNT = 10;
	
	/** A multimap of strings to words */
	private final Map<String, Set<String>> dictionary;
	
	/** A mapping of characters to numbers */
	private final Map<Character, String> characterMapping;
	
	/**
	 * Initialize a new empty PhoneNumberDictionary with a mapping from the 
	 * digits 0-9 to the characters a-z.
	 * 
	 * @param mapping
	 * 			A {@code String[]} that contains a mapping between each 
	 * 			digit, 0-9, and the characters, a-z. The supplied {@code String} 
	 * 			array should have 10 elements, each element should contain the
	 * 			characters that map to that elements index. 
	 * 
	 *  @throws IllegalArgumentException If some property of the mapping the
	 *  		dictionary from being created
	 */
	public PhoneNumberDictionary(String[] mapping) {
		
		// Ensure the supplied mapping is valid
		if (mapping == null || mapping.length != NUM_COUNT) {
			throw new IllegalArgumentException("Supplied mapping must contain mappings for " 
					+ NUM_COUNT + " numbers.");
		}
		
		// Initialize the internal dictionary and the character mapping
		this.dictionary = new HashMap<String, Set<String>>();
		this.characterMapping = new HashMap<Character, String>();

		// Build the character mapping from the supplied mapping
		for(int i=0; i < mapping.length; i++) {
			for(int j=0; j < mapping[i].length(); j++) {
				this.characterMapping.put(mapping[i].charAt(j), String.valueOf(i));
			}
		}
	}
	
	/**
	 * Returns {@code true} if the PhoneNumberDictionary contains no mappings.
	 * @return {@code true} if the PhoneNumberDictionary contains no mappings,
	 * 		{@code false} otherwise
	 */
	public boolean isEmpty() {
		return this.dictionary.isEmpty();
	}
	
	/**
	 * Inserts the the specified value in this dictionary and associates it's 
	 * numerical representation as a look up key. If the dictionary previously
	 * contained a mapping for the key, the old value is replaced by the
	 * specified value.
	 * 
	 * @param value
	 * 		A value to be associated by a numerical representation 
	 * @throws
	 * 		IllegalArgumentException If some property of this value prevents it 
	 * 			from being added to this list
	 */
	public void put(String value) {
		
		// Ensure the supplied value is a valid string
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Supplied value is null or empty");
		}
		
		// Use a StringBuilder to hold the value character->digit mapping
		StringBuilder encodedValue = new StringBuilder();
		
		// Loop through the supplied value and only operate on alphabetic characters
		for (int i=0; i < value.length(); i++) {
			if (Character.isAlphabetic(value.charAt(i))) {
				
				// Determine the character to digit mapping based on the 
				// characterMapping map
				encodedValue.append(this.characterMapping.get(Character.toLowerCase(value.charAt(i))));
			}
		}

		// Ensure the value maps to a sequence of digits
		if (encodedValue.length() == 0) {
			throw new IllegalArgumentException("Supplied value does not map to any numeric value");
		}
		
		// Get the list of words currently being held for this encoding
		Set<String> currentWords = this.dictionary.get(encodedValue.toString());
		
		// If no words are being held for the current encoding, create a new
		// list to hold this and future encodings
		if (currentWords == null) {
			this.dictionary.put(encodedValue.toString(), currentWords = new HashSet<String>());
		}
		
		// Add the current value to this encoding
		currentWords.add(value);
	}
	
	/**
	 * Returns a {@code List} of words to which the specified value is mapped, 
	 * or {@code null} if this dictionary contains no mapping for the specified value.
	 * 
	 * @param value
	 * 			A {@code String} of digits whose associated values is to be 
	 * 			returned
	 * @return A {@code Set} of words to which the specified value is mapped, 
	 * or {@code null} if this dictionary contains no mapping for the specified 
	 * value
	 */
	public Set<String> get(String value) {
		return this.dictionary.get(value);
	}
}
