package com.threeSixtyT;

import java.util.LinkedList;
import java.util.List;

public class PhoneNumber {
	private PhoneNumberDictionary pnd;

	public PhoneNumber(PhoneNumberDictionary dictionary) {
		this.pnd = dictionary;
	}

	public void decode(String number) {
		this.decode(number, 0, 1, true, new LinkedList<String>());
	}

	private void decode(String encoding, int startPosition, int currPosition,
			boolean prevDigitDecoded, List<String> decodings) {

		
		List<String> decoding;

		if (startPosition >= encoding.length()) {
			// start is past end of string
			// Display solution
			this.displaySolution(encoding, decodings);

		} else if (currPosition >= encoding.length()) {
			
			// If there is a match, there is a solution
			if ( (decoding = this.pnd.get(encoding.substring(startPosition, currPosition))) != null ) {
				
				// Add solution to decodings
				decodings.add(decoding.get(0));

				// Display solution
				this.displaySolution(encoding, decodings);
				
				// remove encodings
				decodings.remove(decoding.get(0));

			} else if (prevDigitDecoded) {
				// There is no match either a digit should be inserted or an
				// insert digit at start position
				decodings.add(String.valueOf(encoding.charAt(startPosition)));
				
				// call 
				// Need to add solution 
				this.decode(encoding, startPosition+1, startPosition+2, false, decodings);
				
				decodings.remove(String.valueOf(encoding.charAt(startPosition)));

			} 
			
		} else {
			
			// Determine if a match occurred
			if ( (decoding = this.pnd.get(encoding.substring(startPosition, currPosition))) != null ) {
//				decodings.add(decoding.get(0));
//				this.decode(encoding, startPosition+1, startPosition+2, true, decodings);
//				
//				decodings.remove(decoding.get(0));
//				
//				this.decode(encoding, startPosition, currPosition+1, false, decodings);
				
				decodings.add(decoding.get(0));
				this.decode(encoding, currPosition, currPosition+1, true, decodings);
				
				decodings.remove(decoding.get(0));
				
				this.decode(encoding, startPosition, currPosition+1, false, decodings);
				

			} else {
				// Need to add solution 
				this.decode(encoding, startPosition, currPosition+1, prevDigitDecoded, decodings);				
			}
			
		}
		
	}
	
	private void displaySolution(String encoding, List<String> decodings) {
		System.out.print("encoding: ");
		
		StringBuilder result = new StringBuilder();
		
		for (String value : decodings) {
			result.append(value);
			result.append(" ");
		}
		
		System.out.println(result.subSequence(0, result.length() - 1));
		
	}
}
