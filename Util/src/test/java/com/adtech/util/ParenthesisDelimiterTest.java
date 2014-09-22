package com.adtech.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure the {@code ParenthesisDelimiter} object is functioning 
 * properly.
 * 
 * @author Zachary Radtka
 */
public class ParenthesisDelimiterTest {

	/** An array of valid nested delimiters */
	private static final Character[] VALID_DELIMITERS = { '{' };
	
	/** An array of invalid nested delimiters */
	private static final Character[] INVALID_DELIMITERS = { '(', '[', '*', 'a' };

	/** The ParenthesisDelimiter object to test */
	private ParenthesisDelimiter delimiter;

	/**
	 * Initialize the ParenthesisDelimiter object
	 */
	@Before
	public void setup() {
		delimiter = new ParenthesisDelimiter();
	}

	/**
	 * Ensure that all of the valid delimiters return true
	 */
	@Test
	public void validNestedDelimiterTest0() {
		Character nestedDelimiter = VALID_DELIMITERS[0];

		assertTrue(nestedDelimiter + " should be a valid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}

	/**
	 * Ensure that all of the invalid delimiters return false
	 */
	@Test
	public void invalidNestedDelimiterTest0() {
		Character nestedDelimiter = INVALID_DELIMITERS[0];

		assertFalse(nestedDelimiter + " should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}

	/**
	 * Ensure that all of the invalid delimiters return false
	 */
	@Test
	public void invalidNestedDelimiterTest1() {
		Character nestedDelimiter = INVALID_DELIMITERS[1];

		assertFalse(nestedDelimiter + " should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}

	/**
	 * Ensure that all of the invalid delimiters return false
	 */
	@Test
	public void invalidNestedDelimiterTest2() {
		Character nestedDelimiter = INVALID_DELIMITERS[2];

		assertFalse(nestedDelimiter + " should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}

	/**
	 * Ensure that all of the invalid delimiters return false
	 */
	@Test
	public void invalidNestedDelimiterTest3() {
		Character nestedDelimiter = INVALID_DELIMITERS[3];

		assertFalse(nestedDelimiter + " should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}

	/**
	 * Ensure that all of the invalid delimiters return false
	 */
	@Test
	public void nullNestedDelimiterTest() {

		assertFalse("null should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(null));
	}
	
}
