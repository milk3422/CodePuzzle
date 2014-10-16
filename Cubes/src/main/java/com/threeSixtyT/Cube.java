package com.threeSixtyT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The Cube is designed to find all of the solutions for a cube made of 6 sides
 * 
 * @author Zachary Radtka
 */
public class Cube {

	/** The number of sides in a cube */
	private static final int NUM_SIDES = 6;
	
	/** A list of sides representing the cube */
	private List<Side> sides;
	
	/**
	 * Initialize a new Cube by specifying a {@code String[][]} . The 
	 * {@code String[][]}  must be of dimensions 6 x 6. Each {@code String[]} 
	 * must use '1' and '0' to represent the parts of a side that is present 
	 * and missing, respectively.
	 * 
	 * @param sides
	 * 			A {@code String[][]} representation of a Cube
	 */
	public Cube(String[][] sides) {
		this.sides = new ArrayList<Side>(NUM_SIDES);
		
		for (String[] currSide: sides) {
			this.sides.add(new Side(currSide));
		}
	}
	
	/**
	 * Initialize a Cube on a previously initialized Cube
	 * 
	 * @param cube
	 * 			The {@code Cube} to copy
	 */
	public Cube(List<Side> cube) {
		this.sides = new ArrayList<Side>(NUM_SIDES);
		
		for (Side side: cube) {
			this.sides.add(new Side(side));
		}
	}

	/**
	 * Return a copy of a side of the cube.
	 * @param index
	 * 		The index of the {@code Side} to return
	 * 
	 * @return	The {@code Side} at the specified index
	 * @exception IndexOutOfBoundsException if the {@code index} argument is
	 * 				negative or is not less than the number of the sides
	 */
	public Side getSide(int index) {
		if ((index < 0) || (index >= this.sides.size())) {
			throw new IndexOutOfBoundsException();
		}
		
		return new Side(this.sides.get(index));
	}
	
	/**
	 * Find all of the possible solutions for a cube. A {@code null} is 
	 * returned if no possible solutions were found
	 * 
	 * @return	A {@code List<Cube>} of solutions, a {@code null} is returned
	 * 			if no solutions were found
	 */
	public List<Cube> solve() {
		
		// Create a list to hold all of the possible solutions
		List<Cube> allSolutions = new LinkedList<Cube>();
		
		// Create a list to hold the current solution
		List<Side> currsolution = new ArrayList<Side>(NUM_SIDES);
		
		// Create a list to hold the remaining solutions
		List<Side> remainingSides = new ArrayList<Side>(NUM_SIDES);
		
		// Add all of the sides of the cube to the remaining sides
		remainingSides.addAll(this.sides);
				
		// Find all of the possible solutions
		findValidSide(currsolution, remainingSides, 0, 0, 0, false,
				allSolutions);
		
		return allSolutions;
	}

	/**
	 * The general layout of sides is:
	 * <p> 0
	 * <p> 1
	 * <p> 2
	 * <p>435
	 * <p>The general algorithm is to find a solution for each position using 
	 * all possible combinations. If a side is a solution for the current 
	 * position, the side is inserted into the currSolution List. If the side
	 * is not a solution, it is rotated and/or flipped and re-tested.
	 * <p>A solution is stored in allSolutions when currSolution has 6 Sides.
	 * 
	 * @param currSolution
	 * 			The {@code List<Side>} of {@code Side}s that form a possible 
	 * 			solution
	 * @param remainingSides
	 * 			The {@code List<Side>} of {@code Side}s not in the current 
	 * 			solution
	 * @param currPosition
	 * 			The position trying to be solved
	 * @param index
	 * 			The solutions index of the remaniningSides
	 * @param numRotation
	 * 			The number of rotations the current {@code Side} has performed
	 * @param hasFlipped
	 * 			Has the current {@code Side} flipped
	 * @param allSolutions
	 * 			A {@code List<Cube>} of all valid solutions
	 */
	private void findValidSide(List<Side> currSolution, 
			List<Side> remainingSides, 
			int currPosition, 
			int index, 
			int numRotation, 
			boolean hasFlipped, 
			List<Cube> allSolutions) {
		
		// If the current position is equal to the number of sides, a solution 
		// has been found
		if (currPosition == NUM_SIDES) {
			
			// If a solution has been found add it to the list of solutions
			allSolutions.add(new Cube(currSolution));
			
		} else if (index < remainingSides.size()) {
			
			// The index is less than the amount of sides remaining, determine
			// if a match exists
			if (sidesMatch(currPosition, currSolution, remainingSides.get(index))) {
				
				// Add the matching side from the remaining solutions to the current
				// solution, find solutions, and replace the matching side back 
				// in the remaining sides list
				currSolution.add(remainingSides.remove(index));
				findValidSide(currSolution, remainingSides, currPosition+1, 0, 0, 
						false, allSolutions);			
				remainingSides.add(index, currSolution.remove(currPosition));
			}
			
			// If no match rotate the shape
			if (numRotation < remainingSides.get(index).getNumAllowedRotations()) {
		
				// Rotate the shape to its right, find solutions, and rotate it back
				// to its original orientation
				remainingSides.get(index).rotateRight();
				findValidSide(currSolution, remainingSides, currPosition, index, 
						numRotation+1, hasFlipped, allSolutions);		
				remainingSides.get(index).rotateLeft();
				
			} else if (!hasFlipped && remainingSides.get(index).isFlipAllowed()) {
		
				// Flip the shape over, find solutions, and flip it back to its
				// original orientation
				remainingSides.get(index).flip();
				findValidSide(currSolution, remainingSides, currPosition, index, 0, 
						true, allSolutions);
				remainingSides.get(index).flip();
				
			} else if (currPosition > 0){
				
				// Every position except for the first position uses the index
				// increase the index
				findValidSide(currSolution, remainingSides, currPosition, index+1, 0, 
						false, allSolutions);			
			}
		}
	}
	

	/**
	 * Determine if a side can be placed in the current position.
	 * 
	 * @param currPosition
	 * 		The current side needing to be matched
	 * @param solution
	 * 		The {@code List<Side>} of possible solutions
	 * @param currSide
	 * 		The {@code Side} being evaluated
	 * @return	{@code true} if the current side is a match, {@code false} 
	 * 			otherwise
	 */
	private boolean sidesMatch(int currPosition, List<Side> solution, Side currSide) {
		
		if (currPosition == 0) {
			
			// No comparison needs to be done
			return true;
			
		} else if (currPosition == 1 || currPosition == 2) {
			
			// Bottom of previous position matches top of current
			return currSide.getTopEdge().matches(solution.get(currPosition-1).getBottomEdge(), true);
			
		} else if (currPosition == 3) {
			
			// Bottom of 2 position matches top of current
			return currSide.getTopEdge().matches(solution.get(2).getBottomEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getTopEdge(), true);
			
		} else if (currPosition == 4) {
			
			// Right edge of 4 must match left edge of 3
			// AND top edge of 4 must match left edge of 2
			// AND left edge of 4 must match left edge 1
			// And bottom edge of 4 must match left edge of 0
			return currSide.getRightEdge().matches(solution.get(3).getLeftEdge(), true)
					&& currSide.getTopEdge().matches(solution.get(2).getLeftEdge(), true)
					&& currSide.getLeftEdge().matches(solution.get(1).getLeftEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getLeftEdge(), true);
		
		} else if (currPosition == 5) {
			
			// Left edge of 5 must match right edge of 3
			// AND top edge of 5 must match right edge of 2
			// AND right edge of 5 must match right edge 1
			// And bottom edge of 5 must match right edge of 0
			return currSide.getLeftEdge().matches(solution.get(3).getRightEdge(), true)
					&& currSide.getTopEdge().matches(solution.get(2).getRightEdge(), true)
					&& currSide.getRightEdge().matches(solution.get(1).getRightEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getRightEdge(), true);
		}
		
		return false;
	}
}
