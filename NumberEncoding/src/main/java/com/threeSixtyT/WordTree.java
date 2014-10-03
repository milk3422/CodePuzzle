package com.threeSixtyT;

import java.util.HashSet;
import java.util.Set;


public class WordTree {

	private TreeNode rootNode;
	private boolean empty;
	
	public WordTree() {
		this.rootNode = new TreeNode();
		this.empty = true;
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public void put(String value) {
		
		if (!value.isEmpty()) {
			this.put(0, this.rootNode, value);	
		}
	}
	
	public boolean containsPrefix(String value) {
		
		if (!value.isEmpty()) {
			return this.contains(0, this.rootNode, value);
		}
		
		return false;
	}
	

	
	public Set<String> get(String value) {
		if (!value.isEmpty()) {
			return get(0, this.rootNode, value);
		}
		
		return null;
	}
	
	
	private void put(int currIndex, TreeNode currNode, String value) {

		if (currIndex == value.length()) {
			// If you are at the end of the string insert the value into the final node
			currNode.setValue(value);
		} else	if (currIndex < value.length()) {
			// Get the current character in lower case form
			char currChar = value.charAt(currIndex);
			
			put(++currIndex, currNode.insert(currChar), value);
		}

	}
	
	private boolean contains(int currIndex, TreeNode currNode, String value) {
		
		char currChar = value.charAt(currIndex);
		
		if (currIndex == (value.length() - 1)) {
			return currNode.contains(currChar);
		} else if (currIndex < value.length() && currNode.getChild(currChar) != null) {
			
			return contains(++currIndex, currNode.getChild(currChar), value);

		}
		
		return false;
	}
	
	
	private Set<String> get(int currIndex, TreeNode currNode, String value) {

		if (currIndex == value.length()) {
			return currNode.getValues();
		} else if (currIndex < value.length()) {
			
			char currChar = value.charAt(currIndex);
			
			if (currNode.getChild(currChar) != null) {
				
				return get(++currIndex, currNode.getChild(currChar), value);
			}
		}
		
		return null;
	}
	
	public static int getCharIndex(char value) {
		// Get the current character in lower case form
		char currChar = Character.toLowerCase(value);
					
		// Get the index of the character
		return currChar - 97;
	}
	
	public static boolean isValidChar(char value) {
		int index = getCharIndex(value);
		
		// Make sure the index is within range
		if (index >=0 && index <26) {
			return true;
		}

		return false;
	}
	

	
	class TreeNode {
		
		private TreeNode[] children;
		
		private String value;
		
		private Set<String> values;
		
		public TreeNode() {
			this.children = new TreeNode[26];
		}

		public boolean contains(char value) {

			// Get the index of the character			
			int index = getCharIndex(value);
			
			// Make sure the index is within range
			if (isValidChar(value) && children[index] != null) {
				return true;
			}
			
			return false;
		}
		
		public TreeNode insert(char value) {
			
			if (!isValidChar(value)) {
				return this;
			} else if(!this.contains(value)) {
				this.children[getCharIndex(value)] = new TreeNode();
			}
			
			// Return the TreeNode at the specified location
			return this.children[getCharIndex(value)];
		}
		
		public TreeNode getChild(char value) {
			return this.children[getCharIndex(value)];
		}
		
		public void setValue(String value) {
			
			if (this.values == null) {
				this.values = new HashSet<String>();
			}
			
			this.values.add(value);
			
//			this.value = value;
		}
		
		/**
		 * Get the value being held at the current node
		 * @return 
		 */
		public String getValue() {
			return this.value;
		}
		
		// return a copy so it can't be altered
		public Set<String> getValues() {
			if (this.values == null) {
				// Return an empty set if no value exists
				return new HashSet<String>();
			}
			return new HashSet<String>(this.values);
		}

	}
}
