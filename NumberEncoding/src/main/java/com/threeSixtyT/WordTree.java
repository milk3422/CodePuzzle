package com.threeSixtyT;

public class WordTree {

	private TreeNode rootNode;
	private boolean empty;
	
	public WordTree() {
		this.rootNode = new TreeNode();
		this.empty = true;
	}
	
	public Boolean isEmpty() {
		return this.empty;
	}
	
	public void add(String value) {
		
		if (!value.isEmpty()) {
			this.insert(value.charAt(0), this.rootNode, value);	
		}
	}
	
	public Boolean contains(String value) {
		if (!value.isEmpty()) {
			return this.exists(value.charAt(0), this.rootNode, value);
		}
		
		return false;
	}
	
	
	private void insert(int currIndex, TreeNode currNode, String value) {
		
		if (currIndex < value.length()) {
			// Get the current character in lower case form
			char currChar = value.charAt(currIndex);

			//TODO Check to make sure index is greater than 0
			insert(++currIndex, currNode.insert(currChar), value);
		}
	}
	
	private Boolean exists(int currIndex, TreeNode currNode, String value) {
		
		char currChar = value.charAt(currIndex);
		
		if (currIndex == (value.length() - 1)) {
			return currNode.contains(currChar);
		} else if (currIndex < value.length() && currNode.getChild(currChar) != null) {
			
			return exists(++currIndex, currNode.getChild(currChar), value);

		}
		
		return false;
	}
	
	
	public static int getCharIndex(char value) {
		// Get the current character in lower case form
		char currChar = Character.toLowerCase(value);
					
		// Get the index of the character
		return currChar - 97;
	}
	
	
	class TreeNode {
		private boolean hasChildren;
		
		private TreeNode[] children;
		private char value;
		
		public TreeNode() {
			this.hasChildren = false;
			this.children = new TreeNode[26];
		}
		
		public TreeNode(char value) {
			this();
			this.value = value;
		}
		
		public Boolean contains(char value) {

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
				this.children[getCharIndex(value)] = new TreeNode(value);
			}
			
			// Return the TreeNode at the specified location
			return this.children[getCharIndex(value)];
		}
		
		public TreeNode getChild(char value) {
			return this.children[getCharIndex(value)];
		}

	}
}
