package com.adtech.util;

/**
 * The {@code CurlyBraceDelimiter} class represents the delimiters '<b>{</b>'
 * and '<b>}</b>'. This class is designed to determine if strings are well
 * formed by testing nested delimiters against the list of allowed delimiters.
 * <p>
 * Allowed nested delimiters:
 * <ul>
 * <li>[</li>
 * </ul>
 * 
 * @author Zachary Radtka
 */
public final class CurlyBraceDelimiter extends Delimiter {

	/** The value of the opening delimiter */
	private static final Character OPENING_DELIMITER = '{';

	/** The value of the closing delimiter */
	private static final Character CLOSING_DELIMITER = '}';

	/** The allowed nested delimiter */
	private static final Character ALLOWED_NESTED_DELIMITER = '[';

	/**
	 * Initializes a newly created {@code CurlyBraceDelimiter} with an opening
	 * delimiter of '<b>{</b>' and a closing delimiter of '<b>}</b>'.
	 */
	public CurlyBraceDelimiter() {
		super(OPENING_DELIMITER, CLOSING_DELIMITER);
		
		allowedNestedDelimiters.add(ALLOWED_NESTED_DELIMITER);
	}

}
