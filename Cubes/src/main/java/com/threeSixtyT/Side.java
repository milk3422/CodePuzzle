package com.threeSixtyT;

import java.util.Arrays;


public class Side {
	
	public enum Edge {
		TOP, BOTTOM, RIGHT, LEFT
	}
	
	private static final int DEFAULT_NUM_ROTATIONS = 3;
	
	private Boolean[][] side;
	private int dimension;
	private int numSupportedRotations;
	private boolean flipAllowed;
	
	
	public Side(String[] side) {
		

		// Get the dimensions for the side
		this.dimension = side.length;
		this.numSupportedRotations = DEFAULT_NUM_ROTATIONS;
		this.flipAllowed = true;
		
		// Initialize the side
		this.side = new Boolean[this.dimension][this.dimension];

		// Copy the passed in array to this array
		for (int y=0; y < this.dimension; y++) {
			for (int x=0; x < side[y].length(); x++) {
				this.side[y][x] = (side[y].charAt(x) == '1') ? true: false;
			}
		}
		
		// Determine if the shape has a reflection to limit the number of rotations
		if (this.edgeEquals(Edge.TOP, this.getEdge(Edge.RIGHT)) 
				&& this.equalsEdge(Edge.RIGHT, this.getEdge(Edge.BOTTOM), true)
				&& this.edgeEquals(Edge.BOTTOM, this.getEdge(Edge.LEFT)) 
				&& this.equalsEdge(Edge.LEFT, this.getEdge(Edge.TOP), true)) {
			this.numSupportedRotations = 0;
		} else if (this.equalsEdge(Edge.TOP, this.getEdge(Edge.BOTTOM), true) 
				&& this.equalsEdge(Edge.LEFT, this.getEdge(Edge.RIGHT), true)) {
			// if top == bottom and left == right
			
			this.numSupportedRotations = 1;
		}
		
		if (this.containsHorizontalReflection() || this.containsVerticalReflection()) {
			this.flipAllowed = false;
		}

	}
	
	
	public int getDimension() {
		return this.dimension;
	}

	public String getRow(int rowNum) {
		
		StringBuilder row = new StringBuilder();

		for (int x=0; x < this.dimension; x++) {
			row.append(this.side[rowNum][x] ? "o" : " ");
		}

		return row.toString();
	}
	
	public void display() {
		
		// Print the values
		for (int y=0; y < this.dimension; y++) {
			for (int x=0; x < this.dimension; x++) {
				System.out.print((this.side[y][x]) ? "o" : " ");
			}
			System.out.print("\n");
		}
	}
	
	
	public void rotateRight() {
		
		// Initialize the side
		Boolean[][] rotatedEdge = new Boolean[this.dimension][this.dimension];
		
		// Print the values
		for (int y=0; y < this.dimension; y++) {
			for (int x=0; x < this.dimension; x++) {
				rotatedEdge[y][x] = this.side[this.dimension - x -1][y];				
			}
		}
		
		// Copy new side into this side
		for (int y=0; y < this.dimension; y++) {
			for (int x=0; x < this.dimension; x++) {
				this.side[y][x] = rotatedEdge[y][x];				
			}
		}
	}
	
	
	public void flip() {

		// Invert the value
		for (int x=0; x < this.dimension; x++) {
			reverseEdge(this.side[x]);
		}
	}
	
	
	
	public int getNumSupportedRotations() {
		return this.numSupportedRotations;
	}

	public boolean isFlipAllowed() {
		return this.flipAllowed;
	}
	
	public boolean edgeEquals(Edge edgeType, Boolean[] edgeToCompare) {
		return this.equalsEdge(edgeType, edgeToCompare, false);
	}
	
	
	public boolean equalsEdge(Edge edgeType, Boolean[] edgeToCompare, boolean invert) {
		
		Boolean[] thisSideEdge = this.getEdge(edgeType);
		
		if (invert) {
			thisSideEdge = reverseEdge(thisSideEdge);
		}
		
		for (int i=0; i< this.dimension; i++) {
			if (thisSideEdge[i] ^ edgeToCompare[i]) {
				return false;
			}
		}

		return true;		
	}


	public boolean matches(Edge edgeType, Boolean[] edgeToCompare) {
		return this.matches(edgeType, edgeToCompare, false);
	}
	
	public boolean matches(Edge edgeType, Boolean[] edgeToCompare, Boolean invert) {
		Boolean[] thisSideEdge = this.getEdge(edgeType);
		
		if (invert) {
			thisSideEdge = reverseEdge(thisSideEdge);
		}
		
		for (int i=0; i< this.dimension; i++) {
			if (thisSideEdge[i] & edgeToCompare[i]) {
				return false;
			}
		}

		return true;		
	}
	
	

	public Boolean[] getEdge(Edge edgeType) {
		
		switch (edgeType) {
			case TOP:
				return Arrays.copyOf(this.side[0], this.dimension);
			case BOTTOM:
				return Arrays.copyOf(this.side[this.dimension - 1], this.dimension);
			case RIGHT:
				Boolean[] rightEdge = new Boolean[this.dimension];
				for (int i=0; i < this.dimension; i++) {
					rightEdge[i] = this.side[i][dimension-1];
				}
				return rightEdge;
			case LEFT:
				Boolean[] leftEdge = new Boolean[this.dimension];
				for (int i=0; i < this.dimension; i++) {
					leftEdge[i] = this.side[i][0];
				}
				return leftEdge;
			default:
				return new Boolean[0];
		
		}
	}
	
	private Boolean[] reverseEdge(Boolean[] edge) {

		for (int i=0; i < edge.length/2; i++) {
			Boolean tmp = edge[edge.length - (1+i)];
			edge[edge.length - (1+i)] = edge[i];
			edge[i] = tmp;
		}
		
		return edge;		
	}
	
	
	private boolean containsVerticalReflection() {
		
		// Check for a reflection on the vertical axis
		for (int y=0; y < this.dimension/2; y++) {
			for (int x=0; x < this.dimension; x++) {
				if (this.side[y][x] ^ this.side[y][this.dimension - (x+1)]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	private boolean containsHorizontalReflection() {
		
		// Check for a reflection on the vertical axis
		for (int x=0; x < this.dimension/2; x++) {
			for (int y=0; y < this.dimension; y++) {
				if (this.side[y][x] ^ this.side[this.dimension - (y+1)][x]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}