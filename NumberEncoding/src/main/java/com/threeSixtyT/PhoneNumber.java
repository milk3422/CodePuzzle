package com.threeSixtyT;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PhoneNumber {
	private PhoneNumberDictionary pnd;

	public PhoneNumber(PhoneNumberDictionary dictionary) {
		this.pnd = dictionary;
	}

	public Set<String> decode(String number) {
		Set<String> solutions = new HashSet<String>();
		
		
		this.decode(number.replaceAll("[^0-9]", ""), 0, 1, true, new LinkedList<String>(), solutions);
		
		return solutions;
	}

	private void decode(String encoding, int startPosition, int currPosition,
			boolean prevDigitDecoded, LinkedList<String> decodings, Set<String> solutions) {

		
		Set<String> decoding;

		if (startPosition >= encoding.length()) {
			// start is past end of string
			// Display solution
			solutions.add(formatSolution(decodings));

		} else if (currPosition >= encoding.length()) {
			
			// If there is a match, there is a solution
			if ( (decoding = this.pnd.get(encoding.substring(startPosition, currPosition))) != null ) {
				
				for (String currDecoding : decoding) {
					// Add solution to decodings
					decodings.add(currDecoding);

					
					solutions.add(formatSolution(decodings));

					
					// remove the last encoding
					decodings.removeLast();				
				}

			} else if (prevDigitDecoded && encoding.length() > 1) {
				// There is no match either a digit should be inserted or an
				// insert digit at start position
				decodings.add(String.valueOf(encoding.charAt(startPosition)));
				
				// call 
				// Need to add solution 
				this.decode(encoding, startPosition+1, startPosition+2, false, decodings, solutions);
				
				// Remove the last encoding
				decodings.removeLast();

			} 
			
		} else {
			
			// Determine if a match occurred
			if ( (decoding = this.pnd.get(encoding.substring(startPosition, currPosition))) != null ) {

				for (String currDecoding : decoding) {
					// Add solution to decodings
					decodings.add(currDecoding);

					// 
					this.decode(encoding, currPosition, currPosition+1, true, decodings, solutions);
					
					// Remove the last encoding
					decodings.removeLast();
				
				}
				
				this.decode(encoding, startPosition, currPosition+1, false, decodings, solutions);
				
			} else {
				// Need to add solution 
				this.decode(encoding, startPosition, currPosition+1, prevDigitDecoded, decodings, solutions);				
			}
			
		}
		
	}
	
	private static String formatSolution(List<String> solution) {
		StringBuilder result = new StringBuilder();
		
		for (String value : solution) {
			result.append(value);
			result.append(" ");
		}
		
		return result.subSequence(0, result.length() - 1).toString();
	}

}
