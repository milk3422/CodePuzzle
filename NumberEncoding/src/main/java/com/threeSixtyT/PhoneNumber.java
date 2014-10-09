package com.threeSixtyT;

import java.util.LinkedList;
import java.util.List;

public class PhoneNumber {
	private PhoneNumberDictionary pnd;

	public PhoneNumber(PhoneNumberDictionary dictionary) {
		this.pnd = dictionary;
	}

	public void decode(String number) {
		this.decode(number, number.replaceAll("[^0-9]", ""), 0, 1, true, new LinkedList<String>());
	}

	private void decode(String rawNumber, String encoding, int startPosition, int currPosition,
			boolean prevDigitDecoded, LinkedList<String> decodings) {

		
		List<String> decoding;

		if (startPosition >= encoding.length()) {
			// start is past end of string
			// Display solution
			this.displaySolution(rawNumber, decodings);

		} else if (currPosition >= encoding.length()) {
			
			// If there is a match, there is a solution
			if ( (decoding = this.pnd.get(encoding.substring(startPosition, currPosition))) != null ) {
				
				for (String currDecoding : decoding) {
					// Add solution to decodings
					decodings.add(currDecoding);

					// Display solution
					this.displaySolution(rawNumber, decodings);
					
					// remove the last encoding
					decodings.removeLast();				
				}

			} else if (prevDigitDecoded && encoding.length() > 1) {
				// There is no match either a digit should be inserted or an
				// insert digit at start position
				decodings.add(String.valueOf(encoding.charAt(startPosition)));
				
				// call 
				// Need to add solution 
				this.decode(rawNumber, encoding, startPosition+1, startPosition+2, false, decodings);
				
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
					this.decode(rawNumber, encoding, currPosition, currPosition+1, true, decodings);
					
					// Remove the last encoding
					decodings.removeLast();
				
				}
				
				this.decode(rawNumber, encoding, startPosition, currPosition+1, false, decodings);
				
			} else {
				// Need to add solution 
				this.decode(rawNumber, encoding, startPosition, currPosition+1, prevDigitDecoded, decodings);				
			}
			
		}
		
	}
	
	
	private void displaySolution(String rawNumber, List<String> decodings) {
		System.out.print(rawNumber + ": ");
		
		StringBuilder result = new StringBuilder();
		
		for (String value : decodings) {
			result.append(value);
			result.append(" ");
		}
		
		System.out.println(result.subSequence(0, result.length() - 1));
	}
}
