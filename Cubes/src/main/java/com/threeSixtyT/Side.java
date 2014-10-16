package com.threeSixtyT;

/**
 * A Side is an n x n representation of a side of a three dimensional shape. A 
 * Side can be rotated, flipped, and compared to other sides to determine if 
 * the edges of sides match.
 * <p>A Side is composed of four edges: top, left, bottom, right.
 * 
 * @author Zachary Radtka
 */
public class Side {
	
	/** The default number of rotations supported by a side*/
	private static final int DEFAULT_NUM_ROTATIONS = 3;
	
	/** The Character used to draw the side */
	private static final Character DEFAULT_FILLED_CHARACTER = 'o';
	
	/** The Character used to draw the empty parts of the side */
	private static final Character DEFAULT_EMPTY_SPACE = ' ';
	
	/** The dimension of the side */
	private final int dimension;
	
	/** The number of rotations supported by the current side */
	private final int numSupportedRotations;
	
	/** Determines if a flip is allowed */
	private final boolean flipAllowed;
	
	/** The top edge */
	private Edge top;
	
	/** The right edge */
	private Edge right;
	
	/** The bottom edge */
	private Edge bottom;
	
	/** The left edge*/
	private Edge left;
	
	/**
	 * Initialize a new Side by specifying an n x n {@code String[]}. The 
	 * {@code String[]} must use '1' and '0' to represent the parts of the side
	 * that is present and missing, respectively.
	 * 
	 * @param side
	 * 			An n x n {@code String[]} that represents a Side
	 */
	public Side(String[] side) {

		// Ensure the dimensions are correct and assign the dimensions
		int sideLength = side.length;
		
		for (int i=0; i < side.length; i++) {
			if (side[i].length() != sideLength) {
				throw new IllegalArgumentException("Side must be n x n");
			}
		}
		
		this.dimension = side.length;
		
		// Utilize a string builder to obtain the edges
		StringBuilder edge = new StringBuilder();
		
		
		// Initialize the top side
		this.top = new Edge(side[0]);
		
		// Initialize the right edge
		for (int i=0; i < this.dimension; i++) {
			edge.append(side[i].charAt(this.dimension - 1));
		}
		this.right = new Edge(edge.toString());
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

		// Determine the number of rotations by the shapes symmetry
		if (this.isFullySymmetrical()) {
			this.numSupportedRotations = 0;
		} else if (this.isPartiallySymmetrical()) {
			this.numSupportedRotations = 1;
		} else {
			this.numSupportedRotations = DEFAULT_NUM_ROTATIONS;
		}
		
		// Determine if a flip is allowed based on if a reflection is present
		if (this.containsReflection()) {
			this.flipAllowed = false;
		} else {
			this.flipAllowed = true;
		}
	}
	
	/**
	 * Initialize a new Side by specifying a previously defined side
	 * 
	 * @param original
	 * 		The {@code Side} to copy
	 */
	public Side(Side original) {
		
		// Set the side's dimensions
		this.dimension = original.dimension;
		
		// Set the number of supported rotations
		this.numSupportedRotations = original.numSupportedRotations;
		
		// Set if the flip is allowed
		this.flipAllowed = original.flipAllowed;
		
		// Set all of the edges
		this.top = new Edge(original.top);		
		this.right = new Edge(original.right);		
		this.bottom = new Edge(original.bottom);
		this.left = new Edge(original.left);
	}

	/**
	 * Returns the dimension of the side. 
	 * 
	 * @return The length of a side
	 */
	public int getDimension() {
		return this.dimension;
	}
	
	/**
	 * Return a copy of the top edge.
	 * 
	 * @return	The top edge
	 */
	public Edge getTopEdge() {
		return new Edge(this.top);
	}
	
	/**
	 * Return a copy of the right edge.
	 * 
	 * @return	The right edge
	 */
	public Edge getRightEdge() {
		return new Edge(this.right);
	}
	
	/**
	 * Return a copy of the bottom edge.
	 * 
	 * @return	The bottom edge
	 */
	public Edge getBottomEdge() {
		return new Edge(this.bottom);
	}
	
	/**
	 * Return a copy of the left edge.
	 * 
	 * @return	The left edge
	 */
	public Edge getLeftEdge() {
		return new Edge(this.left);
	}
	
	/**
	 * Return the number of allowed rotations for the shape based on the shapes
	 * symmetry.
	 * 
	 * @return	The number of rotations
	 */
	public int getNumAllowedRotations() {
		return this.numSupportedRotations;
	}
	
	/**
	 * Returns {@code true} if the side is allowed to be flipped, {@code false}
	 * otherwise.
	 * 
	 * @return	{@code true} if the side is allowed to be flipped, {@code false}
	 * otherwise
	 */
	public boolean isFlipAllowed() {
		return this.flipAllowed;
	}
	
	/**
	 * Perform an in-place clockwise rotation.
	 */
	public void rotateRight() {	

		// Copy the top edge
		Edge top = this.top;
		
		// Rotate all of the edges
		this.top = this.left;
		this.left = this.bottom;
		this.bottom = this.right;
		this.right = top;
	}
	
	/**
	 * Perform an in-place counter clockwise rotation.
	 */
	public void rotateLeft() {	
		
		// Copy the top edge
		Edge top = this.top;
		
		// Rotate all of the edges
		this.top = this.right;
		this.right = this.bottom;
		this.bottom = this.left;
		this.left = top;
	}
	
	/**
	 * Perform an in-place flip on the side along the vertical axis.
	 */
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
	
	/** 
	 * Determines if the side is fully symmetrical. A symmetrical side
	 * has four edges that are all equal.
	 * 
	 * @return {@code true} if the side is fully symmetrical, {@code false} 
	 * 			otherwise
	 */
	public boolean isFullySymmetrical() {
		// A symmetrical shape has all sides equal
		if (this.top.equals(this.right) 
				&& this.top.equals(this.bottom)
				&& this.top.equals(this.left)) {
			return true;
		}
		return false;
	}

	/**
	 * Determine if the side is partially symmetrical. A partially symmetrical
	 * side is a side that contains a top edge equal to a bottom edge, and a
	 * left edge equal to a right edge.
	 * 
	 * @return	{@code true} if the side is partially symmetrical, {@code false} 
	 * 			otherwise
	 */
	public boolean isPartiallySymmetrical() {
		// partially symmetrical means 
		if (this.top.equals(this.bottom) 
				&& this.left.equals(this.right)) {
			return true;
		}
		return false;
	}

	/**
	 * Determine if the side contains a reflection. A reflection occurs if a 
	 * side flipped on the horizontal or vertical axis is equal to side 
	 * un-flipped
	 * 
	 * @return	{@code true} if the side contains a reflection, {@code false} 
	 * 			otherwise
	 */
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
	
	/**
	 * Return the side as a String[].
	 * 
	 * @return {@code String[]} containing the side
	 */
	public String[] getSide() {
		
		// Declare a String array to return
		String[] side = new String[this.dimension];
		
		// Make a copy of all of the edges
		Boolean[] topEdge = this.top.getEdge();
		Boolean[] rightEdge = this.right.getEdge();
		Boolean[] bottomEdge = this.bottom.getEdge();
		Boolean[] leftEdge = this.left.getEdge();
		
		// Use a StringBuilder to build a row at a time
		StringBuilder edgeString = new StringBuilder();
		
		// Create the first row
		for (int i=0; i< this.dimension; i++) {
			edgeString.append((topEdge[i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);
		}
		side[0] = edgeString.toString();
		
		// Create all rows between the top and bottom
		for (int i=1; i< this.dimension; i++) {
			edgeString.setLength(0);
			for (int j=0; j< this.dimension; j++) {
		
				// The first and last element of a row must be taken from the 
				// left and right side respectively
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
		
		// Create the last row
		edgeString.setLength(0);
		for (int i=0; i < this.dimension; i++) {
			edgeString.append((bottomEdge[this.dimension - 1 - i]) ? DEFAULT_FILLED_CHARACTER : DEFAULT_EMPTY_SPACE);
		}
		side[this.dimension - 1] = edgeString.toString();
		
		
		return side;
	}
	
	/**
	 * Display the side to STDOUT
	 */
	public void display() {
		String[] currSide = this.getSide();
		for(int i=0; i< currSide.length; i++) {
			System.out.println(currSide[i]);
		}
	}

}
