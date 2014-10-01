package com.threeSixtyT;

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
	
	public void add(String value) {
		
		if (!value.isEmpty()) {
			this.insert(0, this.rootNode, value);	
		}
	}
	
	public boolean contains(String value) {
		
		if (!value.isEmpty()) {
			return this.exists(0, this.rootNode, value);
		}
		
		return false;
	}
	
	
	private void insert(int currIndex, TreeNode currNode, String value) {
		
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
		

		if (currIndex < value.length()) {
			// Get the current character in lower case form
			char currChar = value.charAt(currIndex);
			
			//TODO Check to make sure index is greater than 0
			insert(++currIndex, currNode.insert(currChar), value);
		}
		
	}
	
	private boolean exists(int currIndex, TreeNode currNode, String value) {
		
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
	
//	public Set<String> keySet() {
//		
//	}
	
	class TreeNode {
		private boolean hasChildren;
		
		private TreeNode[] children;
//		private char value;
		
		private String value;
		
		public TreeNode() {
			this.hasChildren = false;
			this.children = new TreeNode[26];
		}
		
//		public TreeNode(char value) {
//			this();
//			this.value = value;
//		}
		
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

	}
}
