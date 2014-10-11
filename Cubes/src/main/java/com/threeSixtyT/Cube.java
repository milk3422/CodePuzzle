package com.threeSixtyT;

import java.util.ArrayList;
import java.util.List;

public class Cube {

	/** The number of sides in a cube */
	private static final int NUM_SIDES = 6;
	
	/** A list of sides representing the cube */
	private List<Side> cube;
	
	
	public Cube(String[][] sides) {
		
		// Initialize the list to be 
		this.cube = new ArrayList<Side>(NUM_SIDES);
		
		for (String[] currSide: sides) {
			this.cube.add(new Side(currSide));
		}
	}
	
	public void addSides(String[][] sides) {
		// Initialize the list to be 
		this.cube = new ArrayList<Side>(NUM_SIDES);
		
		for (String[] currSide: sides) {
			this.cube.add(new Side(currSide));
		}
	}
	
	public void display() {
		this.display(this.cube);
	}
	
	public void display(List<Side> cube) {
		for (Side currSide: cube) {
			currSide.display();
			System.out.println();
		}
	}

	public void solve() {
		
		List<Side> solution = new ArrayList<Side>(NUM_SIDES);
		List<Side> remainingSides = new ArrayList<Side>(NUM_SIDES);
		
		remainingSides.addAll(cube);
				
		findValidSide(solution, remainingSides, 0, 0, 0, false);
	}

	
	private void findValidSide(List<Side> solution, List<Side> remainingSides, int currPosition, int index, int numRotation, boolean hasFlipped) {
		System.out.println("\nCurr Solution");
		displayDebug(solution);
		System.out.println("\nremainingSides");
		displayDebug(remainingSides);
		System.out.println();
		
		
		if (currPosition == NUM_SIDES) {
			System.out.println("Solution Found");
			displaySolution(solution);
			return;

		}
		
		if (index >= remainingSides.size()) {
			return;
		}
			
		
		// If a match 
		if (sidesMatch(currPosition, solution, remainingSides.get(index))) {
			
			solution.add(remainingSides.remove(index));
			findValidSide(solution, remainingSides, currPosition+1, 0, 0, false);			
			remainingSides.add(index, solution.remove(currPosition));
		}
		
		
		// If no match rotate the shape
		if (numRotation < remainingSides.get(index).getNumSupportedRotations()) {
	
			// Rotate the shape to its right
			remainingSides.get(index).rotateRight();

			findValidSide(solution, remainingSides, currPosition, index, numRotation+1, hasFlipped);		

			
			// Rotate back to the original position
			remainingSides.get(index).rotateLeft();
		} else if (!hasFlipped && remainingSides.get(index).isFlipAllowed()) {
	
			// Flip
			remainingSides.get(index).flip();
	
			findValidSide(solution, remainingSides, currPosition, index, 0, true);			

			
			// Flip back to original orientation
			remainingSides.get(index).flip();
		} else if (currPosition > 0){
			
			// Every position except for the first position uses the index
			// increase the index
			findValidSide(solution, remainingSides, currPosition, index+1, 0, false);			
		}
	}
	

	private boolean sidesMatch(int currPosition, List<Side> solution, Side currSide) {
		
		if (currPosition == 0) {
			// No comparison needs to be done
			return true;
		} else if (currPosition == 1 || currPosition == 2) {
			// Bottom of previous position matches top of current
			return currSide.getTopEdge().matches(solution.get(currPosition-1).getBottomEdge(), true);
			
		} else if (currPosition == 3) {
			// Bottom of 2 position matches top oc current
			return currSide.getTopEdge().matches(solution.get(2).getBottomEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getTopEdge(), true);
			
		} else if (currPosition == 4) {

			return currSide.getRightEdge().matches(solution.get(3).getLeftEdge(), true)
					&& currSide.getTopEdge().matches(solution.get(2).getLeftEdge(), true)
					&& currSide.getLeftEdge().matches(solution.get(1).getLeftEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getLeftEdge(), true);
			

		} else if (currPosition == 5) {
			return currSide.getLeftEdge().matches(solution.get(3).getRightEdge(), true)
					&& currSide.getTopEdge().matches(solution.get(2).getRightEdge(), true)
					&& currSide.getRightEdge().matches(solution.get(1).getRightEdge(), true)
					&& currSide.getBottomEdge().matches(solution.get(0).getRightEdge(), true);
		}
		
		return false;
	}
	
	
	private void displaySolution(List<Side> solution) {
	int dimension = solution.get(0).getDimension();	
	
	// Display the first three sides
	for(int i=0; i < 3; i++) {
		Side currSide = solution.get(i);
		String[] s = currSide.getSide();
		
		if (i < 3) {				
			for (int j=0; j< dimension; j++) {
				System.out.format("%" + 2*dimension + "s\n", s[j]);
				}
			}
		}
		
		// Display the last three sides
		for (int i=0; i < dimension; i++) {
			System.out.format("%s%s%s\n", solution.get(4).getSide()[i], solution.get(3).getSide()[i], solution.get(5).getSide()[i]);
		}
		
		System.out.println();
	}
	
	
	private void displayDebug(List<Side> solution) {
		if (solution.isEmpty()) {
			return;
		}
		int dimension = solution.get(0).getDimension();	
	
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < dimension; i++) {
			for( Side curr : solution) {
				sb.append(curr.getSide()[i]).append(" ");
			}
			
			System.out.println(sb.toString());
			sb.setLength(0);
			
		}		
	}
	
}
