package com.adtech.util;

/**
 * The {@code ParenthesisDelimiter} class represents the delimiters '<b>(</b>'
 * and '<b>)</b>'. This class is designed to determine if strings are well
 * formed by testing nested delimiters against the list of allowed delimiters.
 * <p>
 * Allowed nested delimiters:
 * <ul>
 * <li>{</li>
 * </ul>
 * 
 * @author Zachary Radtka
 */
public final class ParenthesisDelimiter extends Delimiter {

	/** The value of the opening delimiter */
	private static final Character OPENING_DELIMITER = '(';

	/** The value of the closing delimiter */
	private static final Character CLOSING_DELIMITER = ')';

	/** The allowed nested delimiter */
	private static final Character ALLOWED_NESTED_DELIMITER = '{';

	/**
	 * Initializes a newly created {@code ParenthesisDelimiter} with an opening
	 * delimiter of '<b>(</b>' and a closing delimiter of '<b>)</b>'.
	 */
	public ParenthesisDelimiter() {
		super(OPENING_DELIMITER, CLOSING_DELIMITER);
		
		// Add the allowed nested delimiter
		allowedNestedDelimiters.add(ALLOWED_NESTED_DELIMITER);
	}

}
