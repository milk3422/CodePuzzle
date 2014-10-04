package com.threeSixtyT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Driver {
	
	public static final String[] NUMBER_MAPPING = {
		"e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", "lop", "ghz"
	};

	public static void main(String[] args) {

//		WordTree dictionary;
//		String dictionaryFile;
//		String numberFile;
//		
//		if (args.length != 3) {
//			System.err.println("Usage: programName <dictionaryFile> <numberFile>");
//			System.exit(1);
//		}
//		
//		dictionaryFile = args[1];
//		numberFile = args[2];
//		
//		
//		// First populate the dictionary
//		try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
//			
//			// Initialize the dictionary
//			dictionary = new WordTree();
//			
//			String currLine;
//			
//			// Read all of the lines of the dictionary 
//			while ((currLine = br.readLine()) != null) {
//				dictionary.put(currLine);
//			}
//		
//		} catch (FileNotFoundException e) {
//			System.out.println("Error: File '" + dictionaryFile + "' could not be found.");
//			System.exit(1);
//		} catch (IOException e) {
//			System.out.println("Error: Error while reading  '" + dictionaryFile + "'.");
//			System.exit(1);
//		}
//		
//		
//		// Read and test the numbers from the number file
//		try (BufferedReader br = new BufferedReader(new FileReader(numberFile))) {
//			
//			String currLine;
//			
//			// Read all of the lines of the dictionary 
//			while ((currLine = br.readLine()) != null) {
//				// Operate on the current number
//
//			}
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("Error: File '" + numberFile + "' could not be found.");
//			System.exit(1);
//		} catch (IOException e) {
//			System.out.println("Error: Error while reading  '" + numberFile + "'.");
//			System.exit(1);
//		}
		
		
		// New Word Tree
		WordTree wt = new WordTree();
		
		// Generate all possible combinations
		String num = "123";
		
		LinkedList<Map.Entry<List<String>, String>> possibleCombinations = new LinkedList<Map.Entry<List<String>, String>>();

		for (int i = 0; i < num.length(); i++) {

	
			generateCombination(possibleCombinations, num.substring(i, i+1), NUMBER_MAPPING, wt);

			
		}
		
		
		
		
//		System.out.println("Hello World");
//		
//		// New Word Tree
//		WordTree wt = new WordTree();
//		
//		wt.put("abtru\"nnig");
//		wt.put("Abtru\"nnigkeit");
//		wt.put("Abtrunnigkeit");
//		wt.put("Abtrunni-gkeit");
//		
//		String s1 = "abtrunnigkeit";
//		
//		if (wt.containsPrefix(s1)) {
//			System.out.println(s1 + " in Word Tree");
//			
//			// Print any matching words
//			for (String word : wt.get(s1)) {
//				System.out.println(word);
//			}
//			
//		} else {
//			System.out.println(s1 + " not in Word Tree");
//		}
		
	}
	
	public static void generateCombination( Set<String> combinations, int num, String[] mapping) {
		// TODO
		System.out.println("Adding combinations for number " + num);
		
		if (combinations.isEmpty()) {
			// If there are no current combinations populate the 
			
			for (int j = 0; j < NUMBER_MAPPING[Integer.valueOf(num)].length(); j++) {
				System.out.println("Adding: " + NUMBER_MAPPING[num].substring(j, j+1));
				combinations.add(NUMBER_MAPPING[num].substring(j, j+1));
			}
		} else {
			// combinations are not empty and they must all be enumerated and added to
			Set<String> newCombinations = new HashSet<String>();
			
			for (String combo: combinations) {

				for (int j = 0; j < NUMBER_MAPPING[Integer.valueOf(num)].length(); j++) {
					
//					System.out.println("Adding: " + NUMBER_MAPPING[num].substring(j, j+1));

					newCombinations.add(combo + NUMBER_MAPPING[num].substring(j, j+1));
				}
			}
			
			combinations.addAll(newCombinations);
			
		}
		
		
	}
	
	public static void generateCombination(
			LinkedList<Map.Entry<List<String>, String>> combinations, 
			String num, 
			String[] mapping, WordTree dictionary) {

		// Hold all of the new possible combinations
		LinkedList<Map.Entry<List<String>, String>> newCombinations = new LinkedList<Map.Entry<List<String>, String>>();
		
		// If the list of combinations is empty it must get populated for the first time
		if (combinations.isEmpty()) {
			
			for(int i = 0; i < mapping[Integer.valueOf(num)].length(); i++) {
				
				// Get the current value
				String currComb = mapping[Integer.valueOf(num)].substring(i, i+1);
				
				if (dictionary.containsPrefix(currComb)) {
					
					// Add the new combination
					newCombinations.add(new AbstractMap.SimpleEntry<List<String>, String>(new LinkedList<String>(), currComb));
					
					// Iterate through the dictionary and insert any word available
					for (String word : dictionary.get(currComb)) {
						// Add the new combination
						List<String> possibleWords = new LinkedList<String>();
						possibleWords.add(word);
						newCombinations.add(new AbstractMap.SimpleEntry<List<String>, String>(possibleWords, ""));
					}
				}
			}
		} else {
			
			
			for (Map.Entry<List<String>, String> currElement : combinations) {
				for (int i = 0; i < mapping[Integer.valueOf(num)].length(); i++) {
					
					// Build the new combination
					String currComb = currElement.getValue() + mapping[Integer.valueOf(num)].substring(i, i+1);
					
					
					if (dictionary.containsPrefix(currComb)) {
						
						// Add the new combination
						newCombinations.add(new AbstractMap.SimpleEntry<List<String>, String>(currElement.getKey(), currComb));
						
						// Iterate through the dictionary and insert any word available
						for (String word : dictionary.get(currComb)) {
							// Add the new combination
							List<String> possibleWords = new LinkedList<String>(currElement.getKey());
							possibleWords.add(word);

							newCombinations.add(new AbstractMap.SimpleEntry<List<String>, String>(possibleWords, ""));
						}
					}
				}

			}
			
			// Clear the old list of possible combinations
			combinations.clear();
			
		}
		
		// Add all of the new possible combinations
		combinations.addAll(newCombinations);
		
		
	}

}
