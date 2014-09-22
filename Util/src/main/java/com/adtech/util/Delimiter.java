package com.adtech.util;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Delimiter} is designed to determine if strings are well formed by
 * testing nested delimiters against the list of allowed delimiters.
 * <p>
 * Implementing subclasses should override the default constructor and add 
 * {@code Character}s to the {@link #allowedNestedDelimiters} {@code set}.
 * 
 * @author Zachary Radtka
 */
public abstract class Delimiter {

	/** The value of the opening delimiter */
	protected final Character openingDelimiter;

	/** The value of the closing delimiter */
	protected final Character closingDelimiter;

	/** A set of allowed nested delimiters */
	protected final Set<Character> allowedNestedDelimiters;
	
	/**
	 * Initializes a newly created {@code Delimiter} object.
	 * 
	 * @param openingDelimiter
	 *            A {@code Character} representing a valid opening delimiter
	 * @param closingDelimiter
	 *            A {@code Character} representing a valid closing delimiter
	 */
	protected Delimiter(Character openingDelimiter, Character closingDelimiter) {
		this.openingDelimiter = openingDelimiter;
		this.closingDelimiter = closingDelimiter;
		this.allowedNestedDelimiters = new HashSet<Character>();
	}

	/**
	 * Determines if this opening delimiter matches the the specified closing
	 * delimiter. The result is {@code true} if and only if the argument is not
	 * {@code null} and is the valid closing delimiter {@code Character} for
	 * this opening delimiter.
	 * 
	 * @param closingDelimiter
	 *            A {@code Character} to compare against an opening delimiter
	 * @return {@code true} if the given {@code Character} is the valid closing
	 *         delimiter, {@code false} otherwise
	 */
	public Boolean matchesOpeningDelimiter(Character closingDelimiter) {
		if (closingDelimiter != null
				&& closingDelimiter.equals(this.closingDelimiter)) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the allowed nested delimiters for the current Delimiter object.
	 * <p>
	 * This operation effectively performs an intersection between the existing
	 * {@code Set} of allowedNestedDelimiters and the provided {@code Set} of 
	 * allowedNestedDelimiters.
	 * 
	 * @param allowedNestedDelimiters
	 * 			A {@code Set<Character>} of allowed nested delimiters
	 */
	public void setAllowedNestedDelimiters(Set<Character> allowedNestedDelimiters) {
		// Perform an intersection with the current set of allowed delimiters
		this.allowedNestedDelimiters.retainAll(allowedNestedDelimiters);
	}
	
	/**
	 * Determines if the specified {@code Character} is a valid nested
	 * delimiter. The result is {@code true} if and only if the argument is a
	 * valid nested delimiter {@code Character}. Otherwise, the result is
	 * {@code false}.
	 * 
	 * @param nestedDelimiter
	 *            A {@code Character} to compare against allowed nested
	 *            delimiters
	 * @return {@code true} if the given {@code Character} is a valid nested
	 *         delimiter, {@code false} otherwise
	 */
	public Boolean isAllowedNestedDelimiter(Character nestedDelimiter) {
		if (nestedDelimiter != null 
				&& this.allowedNestedDelimiters.contains(nestedDelimiter)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns a {@code Set<Characters>} that contains valid nested delimiters.
	 * @return
	 * 		A {@code Set<Character>} representing allowed delimiters
	 */
	public Set<Character> getAllowedNestedDelimiters() {
		// Create a copy, don't share the internal structure
		return new HashSet<Character>(this.allowedNestedDelimiters);
	}
	
}
