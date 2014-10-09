package com.threeSixtyT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Driver {
	
	public static final String[] CHARACTER_MAPPING = {
		"e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", "lop", "ghz"
	};

	public static void main(String[] args) {
	
		PhoneNumberDictionary dictionary = null;
		String dictionaryFile;
		
		if (args.length != 1) {
			System.err.println("Usage: programName <dictionaryFile>");
			System.exit(1);
		}
		
		dictionaryFile = args[0];
	
		// First populate the dictionary
		try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
			
			// Initialize the dictionary
			dictionary = new PhoneNumberDictionary(CHARACTER_MAPPING);
			
			String currLine;
			
			// Read all of the lines of the dictionary 
			while ((currLine = br.readLine()) != null) {
				dictionary.put(currLine);
			}
		
		} catch (FileNotFoundException e) {
			System.out.println("Error: File '" + dictionaryFile + "' could not be found.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Error: Error while reading  '" + dictionaryFile + "'.");
			System.exit(1);
		}

		
		// A phone number object used to turn phone numbers into words
		PhoneNumber number = new PhoneNumber(dictionary);

		// Read form STDIN and add each line to the shared queue
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			// The current line
			String input;

			// Continue processing until a null character has been reached
			while ((input = br.readLine()) != null) {

				
				for (String currSolution: number.decode(input)) {
					System.out.println(input + ": " + currSolution);
				}
			}

		} catch (IOException e) {
			System.err.println("Error reading from STDIN: " + e.getMessage());
			System.exit(1);
		}

	}

}
