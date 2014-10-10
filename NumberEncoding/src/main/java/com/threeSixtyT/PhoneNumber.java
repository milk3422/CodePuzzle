package com.threeSixtyT;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The PhoneNumber is designed to convert a sequence of numbers into words
 * based on a dictionary of possible words and a mapping from numbers to 
 * characters. 
 * <p>The PhoneNumber object will produce all of the possible words for a given
 * sequence of numbers.
 * 
 * @author Zachary Radtka
 */
public class PhoneNumber {

	/** The delimiter for the solutions */
	private static final Character DELIMITER = ' ';
	
	/** A dictionary of numbers to words */
	private final PhoneNumberDictionary dictionary;

	/**
	 * Initialize a new PhoneNumber with a corresponding dictionary used to 
	 * determine the possible words for a given phone number.
	 * 
	 * @param dictionary
	 * 			An {@link PhoneNumberDictionary} used to determine possible 
	 * 			words for a given number 
	 */
	public PhoneNumber(PhoneNumberDictionary dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * Determine all of the possible word combinations for a given number.
	 * 
	 * @param number
	 * 		The number to decode
	 * @return
	 * 		The {@code Set} of possible solutions. If more than one word exists 
	 * 		for a solution the words will be space delimited. If no solution 
	 * 		exists, an empty {@code Set} will be returned.
	 */
	public Set<String> decode(String number) {
		Set<String> solutions = new HashSet<String>();
		
		// Ensure the number being passed in is not null
		if (number != null && !number.isEmpty()) {
			this.decode(number.replaceAll("[^0-9]", ""), 0, 1, true, 
					new LinkedList<String>(), solutions);
		}
		
		return solutions;
	}

	/**
	 * A recursive method that will find all of the possible word combinations 
	 * for a given number.
	 * 
	 * @param encoding
	 * 		The encoded number being decoded
	 * @param startPosition
	 * 		The starting position of the substring currently being evaluated
	 * @param currPosition
	 * 		The ending position of the substring currently being evaluated
	 * @param prevDigitDecoded
	 * 		{@code true} if the previous digit was able to be decoded, 
	 * 		{@code false} otherwise
	 * @param currSolution
	 * 		A {@code LinkedList} of a possible decoding
	 * @param solutions
	 * 		A {@code Set} of all of the possible solutions
	 */
	private void decode(String encoding, 
			int startPosition, 
			int currPosition,
			boolean prevDigitDecoded, 
			LinkedList<String> currSolution, 
			Set<String> solutions) {

		// The current possible decoding
		Set<String> decoding;

		// Three possible combinations exist:
		// 1. The start position is past the end of the encoding
		// 2. The current position is past the end of the encoding
		// 3. The first two are not true
		if (startPosition >= encoding.length()) {
			
			// If the start is past the end of the encoding as solution has 
			// been found
			solutions.add(formatSolution(currSolution, DELIMITER));
		} else if (currPosition >= encoding.length()) {
			
			// If there is a match, a solution has been found, otherwise, 
			// determine what to do
			if ( (decoding = this.dictionary.get(encoding.substring(
					startPosition, currPosition))) != null ) {
				
				// Add all of the possible words to the solution
				for (String currDecoding : decoding) {
					currSolution.add(currDecoding);					
					solutions.add(formatSolution(currSolution, DELIMITER));
					currSolution.removeLast();				
				}

			} else if (prevDigitDecoded && encoding.length() > 1) {
				
				// There is no match and the previous digit was decoded, so 
				// it is safe to insert the current digit at the start index
				// into the current possible solution
				currSolution.add(String.valueOf(encoding.charAt(startPosition)));
				
				// Continue solving, updating the positions and making sure that 
				// the prevDigitDecoded is set to false 
				this.decode(encoding, startPosition+1, startPosition+2, false, 
						currSolution, solutions);
				
				// Remove the last encoding from the current solution
				currSolution.removeLast();
			} 
		} else {
			
			// The starting and current position are within range, determine if 
			// a match occurred
			if ( (decoding = this.dictionary.get(encoding.substring(
					startPosition, currPosition))) != null ) {

				// A match occurred, so the current decodings must be processed
				// first
				for (String currDecoding : decoding) {
					currSolution.add(currDecoding);
					this.decode(encoding, currPosition, currPosition+1, true, 
							currSolution, solutions);					
					currSolution.removeLast();
				}
				
				// Next, continue processing as if no match occurred, but 
				// ensure the previousDigitDecoded is set to false so the first
				// digit will not be inserted as a solution
				this.decode(encoding, startPosition, currPosition+1, false, 
						currSolution, solutions);
			} else {
				
				// No match was found, continue processing
				this.decode(encoding, startPosition, currPosition+1, 
						prevDigitDecoded, currSolution, solutions);				
			}
		}
	}
	
	/**
	 * Transform a {@code List} of strings into a single string delimited, by
	 * the provided delimiter.
	 * 
	 * @param solution
	 * 		A {@code List} containing a solution
	 * @param delimiter
	 * 		A {@code Character} delimiter
	 * @return
	 * 		The solution as a {@code String} 
	 */
	private static String formatSolution(List<String> solution, 
			Character delimiter) {
		
		// Initialize an empty StringBuilder
		StringBuilder result = new StringBuilder();
		
		// Insert each word with a space separating the words
		for (String value : solution) {
			result.append(value);
			result.append(delimiter);
		}
		
		// Return the StringBuilder with the last space removed
		return result.subSequence(0, result.length() - 1).toString();
	}

}
