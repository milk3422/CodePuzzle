package com.adtech.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure the {@code SquareBraceDelimiterTest} object is functioning 
 * properly.
 * 
 * @author Zachary Radtka
 */
public class SquareBraceDelimiterTest {

	/** An array of allowed nested delimiters */
	private static final Character[] ALLOWED_NESTED_DELIMITERS = {'[', '{', '('};
	
	/** An array of valid nested delimiters */
	private static final Character[] VALID_DELIMITERS = {'{', '(', '['};
	
	/** An array of invalid nested delimiters */
	private static final Character[] INVALID_DELIMITERS = {'*', 'a'};

	/** The ParenthesisDelimiter object to test */
	private SquareBraceDelimiter delimiter;

	/**
	 * Initialize the ParenthesisDelimiter object
	 */
	@Before
	public void setup() {
		delimiter = new SquareBraceDelimiter();
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
	 * Ensure that all of the valid delimiters return true
	 */
	@Test
	public void validNestedDelimiterTest1() {
		Character nestedDelimiter = VALID_DELIMITERS[1];

		assertTrue(nestedDelimiter + " should be a valid nested delimtier",
				delimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}
	
	/**
	 * Ensure that all of the valid delimiters return true
	 */
	@Test
	public void validNestedDelimiterTest2() {
		Character nestedDelimiter = VALID_DELIMITERS[2];

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
	public void nullNestedDelimiterTest() {

		assertFalse("null should be an invalid nested delimtier",
				delimiter.isAllowedNestedDelimiter(null));
	}
	
	/**
	 * Ensure getAllowedNestedDelimiters is being created correctly
	 */
	@Test
	public void getAllowedNestedDelimitersTest() {
		Set<Character> expected = new HashSet<Character>(Arrays.asList(ALLOWED_NESTED_DELIMITERS));

		assertEquals("Allowed nested delimiters should be {'{','[','('}", 
				expected, delimiter.getAllowedNestedDelimiters());
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void setAllowedNestedDelimitersTest0() {
		Set<Character> expected = new HashSet<Character>(Arrays.asList('['));

		delimiter.setAllowedNestedDelimiters(expected);
		
		assertEquals("Allowed nested delimiters should be {'['}", 
				expected, delimiter.getAllowedNestedDelimiters());
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void setAllowedNestedDelimitersTest1() {
		Set<Character> expected = new HashSet<Character>(Arrays.asList('<'));

		delimiter.setAllowedNestedDelimiters(expected);
		
		assertTrue("Allowed nested delimiters should be {}", 
				delimiter.getAllowedNestedDelimiters().isEmpty());
	}
	
}
