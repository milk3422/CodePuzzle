package com.threeSixtyT;



public class Side {
	
	public enum EdgeEnum {
		TOP, BOTTOM, RIGHT, LEFT
	}
	
	private static final int DEFAULT_NUM_ROTATIONS = 3;
	private static final Character DEFAULT_FILLED_CHARACTER = 'o';
	private static final Character DEFAULT_EMPTY_SPACE = ' ';
	
	
//	private Boolean[][] side;
//	private int dimension;
//	private int numSupportedRotations;
//	private boolean flipAllowed;
	
	private int dimension;
	private int numSupportedRotations;
	private boolean flipAllowed;
	
	private Edge top;
	private Edge right;
	private Edge bottom;
	private Edge left;
	
	private Character drawingCharacter;
	
//	public Side(String[] side) {
//		
//		
//		// Ensure the dimensions are correct
//		int sideLength = side.length;
//		
//		for (int i=0; i < side.length; i++) {
//			if (side[i].length() != sideLength) {
//				throw new IllegalArgumentException("Matrix must be nxn");
//			}
//		}
//		
//		
//		// Get the dimensions for the side
//		this.dimension = side.length;
//		this.numSupportedRotations = DEFAULT_NUM_ROTATIONS;
//		this.flipAllowed = true;
//		
//		// Initialize the side
//		this.side = new Boolean[this.dimension][this.dimension];
//
//		// Copy the passed in array to this array
//		for (int y=0; y < this.dimension; y++) {
//			for (int x=0; x < side[y].length(); x++) {
//				this.side[y][x] = (side[y].charAt(x) == '1') ? true: false;
//			}
//		}
//		
//		// Initialize the Drawing character
//		this.drawingCharacter = DEFAULT_FILLED_CHARACTER;
//		
//		// Initialize the sides
//
//		
//		StringBuilder edge = new StringBuilder();
//		
//		
//		// Initialize the top side
//		this.top = new Edge(side[0]);
//		
//		// Initialize the right edge
//		for (int i=0; i < this.dimension; i++) {
//			edge.append(side[i].charAt(this.dimension - 1));
//		}
//		this.right = new Edge(edge.toString());
//		
//		// Clear the edge
//		edge.setLength(0);
//		
//		// Populate the bottom edge
//		for (int i=0; i < this.dimension; i++) {
//			edge.append(side[this.dimension -1].charAt(this.dimension - 1 - i));
//		}
//		this.bottom = new Edge(edge.toString());
//		
//		edge.setLength(0);
//		
//		// Populate the left edge
//		for (int i=0; i < this.dimension; i++) {
//			edge.append(side[this.dimension - 1 - i].charAt(0));
//		}
//		this.left = new Edge(edge.toString());
//
//	
//		
//		
//		// Determine if the shape has a reflection to limit the number of rotations
//		if (this.edgeEquals(EdgeEnum.TOP, this.getEdge(EdgeEnum.RIGHT)) 
//				&& this.equalsEdge(EdgeEnum.RIGHT, this.getEdge(EdgeEnum.BOTTOM), true)
//				&& this.edgeEquals(EdgeEnum.BOTTOM, this.getEdge(EdgeEnum.LEFT)) 
//				&& this.equalsEdge(EdgeEnum.LEFT, this.getEdge(EdgeEnum.TOP), true)) {
//			this.numSupportedRotations = 0;
//		} else if (this.equalsEdge(EdgeEnum.TOP, this.getEdge(EdgeEnum.BOTTOM), true) 
//				&& this.equalsEdge(EdgeEnum.LEFT, this.getEdge(EdgeEnum.RIGHT), true)) {
//			// if top == bottom and left == right
//			
//			this.numSupportedRotations = 1;
//		}
//		
//		if (this.containsHorizontalReflection() || this.containsVerticalReflection()) {
//			this.flipAllowed = false;
//		}
//
//	}
	
	
	
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
	
//	
//	public String getRow(int rowNum) {
//		
//		StringBuilder row = new StringBuilder();
//
//		for (int x=0; x < this.dimension; x++) {
//			row.append(this.side[rowNum][x] ? "o" : " ");
//		}
//
//		return row.toString();
//	}
//	
//	public void display() {
//		
//		// Print the values
//		for (int y=0; y < this.dimension; y++) {
//			for (int x=0; x < this.dimension; x++) {
//				System.out.print((this.side[y][x]) ? "o" : " ");
//			}
//			System.out.print("\n");
//		}
//	}
//	
//	
//	public void rotateRight() {
//		
//		// Initialize the side
//		Boolean[][] rotatedEdge = new Boolean[this.dimension][this.dimension];
//		
//		// Print the values
//		for (int y=0; y < this.dimension; y++) {
//			for (int x=0; x < this.dimension; x++) {
//				rotatedEdge[y][x] = this.side[this.dimension - x -1][y];				
//			}
//		}
//		
//		// Copy new side into this side
//		for (int y=0; y < this.dimension; y++) {
//			for (int x=0; x < this.dimension; x++) {
//				this.side[y][x] = rotatedEdge[y][x];				
//			}
//		}
//	}
//	
//	
//	public void flip() {
//
//		// Invert the value
//		for (int x=0; x < this.dimension; x++) {
//			reverseEdge(this.side[x]);
//		}
//	}
//	
//	
//	
//	public int getNumSupportedRotations() {
//		return this.numSupportedRotations;
//	}
//
//	public boolean isFlipAllowed() {
//		return this.flipAllowed;
//	}
//	
//	public boolean edgeEquals(EdgeEnum edgeType, Boolean[] edgeToCompare) {
//		return this.equalsEdge(edgeType, edgeToCompare, false);
//	}
//	
//	
//	public boolean equalsEdge(EdgeEnum edgeType, Boolean[] edgeToCompare, boolean invert) {
//		
//		Boolean[] thisSideEdge = this.getEdge(edgeType);
//		
//		if (invert) {
//			thisSideEdge = reverseEdge(thisSideEdge);
//		}
//		
//		for (int i=0; i< this.dimension; i++) {
//			if (thisSideEdge[i] ^ edgeToCompare[i]) {
//				return false;
//			}
//		}
//
//		return true;		
//	}
//
//
//	public boolean matches(EdgeEnum edgeType, Boolean[] edgeToCompare) {
//		return this.matches(edgeType, edgeToCompare, false);
//	}
//	
//	public boolean matches(EdgeEnum edgeType, Boolean[] edgeToCompare, Boolean invert) {
//		Boolean[] thisSideEdge = this.getEdge(edgeType);
//		
//		if (invert) {
//			thisSideEdge = reverseEdge(thisSideEdge);
//		}
//		
//		for (int i=0; i< this.dimension; i++) {
//			if ((thisSideEdge[i] & edgeToCompare[i])) {
//				return false;
//			}
//		}
//
//		return true;		
//	}
//	
//	
//
//	public Boolean[] getEdge(EdgeEnum edgeType) {
//		
//		switch (edgeType) {
//			case TOP:
//				return Arrays.copyOf(this.side[0], this.dimension);
//			case BOTTOM:
//				return Arrays.copyOf(this.side[this.dimension - 1], this.dimension);
//			case RIGHT:
//				Boolean[] rightEdge = new Boolean[this.dimension];
//				for (int i=0; i < this.dimension; i++) {
//					rightEdge[i] = this.side[i][dimension-1];
//				}
//				return rightEdge;
//			case LEFT:
//				Boolean[] leftEdge = new Boolean[this.dimension];
//				for (int i=0; i < this.dimension; i++) {
//					leftEdge[i] = this.side[i][0];
//				}
//				return leftEdge;
//			default:
//				return new Boolean[0];
//		
//		}
//	}
//	
//	private Boolean[] reverseEdge(Boolean[] edge) {
//
//		for (int i=0; i < edge.length/2; i++) {
//			Boolean tmp = edge[edge.length - (1+i)];
//			edge[edge.length - (1+i)] = edge[i];
//			edge[i] = tmp;
//		}
//		
//		return edge;		
//	}
//	
//	
//	private boolean containsVerticalReflection() {
//		
//		// Check for a reflection on the vertical axis
//		for (int y=0; y < this.dimension/2; y++) {
//			for (int x=0; x < this.dimension; x++) {
//				if (this.side[y][x] ^ this.side[y][this.dimension - (x+1)]) {
//					return false;
//				}
//			}
//		}
//		
//		return true;
//	}
//	
//	
//	
//	private boolean containsHorizontalReflection() {
//		
//		// Check for a reflection on the vertical axis
//		for (int x=0; x < this.dimension/2; x++) {
//			for (int y=0; y < this.dimension; y++) {
//				if (this.side[y][x] ^ this.side[this.dimension - (y+1)][x]) {
//					return false;
//				}
//			}
//		}
//		
//		return true;
//	}
//	
}
