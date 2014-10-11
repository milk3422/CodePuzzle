package com.threeSixtyT;



public class Side {
	
	public enum EdgeEnum {
		TOP, BOTTOM, RIGHT, LEFT
	}
	
	private static final int DEFAULT_NUM_ROTATIONS = 3;
	private static final Character DEFAULT_FILLED_CHARACTER = 'o';
	private static final Character DEFAULT_EMPTY_SPACE = ' ';
	
	private int dimension;
	private int numSupportedRotations;
	private boolean flipAllowed;
	
	private Edge top;
	private Edge right;
	private Edge bottom;
	private Edge left;
	
	private Character drawingCharacter;
	
	public Side(String[] side) {
		
		
		// Ensure the dimensions are correct
		int sideLength = side.length;
		
		for (int i=0; i < side.length; i++) {
			if (side[i].length() != sideLength) {
				throw new IllegalArgumentException("Matrix must be nxn");
			}
		}
		
		
		// Get the dimensions for the side
		this.dimension = side.length;
		this.numSupportedRotations = DEFAULT_NUM_ROTATIONS;
		this.flipAllowed = true;
		
		// Initialize the Drawing character
		this.drawingCharacter = DEFAULT_FILLED_CHARACTER;
		
		// Initialize the sides
		StringBuilder edge = new StringBuilder();
		
		
		// Initialize the top side
		this.top = new Edge(side[0]);
		
		// Initialize the right edge
		for (int i=0; i < this.dimension; i++) {
			edge.append(side[i].charAt(this.dimension - 1));
		}
		this.right = new Edge(edge.toString());
		
		// Clear the edge
		edge.setLength(0);
		
		// Populate the bottom edge
		for (int i=0; i < this.dimension; i++) {
			edge.append(side[this.dimension -1].charAt(this.dimension - 1 - i));
		}
		this.bottom = new Edge(edge.toString());
		
		edge.setLength(0);
		
		// Populate the left edge
		for (int i=0; i < this.dimension; i++) {
			edge.append(side[this.dimension - 1 - i].charAt(0));
		}
		this.left = new Edge(edge.toString());

	
		// Determine if the shape is symmetrical
		if (this.isFullySymmetrical()) {
			this.numSupportedRotations = 0;
		} else if (this.isPartiallySymmetrical()) {
			this.numSupportedRotations = 1;
		}
		
		// Determine if the shape contains a reflection
		if (this.containsReflection()) {
			this.flipAllowed = false;
		}
	}
	
	
	public int getDimension() {
		return this.dimension;
	}
	
	public Edge getTopEdge() {
		return new Edge(this.top);
	}
	
	public Edge getRightEdge() {
		return new Edge(this.right);
	}
	
	public Edge getBottomEdge() {
		return new Edge(this.bottom);
	}
	
	public Edge getLeftEdge() {
		return new Edge(this.left);
	}
	
	
	public int getNumSupportedRotations() {
		return this.numSupportedRotations;
	}
	
	public boolean isFlipAllowed() {
		return this.flipAllowed;
	}
	
	public void rotateRight() {	
		// Perform an inplace rotation
		Edge top = this.top;
		
		this.top = this.left;
		this.left = this.bottom;
		this.bottom = this.right;
		this.right = top;
	}
	
	public void rotateLeft() {	
		// Perform an inplace rotation
		Edge top = this.top;
		
		this.top = this.right;
		this.right = this.bottom;
		this.bottom = this.left;
		this.left = top;
	}
	
	
	
	public void flip() {
		// Reverse all of the sides
		this.top.reverse();
		this.right.reverse();
		this.bottom.reverse();
		this.left.reverse();
		
		// Swap the left and right sides
		Edge right = this.right;
		this.right = this.left;
		this.left = right;
	}
	
	
	public boolean isFullySymmetrical() {
		// A symetrical shape has all sides equal
		if (this.top.equals(this.right) 
				&& this.top.equals(this.bottom)
				&& this.top.equals(this.left)) {
			return true;
		}
		return false;
	}

	public boolean isPartiallySymmetrical() {
		// partially symmetrical means 
		if (this.top.equals(this.bottom) 
				&& this.left.equals(this.right)) {
			return true;
		}
		return false;
	}

	
	public boolean containsReflection() {
		
		// Check reflection along the horizontal axis
		if (this.top.equals(this.bottom, true) 
				&& this.left.isSymmetrical()
				&& this.right.isSymmetrical()) {
			return true;
		}
		
		// Check reflection along the vertical axis
		if (this.left.equals(this.right, true) 
				&& this.top.isSymmetrical() 
				&& this.bottom.isSymmetrical()) {
			return true;
		}
		
		return false;
	}
	
	public String[] getSide() {
		String[] side = new String[this.dimension];
		
		Boolean[] topEdge = this.top.getEdge();
		Boolean[] rightEdge = this.right.getEdge();
		Boolean[] bottomEdge = this.bottom.getEdge();
		Boolean[] leftEdge = this.left.getEdge();
		
		StringBuilder edgeString = new StringBuilder();
		
		// fill in the top row
		for (int i=0; i< this.dimension; i++) {
			edgeString.append((topEdge[i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);
		}
		side[0] = edgeString.toString();
		
		// Build everything between the top and bottom
		for (int i=1; i< this.dimension; i++) {
			edgeString.setLength(0);
			for (int j=0; j< this.dimension; j++) {
		
				if (j == 0) {
					edgeString.append((leftEdge[this.dimension - 1 - i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);
				} else if (j == this.dimension -1) {
					edgeString.append((rightEdge[i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);

				} else {
					edgeString.append(DEFAULT_FILLED_CHARACTER);
				}
				
			}
			side[i] = edgeString.toString();
		}
		
		// Fill in the bottom row
		edgeString.setLength(0);
		for (int i=0; i < this.dimension; i++) {
			edgeString.append((bottomEdge[this.dimension - 1 - i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);
		}
		side[this.dimension - 1] = edgeString.toString();
		
		
		return side;
	}
	
	
	public void display() {
		String[] currSide = this.getSide();
		for(int i=0; i< currSide.length; i++) {
			System.out.println(currSide[i]);
		}
	}

}
