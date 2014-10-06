package com.threeSixtyT;

import java.util.Arrays;


public class Side {
	
	public enum Edge {
		TOP, BOTTOM, RIGHT, LEFT
	}
	
	private Boolean[][] side;
	private int dimension;
	
	
	public Side(String[] side) {
		this.dimension = side.length;
		
		// Initialize the side
		this.side = new Boolean[this.dimension][this.dimension];

		// Copy the passed in array to this array
		for (int x=0; x < this.dimension; x++) {
			for (int y=0; y < side[x].length(); y++) {
				this.side[x][y] = (side[x].charAt(y) == '1') ? true: false;
			}
		}
	}
	

	public void display() {
		
		// Print the values
		for (int x=0; x < this.dimension; x++) {
			for (int y=0; y < this.dimension; y++) {
				System.out.print((this.side[x][y]) ? "o" : " ");
			}
			System.out.print("\n");
		}
	}
	
	
	public void rotateRight() {
		
		// Initialize the side
		Boolean[][] newSide = new Boolean[this.dimension][this.dimension];
		
		// Print the values
		for (int x=0; x < this.dimension; x++) {
			for (int y=0; y < this.dimension; y++) {
				newSide[x][y] = this.side[this.dimension - y -1][x];				
			}
		}
		
		// Copy new side into this side
		for (int x=0; x < this.dimension; x++) {
			for (int y=0; y < this.dimension; y++) {
				this.side[x][y] = newSide[x][y];				
			}
		}
	}
	

	
	
	public boolean matchesEdge(Edge edgeType, Boolean[] edgeToCompare) {
		Boolean[] thisSideEdge = this.getEdge(edgeType);
		
		for (int i=0; i< this.dimension; i++) {
			if (thisSideEdge[i] & edgeToCompare[i]) {
				return false;
			}
		}

		return true;		
	}
	
	public boolean matchesEdge(Edge edgeType, Boolean[] edgeToCompare, Boolean inverted) {
		Boolean[] thisSideEdge = this.getEdge(edgeType);
		
		if (inverted) {
			for (int i=0; i < this.dimension/2; i++) {
				Boolean tmp = thisSideEdge[this.dimension - (1+i)];
				thisSideEdge[this.dimension - (1+i)] = thisSideEdge[i];
				thisSideEdge[i] = tmp;
			}
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
}
