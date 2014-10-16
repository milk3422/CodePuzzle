package com.threeSixtyT;

import java.util.Arrays;

/**
 * The Edge is designed to hold an edge of a shape and contains the ability
 * to compare edges of shapes.
 * 
 * @author Zachary Radtka
 */
public class Edge {

	/** The length of the edge */
	private final int length;
	
	/** The edge represented as an array of {@code Boolean} */
	private Boolean[] edge;
	
	/**
	 * Initialize a new Edge by specifying a {@code String[]}. The 
	 * {@code String[]} must use '1' and '0' to represent the parts of the edge
	 * that is present and missing, respectively.
	 * 
	 * @param edge
	 * 			A {@code String} representation of an Edge
	 */
	public Edge(String edge) {
		this.length = edge.length();
		
		this.edge = new Boolean[this.length];
		
		for (int i=0; i < this.length; i++) {
			this.edge[i] = (edge.charAt(i) == '1') ? true: false;
		}
	}
	
	/**
	 * Initialize a new Edge by specifying previously initialized Edge.
	 * 
	 * @param original
	 * 		The {@code Edge} to copy
	 */
	public Edge(Edge original) {
		this.length = original.length;
		this.edge = original.getEdge();
	}
	
	/**
	 * Return a copy of the edge.
	 * 
	 * @return	A {@code Boolean[]} of the edge
	 */
	public Boolean[] getEdge() {
		return Arrays.copyOf(this.edge, this.length);
	}
	
	/**
	 * Returns the length of the edge
	 * 
	 * @return	The length of the edge
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Compares the edge to the specified edge. The result is {@code true} if 
	 * the edge is equal to the specified edge, {@code false} otherwise
	 * 
	 * @param edge
	 * 			The edge to compare this {@code Edge} against
	 * 
	 * @return	{@code true} if the given edge is equal to this edge, 
	 * {@code false} otherwise
	 */
	public boolean equals(Edge edge) {	
		
		// Ensure both edges are the same length
		if (this.length != edge.getLength()) {
			return false;
		}
		
		for (int i=0; i < this.length; i++) {
			if (this.edge[i] ^ edge.getValueAt(i)) {
				return false;
			}
		}
				
		return true;		
	}
	
	/**
	 * Compares the edge to the specified edge. The result is {@code true} if 
	 * the edge is equal to the specified edge, {@code false} otherwise
	 * 
	 * @param edge
	 * 			The edge to compare this {@code Edge} against
	 * @param invert
	 * 			{@code true} if the supplied edge to be compared should be 
	 * 			inverted before being compared, {@code false} if the supplied
	 * 			edge should not be inverted before being compared
	 * 
	 * @return	{@code true} if the given edge is equal to this edge, 
	 * {@code false} otherwise
	 */
	public boolean equals(Edge edge, boolean invert) {
		
		// Create a copy of the edge, reverse it and compare it
		Edge edgeCopy = new Edge(edge);
		edgeCopy.reverse();
		return this.equals(edgeCopy);
	}
	
	/**
	 * Compares the edge to the specified edge. The result is {@code true} if 
	 * the edge matches the specified edge, {@code false} otherwise.
	 * 
	 * @param edge
	 * 			The edge to compare this {@code Edge} against
	 * 
	 * @return	{@code true} if the given edge is equal to this edge, 
	 * 			{@code false} otherwise
	 */
	public boolean matches(Edge edge) {	
		
		// Ensure both edges are the same length
		if (this.length != edge.getLength()) {
			return false;
		}
		
		for (int i=0; i < this.length; i++) {
			if ((this.edge[i] & edge.getValueAt(i))) {
				return false;
			}
		}

		return true;
	}
	
	/**
 	 * Compares the edge to the specified edge. The result is {@code true} if 
	 * the edge matches the specified edge, {@code false} otherwise.
	 * 
	 * @param edge
	 * 			The edge to compare this {@code Edge} against
	 * @param invert
	 * 			{@code true} if the supplied edge to be compared should be 
	 * 			inverted before being compared, {@code false} if the supplied
	 * 			edge should not be inverted before being compared
	 * 
	 * @return	{@code true} if the given edge is equal to this edge, 
	 * 			{@code false} otherwise
	 */
	public boolean matches(Edge edge, boolean invert) {

		// Create a copy of the edge, reverse it and compare it
		Edge edgeCopy = new Edge(edge);
		edgeCopy.reverse();
		return this.matches(edgeCopy);
	}
	
	/**
	 * An in place reversal of the Edge
	 */
	public void reverse() {
		for (int i=0; i < this.length/2; i++) {
			Boolean tmp = this.edge[this.length - (1+i)];
			this.edge[this.length - (1+i)] = this.edge[i];
			this.edge[i] = tmp;
		}
	}
	
	/**
	 * Determines if the Edge is symmetrical. A symmetrical edge is one that 
	 * equal to itself forwards and backwards.
	 * 
	 * @return {@code true} if the edge is symmetrical, {@code false} otherwise
	 */
	public boolean isSymmetrical() {
		for (int i=0; i < this.length/2; i++) {
			if (this.edge[i] ^ this.edge[this.length - (1+i)]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the {@code boolean} value at the specified index.
	 * 
	 * @param index
	 * 			The index of the {@code boolean} value
	 * 
	 * @return	The {@code boolean} value at the specified index of this edge
	 * @exception IndexOutOfBoundsException if the {@code index} argument is
	 * 				negative or is not less than the length of the edge
	 */
	private boolean getValueAt(int index) {
		if ((index < 0) || (index >= this.length)) {
			throw new IndexOutOfBoundsException();
		}
		
		return this.edge[index];
	}
}
