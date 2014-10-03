package com.threeSixtyT;


public class Driver {

	public static void main(String[] args) {

		System.out.println("Hello World");
		
		// New Word Tree
		WordTree wt = new WordTree();
		
		wt.put("abtru\"nnig");
		wt.put("Abtru\"nnigkeit");
		wt.put("Abtrunnigkeit");
		wt.put("Abtrunni-gkeit");
		
		String s1 = "abtrunnigkeit";
		
		if (wt.containsPrefix(s1)) {
			System.out.println(s1 + " in Word Tree");
			
			// Print any matching words
			for (String word : wt.get(s1)) {
				System.out.println(word);
			}
			
		} else {
			System.out.println(s1 + " not in Word Tree");
		}
		
	}

}
