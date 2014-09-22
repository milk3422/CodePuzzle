package com.adtech.util;

/**
 * 
 * The {@code DelimiterFactory} class enables the creation of concrete delimiter
 * objects.
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
public class DelimiterFactory {

	/**
	 * Create a {@link com.adtech.util.Delimiter Delimiter} object based on a
	 * specified opening delimiter.
	 * 
	 * @param openingDelimiter
	 *            A {@code Character} that represents the opening delimiter of a
	 *            {@link com.adtech.util.Delimiter Delimiter} object to be
	 *            created
	 * @return A {@link com.adtech.util.Delimiter Delimiter} object
	 *         corresponding the the specified opening delimiter. If no
	 *         implementation of {@link com.adtech.util.Delimiter Delimiter} has
	 *         an opening delimiter matching the specified {@code Character},
	 *         null is returned.
	 */
	public static Delimiter getDelimiter(Character openingDelimiter) {
		switch (openingDelimiter) {
			case '(':
				return new ParenthesisDelimiter();
			case '{':
				return new CurlyBraceDelimiter();
			case '[':
				return new SquareBraceDelimiter();
			default:
				return null;
		}
	}

}
