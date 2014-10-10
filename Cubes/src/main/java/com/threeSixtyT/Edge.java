package com.threeSixtyT;

import java.util.Arrays;

public class Edge {

	private final int length;
	private Boolean[] edge;
	
	public Edge(String edge) {
		this.length = edge.length();
		
		this.edge = new Boolean[this.length];
		
		for (int i=0; i < this.length; i++) {
			this.edge[i] = (edge.charAt(i) == '1') ? true: false;
		}
	}
	
	public Edge(Edge edge) {
		this.length = edge.getLength();
		this.edge = edge.getEdge();
	}
	
	
	public Boolean[] getEdge() {
		return Arrays.copyOf(this.edge, this.length);
	}
	
	public int getLength() {
		return this.length;
	}
	
	public boolean equals(Edge edge) {	
		return this.equals(edge, false);
	}
	
	public boolean equals(Edge edge, boolean invert) {
		
		// Ensure they are the same length
		if (this.length != edge.getLength()) {
			return false;
		}
		
		Boolean[] edgeCopy = edge.getEdge();
		
		if (invert) {
			reverse(edgeCopy);
		}
		
		for (int i=0; i < this.length; i++) {
			if (this.edge[i] ^ edgeCopy[i]) {
				return false;
			}
		}
				
		return true;
		
	}
	
	public boolean matches(Edge edge) {	
		return this.matches(edge, false);
	}
	
	public boolean matches(Edge edge, boolean invert) {
		
		// Ensure both edges are the same length
		if (this.length != edge.getLength()) {
			return false;
		}
		
		Boolean[] edgeCopy = edge.getEdge();
		
		if (invert) {
			reverse(edgeCopy);
		}
		
		for (int i=0; i < this.length; i++) {
			if ((this.edge[i] & edgeCopy[i])) {
				return false;
			}
		}

		return true;
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
	
	public boolean isSymmetrical() {
		for (int i=0; i < this.length/2; i++) {
			if (this.edge[i] ^ this.edge[this.length - (1+i)]){
				return false;
			}
		}
		return true;
	}
	
	private static void reverse(Boolean[] array) {
		for (int i=0; i < array.length/2; i++) {
			Boolean tmp = array[array.length - (1+i)];
			array[array.length - (1+i)] = array[i];
			array[i] = tmp;
		}
	}
	
	
	
}
