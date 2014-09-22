package com.adtech.util;

import java.util.Stack;

import com.adtech.util.Delimiter;
import com.adtech.util.DelimiterFactory;

/**
 * The {@code WellFormedString} class determines if a string is well formed. The
 * rules that constitute a well formed string are governed by the
 * {@link com.adtech.util.Delimiter Delimiter} class and its concrete
 * implementations.
 * 
 * <p>
 * Currently implemented delimiters:
 * <ul>
 * <li>{@link com.adtech.util.ParenthesisDelimiter ParenthesisDelimiter}</li>
 * <li>{@link com.adtech.util.SquareBraceDelimiter SquareBraceDelimiter}</li>
 * <li>{@link com.adtech.util.CurlyBraceDelimiter CurlyBraceDelimiter}</li>
 * </ul>
 * 
 * @author Zachary Radtka
 */
public class WellFormedString {

	/** The data structure used to hold delimiters */
	private Stack<Delimiter> stack;

	/**
	 * Initializes a newly created {@code WellFormedString} object so that it
	 * represents an empty sequence of characters.
	 */
	public WellFormedString() {
		stack = new Stack<Delimiter>();
	}

	/**
	 * Determines if the specified {@code String} is a well formed string. The
	 * result is {@code true} if the given {@code String} adheres to the rules
	 * of a well formed string.
	 * <p>
	 * Note that {@code isValid()} can be used in succession without having to
	 * re-initialize a {@link com.adtech.util.WellFormedString WellFormedString}
	 * object.
	 * 
	 * @param value
	 *            The {@code String} in question
	 * @return {@code true} if the given {@code String} is a well formed string,
	 *         {@code false} otherwise
	 */
	public Boolean isValid(String value) {

		// Ensure the string is string is not empty
		if (value == null || value.length() == 0) {
			return false;
		}

		// Remove anything previously on the stack
		stack.clear();

		// Determine if the string is valid by processing each character
		for (int i = 0; i < value.length(); i++) {

			// Get the character at the current position
			Character currChar = new Character(value.charAt(i));

			// Create a delimiter object from the current character
			Delimiter currDelimiter = DelimiterFactory.getDelimiter(currChar);

			// If a delimiter object was created, a valid opening delimiter was
			// encountered and may be added to the stack. Otherwise, another
			// character was encountered and needs to be processed.
			if (currDelimiter != null) {
				
				// Determine if the delimiter can be added to the stack
				if (stack.empty()) {
					
					// If no previous delimiters exist add the current delimiter 
					// to the stack.
					stack.push(currDelimiter);
				} else if (stack.peek().isAllowedNestedDelimiter(currChar)) {
					
					// Set the current delimiters allowed nested characters
					// before adding it to the stack
					currDelimiter.setAllowedNestedDelimiters(
							stack.peek().getAllowedNestedDelimiters());
					stack.push(currDelimiter);
				} else {
					
					// The delimiter is not allowed and the string is invalid
					return false;
				}
			} else {

				// A non-opening delimiter was encountered and must be
				// processed.
				if (stack.empty()) {

					// If the stack is empty the number or opening/closing
					// characters is mismatched and the string is invalid
					return false;

				} else if (stack.peek().matchesOpeningDelimiter(currChar)) {

					// If the previous character matches the current closing
					// character, remove it from the stack.
					stack.pop();

				} else {

					// An invalid character was entered.
					return false;
				}
			}
		}

		// After processing all of the characters, if the stack is empty, the
		// string is well formed. Otherwise, the string is not well formed.
		return stack.empty();
	}
}
