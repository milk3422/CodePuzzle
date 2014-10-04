package com.threeSixtyT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordTreeTest {

	public static final String[] DICTIONARY = { "Aba\"nderung",
			"Aba\"nderungsantrag", 
			"Aba\"nderungsantra\"ge",
			"Aba\"nderungsvorschlag", 
			"abarbeiten", };

	public static WordTree dictionary = new WordTree();

	@BeforeClass
	public static void CreateTreeMap() {
		
		// Add the words to the dictionary
		for (String word : DICTIONARY) {
			dictionary.put(word);
		}
	}
	
	@Test
	public void containsPrefixTest() {
		assertTrue(dictionary.containsPrefix("aba"));
	}
	
	
	@Test
	public void getTest() {
		Set<String> expected = new HashSet<String>();
		expected.add("Aba\"nderungsantrag");
		
		Set<String> actual = dictionary.get("abanderungsantrag");
		
		assertEquals(expected, actual);
	}

}
