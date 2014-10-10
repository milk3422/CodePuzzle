package com.threeSixtyT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The NumberEncoding solution convert a sequence of numbers into words based on
 * a dictionary of possible words and a mapping from numbers to characters.
 * <p>
 * <b>To Build</b>
 * <p>
 * NumberEncoding can be built using 'mvn clean install' from within the
 * NumberEncoding project. The NumberEncoding jar file will be in
 * NumberEncoding/target.
 * <p>
 * <b>Usage</b>
 * <p>
 * {@literal java -jar NumberEncoding <dictionaryFile> < <inputFile>}
 * 
 * @author Zachary Radtka
 */
public class Driver {

	/** A mapping of characters to corresponding numbers */
	public static final String[] CHARACTER_MAPPING = { "e", "jnq", "rwx",
			"dsy", "ft", "am", "civ", "bku", "lop", "ghz" };

	public static void main(String[] args) {

		// Define the dictionary and the file used to populate the dictionary
		PhoneNumberDictionary dictionary = null;
		String dictionaryFile;

		// Ensure the application has been called correctly
		if (args.length != 1) {
			System.err.println("Usage: NumberEncoding <dictionaryFile>");
			System.exit(1);
		}

		// Get the dictionary file from the list of arguments
		dictionaryFile = args[0];

		// First populate the dictionary
		try (BufferedReader br = new BufferedReader(new FileReader(
				dictionaryFile))) {

			// Initialize the dictionary
			dictionary = new PhoneNumberDictionary(CHARACTER_MAPPING);

			String currLine;

			// Read all of the lines of the dictionary
			while ((currLine = br.readLine()) != null) {
				dictionary.put(currLine);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: File '" + dictionaryFile
					+ "' could not be found.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Error: Error while reading  '" 
					+ dictionaryFile + "'.");
			System.exit(1);
		}

		// A phone number object used to turn phone numbers into words
		PhoneNumber number = new PhoneNumber(dictionary);

		// Read form STDIN and process each number
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in))) {

			String input;

			// Continue processing until a null character has been reached
			while ((input = br.readLine()) != null) {

				// Display the solution(s) for the current number
				for (String currSolution : number.decode(input)) {
					System.out.println(input + ": " + currSolution);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading from STDIN: " + e.getMessage());
			System.exit(1);
		}
	}

}
