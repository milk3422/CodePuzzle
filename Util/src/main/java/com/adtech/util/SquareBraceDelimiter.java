package com.adtech.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code SquareBraceDelimiter} class represents the delimiters '<b>[</b>'
 * and '<b>]</b>'. This class is designed to determine if strings are well
 * formed by testing nested delimiters against the list of allowed delimiters.
 * <p>
 * All characters are allowed nested delimiters.
 * 
 * @author Zachary Radtka
 */
public final class SquareBraceDelimiter extends Delimiter {

	/** The value of the opening delimiter */
	private static final Character OPENING_DELIMITER = '[';

	/** The value of the closing delimiter */
	private static final Character CLOSING_DELIMITER = ']';
	
	/** An array of allowed nested delimiters */
	private static final Character[] ALLOWED_NESTED_DELIMITERS = {'[', '{', '('};
	
	/** A  set of the allowed nested delimiters */
	private final Set<Character> directAllowedCharacters;

	/**
	 * Initializes a newly created {@code SquareBraceDelimiter} with an opening
	 * delimiter of '<b>[</b>' and a closing delimiter of '<b>]</b>'.
	 */
	public SquareBraceDelimiter() {
		super(OPENING_DELIMITER, CLOSING_DELIMITER);
		this.allowedNestedDelimiters.addAll(Arrays.asList(ALLOWED_NESTED_DELIMITERS));
		this.directAllowedCharacters = new HashSet<Character>(Arrays.asList(ALLOWED_NESTED_DELIMITERS));
	}

	@Override
	public Boolean isAllowedNestedDelimiter(Character nestedDelimiter) {
		if( nestedDelimiter != null 
				&& this.directAllowedCharacters.contains(nestedDelimiter)) {
			return true;
		}
		return false;
	}
	
}
