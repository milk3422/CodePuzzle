package com.threeSixtyT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure the {@code PhoneNumber} object is functioning properly.
 * 
 * @author Zachary Radtka
 */
public class PhoneNumberTest {

	/** A mapping of characters to corresponding numbers */
	public static final String[] CHARACTER_MAPPING = { "e", "jnq", "rwx",
			"dsy", "ft", "am", "civ", "bku", "lop", "ghz" };

	/** German words used as a sample dictionary */
	public static final String[] GERMAN_DICTIONARY = { "an", "blau", "Bo\"",
			"Boot", "bo\"s", "da", "Fee", "fern", "Fest", "fort", "je",
			"jemand", "mir", "Mix", "Mixer", "Name", "neu", "o\"d", "Ort",
			"so", "Tor", "Torf", "Wasser" };

	/** A set of sample phone numbers */
	public static final String[] SAMPLE_PHONE_NUMBERS = { "112", "5624-82",
			"4824", "0721/608-4067", "10/783--5", "1078-913-5", "381482",
			"04824" };

	/**
	 * The expected result of the {@link #SAMPLE_PHONE_NUMBERS} ran over the
	 * {@link #GERMAN_DICTIONARY}
	 */
	public static final String[] EXPEXTED_RESULT = { "5624-82: mir Tor",
			"5624-82: Mix Tor", "4824: Torf", "4824: fort", "4824: Tor 4",
			"10/783--5: neu o\"d 5", "10/783--5: je bo\"s 5",
			"10/783--5: je Bo\" da", "381482: so 1 Tor", "04824: 0 Torf",
			"04824: 0 fort", "04824: 0 Tor 4" };

	/** A {@code PhoneNumberDictionary} to hold words */
	public PhoneNumberDictionary dictionary;

	/** A {@code PhoneNumber} that is being tested */
	public PhoneNumber number;

	/**
	 * Initialize the phone number and dictionary before each test
	 */
	@Before
	public void Initialize() {
		dictionary = new PhoneNumberDictionary(CHARACTER_MAPPING);
		number = new PhoneNumber(dictionary);
	}

	/**
	 * Test an empty set is returned when a number cannot be matched
	 */
	@Test
	public void noMatch() {
		assertTrue("A test on an empty dictionary should return an empty set",
				number.decode("5").isEmpty());
	}

	/**
	 * Match a single word in a dictionary with a single word
	 */
	@Test
	public void simpleMatch() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo" };
		String expectedEncoding = "488";

		// Add the words to the expected results
		expected.addAll(Arrays.asList(words));

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("'foo' should be the only word returned", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Match multiple forms of a single word
	 */
	@Test
	public void multipleMatch() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo", "f\"oo", "fo\"o", "\"foo" };
		String expectedEncoding = "488";

		// Add the words to the expected results
		expected.addAll(Arrays.asList(words));

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("Various forms of 'foo', should be retuned", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Match a valid leading digit
	 */
	@Test
	public void validLeadingDigit() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo" };
		String expectedEncoding = "9488";

		// Add the words to the expected results
		expected.add("9 foo");

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("'9 foo' should be returned", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Ensure a digit is not inserted at the first positions, where can be
	 * decoded
	 */
	@Test
	public void invalidLeadingDigit() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo", "ffoo" };
		String expectedEncoding = "4488";

		// Add the words to the expected results
		expected.add("ffoo");

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("'ffoo' should be returned", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Ensure a trailing digit is inserted correctly
	 */
	@Test
	public void validTrailingDigit() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo" };
		String expectedEncoding = "4889";

		// Add the words to the expected results
		expected.add("foo 9");

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("'foo 9' should be returned", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Ensure invalid characters are handled properly
	 */
	@Test
	public void numbersWithInvalidCharacters() {
		Set<String> expected = new HashSet<String>();
		String[] words = { "foo" };
		String expectedEncoding = "/4--8/8";

		// Add the words to the expected results
		expected.add("foo");

		// Populate the dictionary
		for (String word : words) {
			dictionary.put(word);
		}

		assertEquals("Invalid characters should be removed", expected,
				number.decode(expectedEncoding));
	}

	/**
	 * Perform a full test using a German Dictionary
	 */
	@Test
	public void fullTest() {

		// Define the expected and actual sets
		Set<String> actual;
		Set<String> expected;

		// Load the expected results
		expected = new HashSet<String>(Arrays.asList(EXPEXTED_RESULT));

		// Add German words to the dictionary
		for (String currWord : GERMAN_DICTIONARY) {
			dictionary.put(currWord);
		}

		// Initialize the actual results
		actual = new HashSet<String>();

		// Initialize the phone number object
		PhoneNumber number = new PhoneNumber(dictionary);

		// Iterate over the phone numbers, adding the solutions to the set of
		// solutions
		for (String currNumber : SAMPLE_PHONE_NUMBERS) {
			for (String solution : number.decode(currNumber)) {
				actual.add(currNumber + ": " + solution);
			}
		}

		// Compare the actual and expected solutions
		assertEquals(expected, actual);
	}

}
