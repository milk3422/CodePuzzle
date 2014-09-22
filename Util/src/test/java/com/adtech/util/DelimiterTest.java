package com.adtech.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * A class to ensure the abstract {@code Delimiter} object is functioning 
 * properly.
 * 
 * @author Zachary Radtka
 */
public class DelimiterTest {

	/** Allowed nested delimiters */
	private static final Character[] ALLOWED_NESTED_DELIMITERS = {'[', '{', '('};
	
	/**
	 * A mock Delimiter class used to test the abstract Delimiter object's
	 * methods.
	 * 
	 * @author Zachary Radtka
	 */
	public class DelimiterMock extends Delimiter {
		
		public DelimiterMock(Character openingDelimiter,
				Character closingDelimiter) {
			super(openingDelimiter, closingDelimiter);
			
			this.allowedNestedDelimiters.addAll(Arrays.asList(ALLOWED_NESTED_DELIMITERS));
		}
	}
	
	/** The Delimiter object to test */
	DelimiterMock mockDelimiter;

	/**
	 * Ensure that machesOpeningDelimter returns true when the Delimiter
	 * object's closing delimiter is passed to matchesOpeningDelimiter.
	 */
	@Test
	public void validMatchesOpeningDelimiterTest() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Character testClosingDelimiter = closingDelimiter;

		
		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);

		assertTrue(closingDelimiter + " should equal " + testClosingDelimiter,
				mockDelimiter.matchesOpeningDelimiter(testClosingDelimiter));
	}

	/**
	 * Ensure that matchesOpeningDelimiter returns false when the the delimiter
	 * passed to matchesOpeningDelimiter does not match the Delimiter object's
	 * closing delimiter.
	 */
	@Test
	public void invalidMatchesOpeningDelimiterTest() {
		Character openingDelimiter = '[';
		Character closingDelimiter = ']';
		Character testClosingDelimiter = '}';

		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);

		assertFalse(closingDelimiter + " should not equal " + testClosingDelimiter, 
				mockDelimiter.matchesOpeningDelimiter(testClosingDelimiter));
	}

	/**
	 * Ensure a null returns false
	 */
	@Test
	public void nullTest() {
		Character openingDelimiter = '[';
		Character closingDelimiter = ')';

		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);

		assertFalse("null shuold return false",
				mockDelimiter.matchesOpeningDelimiter(null));
	}
	
	/**
	 * Ensure getAllowedNestedDelimiters is being created correctly
	 */
	@Test
	public void getAllowedNestedDelimitersTest() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Set<Character> expected = new HashSet<Character>(Arrays.asList(ALLOWED_NESTED_DELIMITERS));
		
		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		
		assertEquals("Allowed nested delimiters should be {'{','[','('}", 
				expected, mockDelimiter.getAllowedNestedDelimiters());
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void setAllowedNestedDelimitersTest0() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Set<Character> expected = new HashSet<Character>(Arrays.asList(ALLOWED_NESTED_DELIMITERS));

		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		mockDelimiter.setAllowedNestedDelimiters(expected);
		
		assertEquals("Allowed nested delimiters should be {'{','[','('}", 
				expected, mockDelimiter.getAllowedNestedDelimiters());
	}

	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void setAllowedNestedDelimitersTest1() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Set<Character> expected = new HashSet<Character>(Arrays.asList('('));

		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		mockDelimiter.setAllowedNestedDelimiters(expected);
		
		assertEquals("Allowed nested delimiters should be {'('}", 
				expected, mockDelimiter.getAllowedNestedDelimiters());
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void setAllowedNestedDelimitersTest2() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Set<Character> expected = new HashSet<Character>(Arrays.asList('<'));

		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		mockDelimiter.setAllowedNestedDelimiters(expected);
		
		assertTrue("Allowed nested delimiters should be {}", 
				mockDelimiter.getAllowedNestedDelimiters().isEmpty());
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void isAllowedNestedDelimitersTest0() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Character nestedDelimiter = '[';
		
		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		
		assertTrue(nestedDelimiter + " should be allowed", 
				mockDelimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}
	
	/**
	 * Ensure setAllowedNestedDelimiters is functioning correctly
	 */
	@Test
	public void isAllowedNestedDelimitersTest1() {
		Character openingDelimiter = '(';
		Character closingDelimiter = ')';
		Character nestedDelimiter = '<';
		
		mockDelimiter = new DelimiterMock(openingDelimiter, closingDelimiter);
		
		assertFalse(nestedDelimiter + " should not be allowed", 
				mockDelimiter.isAllowedNestedDelimiter(nestedDelimiter));
	}
	
}
