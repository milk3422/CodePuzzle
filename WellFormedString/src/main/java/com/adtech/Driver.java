package com.adtech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.adtech.util.WellFormedString;

/**
 * The single threaded solution utilizes the
 * {@link com.adtech.util.WellFormedString WellFormedString} object to process
 * strings read form STDIN.
 * <p>
 * The {@link com.adtech.util.WellFormedString WellFormedString} object utilizes 
 * {@link com.adtech.util.Delimiter Delimiter} objects and a
 * {@link java.util.Stack Stack} to determine if the provided string is a well 
 * formed string. The {@link com.adtech.util.Delimiter Delimiter} objects 
 * currently defined are:
 * <ul>
 * <li>{@link com.adtech.util.ParenthesisDelimiter ParenthesisDelimiter}</li>
 * <li>{@link com.adtech.util.SquareBraceDelimiter SquareBraceDelimiter}</li>
 * <li>{@link com.adtech.util.CurlyBraceDelimiter CurlyBraceDelimiter}</li>
 * </ul>
 * <p>
 * This object oriented implementation was chosen because it is extensible and
 * it allows for any number of delimiters to be defined with any rules on valid
 * nested delimiters.
 * <p>
 * Once the {@link com.adtech.util.WellFormedString WellFormedString} object 
 * has analyzed the sting, the result is placed on STDOUT.
 * <p>
 * <b>To Build</b>
 * <p>
 * WellFormedString can be built using 'mvn clean install' from within
 * the AdtechCodePuzzle project. The WellFormedString jar file will be
 * in AdtechCodePuzzle/target.
 * <p>
 * <b>Usage</b>
 * <p>
 * {@literal java -jar WellFormedString < file}
 * 
 * @author Zachary Radtka
 */
public class Driver {

	public static void main(String[] args) {

		// A WellFormedString object used to determine if a string is well
		// formed
		WellFormedString wfs = new WellFormedString();

		// Read form STDIN and add each line to the shared queue
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			// The current line
			String input;

			// Continue processing until a null character has been reached
			while ((input = br.readLine()) != null) {

				// Display the strings and its result
				if (wfs.isValid(input)) {
					System.out.println(input + ":True");
				} else {
					System.out.println(input + ":False");
				}
			}

		} catch (IOException e) {
			System.err.println("Error reading from STDIN: " + e.getMessage());
			System.exit(1);
		}
	}

}
