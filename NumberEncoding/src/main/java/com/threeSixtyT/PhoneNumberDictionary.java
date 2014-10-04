package com.threeSixtyT;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneNumberDictionary {
	
	/** a mapping of strings to words */
	private Map<String, List<String>> dictionary;
	
	/** A mapping of characters to numbers */
	private Map<Character, String> characterMapping;
	

	public PhoneNumberDictionary(String[] mapping) {
		this.dictionary = new HashMap<String, List<String>>();
		this.characterMapping = new HashMap<Character, String>();

		// Build the character mapping
		for(int i=0; i < mapping.length; i++) {
			for(int j=0; j < mapping[i].length(); j++) {
				this.characterMapping.put(mapping[i].charAt(j), String.valueOf(i));
			}
		}
	}
	
	public boolean isEmpty() {
		return this.dictionary.isEmpty();
	}
	
	public void put(String value) {
		
		StringBuilder sb = new StringBuilder();
		
		// Loop through the string
		for (int i=0; i < value.length(); i++) {
			// Only add characters that are 
			if (Character.isAlphabetic(value.charAt(i))) {
				// Look up the value in the mapping				
				sb.append(this.characterMapping.get(Character.toLowerCase(value.charAt(i))));
			}
		}

		// Add a word if 
		List<String> currWords = this.dictionary.get(sb.toString());
		
		if (currWords == null) {
			this.dictionary.put(sb.toString(), currWords = new LinkedList<String>());
		}
		
		currWords.add(value);
	}
	
	public List<String> get(String value) {
		List<String> values = this.dictionary.get(value);
		
		if (values == null) {
			return new LinkedList<String>();
		}
		
		return values;
	}
	
}
