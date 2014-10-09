package com.threeSixtyT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure the {@code PhoneNumberDictionary} object is functioning
 * properly.
 * 
 * @author Zachary Radtka
 */
public class PhoneNumberDictionaryTest {

	/** A mapping of characters to corresponding numbers */
	public static final String[] CHARACTER_MAPPING = { "e", "jnq", "rwx", "dsy",
			"ft", "am", "civ", "bku", "lop", "ghz" };

	/** A {@code PhoneNumberDictionary} that is being tested */
	public PhoneNumberDictionary dictionary;

	/**
	 * Initialize the dictionary before each test
	 */
	@Before
	public void Initialize() {
		dictionary = new PhoneNumberDictionary(CHARACTER_MAPPING);
	}

	/**
	 * Ensure that a newly initialized dictionary is empty
	 */
	@Test
	public void isEmptyTest() {
		assertTrue("Dictionary should be empty", dictionary.isEmpty());
	}

	/**
	 * Ensure that a dictionary with an element is not empty
	 */
	@Test
	public void isNotEmptyTest() {
		dictionary.put("a");
		assertFalse("Dictionary should not be empty", dictionary.isEmpty());
	}

	/**
	 * Ensure that an empty dictionary returns a null
	 */
	@Test
	public void getEmptyTest() {
		assertNull("null should be returned for an empty dictionary",
				dictionary.get("5"));
	}

	/**
	 * Ensure that the correct character is returned
	 */
	@Test
	public void getCharacterTest() {
		Set<String> expected = new HashSet<String>();
		String character = "a";

		expected.add(character);
		dictionary.put(character);

		assertEquals("A lower case '" + character + "' was expected", expected,
				dictionary.get("5"));
	}

	/**
	 * Ensure that the correct character is returned
	 */
	@Test
	public void getCapitalTest() {
		Set<String> expected = new HashSet<String>();
		String character = "A";

		expected.add(character);
		dictionary.put(character);

		assertEquals("5 should return " + character, expected, dictionary.get("5"));
	}
	
	/**
	 * Ensure that the correct character is returned
	 */
	@Test
	public void getNonValidLeadingCharacterTest() {
		Set<String> expected = new HashSet<String>();
		String character = "-A";

		expected.add(character);
		dictionary.put(character);

		assertEquals("5 should return " + character, expected, dictionary.get("5"));
	}
	
	/**
	 * Ensure that the correct character is returned
	 */
	@Test
	public void getNonValidTrailingCharacterTest() {
		Set<String> expected = new HashSet<String>();
		String character = "a\"";

		expected.add(character);
		dictionary.put(character);

		assertEquals("5 should return " + character, expected, dictionary.get("5"));
	}
	
	/**
	 * Ensure that the correct character is returned
	 */
	@Test
	public void getNonValidLeadingAndTrailingCharacterTest() {
		Set<String> expected = new HashSet<String>();
		String character = "\"A-";

		expected.add(character);
		dictionary.put(character);

		assertEquals("5 should return " + character, expected, dictionary.get("5"));
	}
	
	/**
	 * Ensure that the correct set of words is returned
	 */
	@Test
	public void getMultipleWordTest() {
		Set<String> expected = new HashSet<String>();		
		String[] words = {"Abba", "aBBA", "A\"BbA", "abba", "a-b-b-a"};

		expected.addAll(Arrays.asList(words));
		
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals(expected, dictionary.get("5775"));
	}
	
	/**
	 * Ensure that a null is returned
	 */
	@Test
	public void getZeroWordTest() {
		String[] words = {"Abba", "aBBA", "A\"BbA", "abba", "a-b-b-a"};
		
		for (String word : words) {
			dictionary.put(word);
		}

		assertNull("5 should return null", dictionary.get("5"));
	}
	
}
