package com.adtech.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure the {@code WellFormedStringTest} object is functioning 
 * properly.
 * 
 * @author Zachary Radtka
 */
public class WellFormedStringTest {

	/** A WellFormedString object to test*/
	WellFormedString wfs;

	/**
	 * Initialize the WellFormedString before each test
	 */
	@Before
	public void setUp() {
		wfs = new WellFormedString();
	}

	/**
	 * Ensure parenthesis are working correctly
	 */
	@Test
	public void parenthesisTest() {
		String input = "()";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure parenthesis are working correctly
	 */
	@Test
	public void validParenthesisTest() {
		String input = "({})";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure parenthesis are working correctly
	 */
	@Test
	public void invalidParenthesisTest0() {
		String input = "(())";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure parenthesis are working correctly
	 */
	@Test
	public void invalidParenthesisTest1() {
		String input = "([])";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure curly braces are working correctly
	 */
	@Test
	public void curlyBraceTest() {
		String input = "{}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure curly braces are working correctly
	 */
	@Test
	public void validCurlyBraceTest() {
		String input = "{[]}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure curly braces are working correctly
	 */
	@Test
	public void invalidCurlyBraceTest0() {
		String input = "{{}}";

		assertFalse("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure curly braces are working correctly
	 */
	@Test
	public void invalidCurlyBraceTest1() {
		String input = "{()}";

		assertFalse("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure square braces are working correctly
	 */
	@Test
	public void squareBraceTest() {
		String input = "[]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure square braces are working correctly
	 */
	@Test
	public void validSquareBraceTest0() {
		String input = "[[]]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure square braces are working correctly
	 */
	@Test
	public void validSquareBraceTest1() {
		String input = "[()]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure square braces are working correctly
	 */
	@Test
	public void validSquareBraceTest2() {
		String input = "[{}]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure square braces are working correctly
	 */
	@Test
	public void invalidSquareBraceTest() {
		String input = "[([])]";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure matching characters is working correctly
	 */
	@Test
	public void matchingTest0() {
		String input = "(";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure matching characters is working correctly
	 */
	@Test
	public void matchingTest1() {
		String input = "(}";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a list of delimiters works correctly
	 */
	@Test
	public void listTest0() {
		String input = "()()";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a list of delimiters works correctly
	 */
	@Test
	public void listTest1() {
		String input = "()[]{}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a list of delimiters works correctly
	 */
	@Test
	public void listTest2() {
		String input = "[()()]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a list of delimiters works correctly
	 */
	@Test
	public void listTest3() {
		String input = "{[][()()]}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a invalid characters returns an invalid result
	 */
	@Test
	public void invalidCharacterTest0() {
		String input = "<>";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a invalid characters returns an invalid result
	 */
	@Test
	public void invalidCharacterTest1() {
		String input = "( )";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a invalid characters returns an invalid result
	 */
	@Test
	public void invalidCharacterTest2() {
		String input = "[|]";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a lengthy valid sequences return correct results
	 */
	@Test
	public void validSequenceTest0() {
		String input = "[{[{[]}]}]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a lengthy valid sequences return correct results
	 */
	@Test
	public void validSequenceTest1() {
		String input = "{[{[]}]}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a lengthy valid sequences return correct results
	 */
	@Test
	public void validSequenceTest3() {
		String input = "[{[{[()[{}]{}]}]}]";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}

	/**
	 * Ensure a lengthy valid sequences return correct results
	 */
	@Test
	public void validSequenceTest4() {
		String input = "{[{[()[{}]{}]}]}";

		assertTrue("String '" + input + "' should be valid", wfs.isValid(input));
	}
	
	/**
	 * Ensure a lengthy valid sequences return correct results
	 */
	@Test
	public void invalidSequenceTest() {
		String input = "{[{[({})[{}]{}]}]}";

		assertFalse("String '" + input + "' should be invalid", wfs.isValid(input));
	}
	
	/**
	 * Ensure an empty string returns false
	 */
	@Test
	public void emptyStringTest() {
		String input = "";

		assertFalse("An empty String should be invalid", wfs.isValid(input));
	}

	/**
	 * Ensure a nulls are handled properly
	 */
	@Test
	public void nullTest() {
		assertFalse("A null should be invalid", wfs.isValid(null));
	}
	
}
