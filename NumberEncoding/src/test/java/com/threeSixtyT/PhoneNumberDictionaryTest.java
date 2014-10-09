package com.threeSixtyT;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * A class to ensure the {@code PhoneNumberDictionary} object is functioning 
 * properly.
 * 
 * @author Zachary Radtka
 */
public class PhoneNumberDictionaryTest {
	
	public static final String[] NUMBER_MAPPING = {
		"e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", "lop", "ghz"
	};
	
	
	public PhoneNumberDictionary dictionary;
	
	
	/**
	 * Ensure that a newly initialized dictionary is empty
	 */
	@Test
	public void isEmptyTest() {
		dictionary = new PhoneNumberDictionary(NUMBER_MAPPING);
		
		assertTrue("Dictionary should be empty", dictionary.isEmpty());
	}
	
	/**
	 * Ensure that a newly initialized dictionary is empty
	 */
	@Test
	public void isNotEmptyTest() {
		dictionary = new PhoneNumberDictionary(NUMBER_MAPPING);
		dictionary.put("a");
		
		assertFalse("Dictionary should not be empty", dictionary.isEmpty());
	}
	
	
}
