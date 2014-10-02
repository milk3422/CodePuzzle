package com.threeSixtyT;


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
	
	public boolean contains(String value) {
		
		if (!value.isEmpty()) {
			return this.contains(0, this.rootNode, value);
		}
		
		return false;
	}
	
	public String get(String value) {
		if (!value.isEmpty()) {
			return get(0, this.rootNode, value);
		}
		
		return null;
	}
	
	
	private void put(int currIndex, TreeNode currNode, String value) {
		
//		// Get the current character in lower case form
//		char currChar = value.charAt(currIndex);
//
//		if (currIndex == (value.length() - 1)) {
//			// At the final character need to insert word
//			// check to see if current 
//			currNode.setValue(value);
//			
//		} else if (currIndex < value.length()) {
//			
//			//TODO Check to make sure index is greater than 0
//			insert(++currIndex, currNode.insert(currChar), value);
//		}
		

//		if (currIndex < value.length()) {
//			// Get the current character in lower case form
//			char currChar = value.charAt(currIndex);
//			
//			//TODO Check to make sure index is greater than 0
//			insert(++currIndex, currNode.insert(currChar), value);
//		}

		if (currIndex == value.length()) {
			// If you are at the end of the string insert the value into the final node
			currNode.setValue(value);
		} else	if (currIndex < value.length()) {
			// Get the current character in lower case form
			char currChar = value.charAt(currIndex);
			
			//TODO Check to make sure index is greater than 0
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
	
	
	private String get(int currIndex, TreeNode currNode, String value) {

		
		if (currIndex == value.length()) {
			return currNode.getValue();
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
	
//	public Set<String> keySet() {
//		
//	}
	
	class TreeNode {
		
		private TreeNode[] children;
		
		private String value;
		
		public TreeNode() {
			this.children = new TreeNode[26];
		}

		public boolean contains(char value) {

			// Get the index of the character			
			int index = getCharIndex(value);
			
			if (children[index] != null) {
				return true;
			}
			
			return false;
		}
		
		public TreeNode insert(char value) {
			
			// If a value is not contained add it
			if(!this.contains(value)) {
				this.children[getCharIndex(value)] = new TreeNode();
			}
			
			// Return the TreeNode at the specified location
			return this.children[getCharIndex(value)];
		}
		
		public TreeNode getChild(char value) {
			return this.children[getCharIndex(value)];
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
		/**
		 * Get the value being held at the current node
		 * @return 
		 */
		public String getValue() {
			return this.value;
		}

	}
}
