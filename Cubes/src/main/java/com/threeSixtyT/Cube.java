package com.threeSixtyT;

import java.util.ArrayList;
import java.util.List;

import com.threeSixtyT.Side.Edge;

public class Cube {

	private static final int NUM_SIDES = 6;
	private List<Side> sides;
	
	
	public Cube(String[][] sides) {
		
		// Initialize the list to be 
		this.sides = new ArrayList<Side>(NUM_SIDES);
		
		for (String[] currSide: sides) {
			this.sides.add(new Side(currSide));
		}
	}
	
	public void addSides(String[][] sides) {
		// Initialize the list to be 
		this.sides = new ArrayList<Side>(NUM_SIDES);
		
		for (String[] currSide: sides) {
			this.sides.add(new Side(currSide));
		}
	}
	
	public void displayCube() {
		
		// Loop through the list of sides and display them
		for (Side currSide: this.sides) {
			currSide.display();
			System.out.println();
		}
	}

	
	public void solve() {
		
		List<Side> potentialSolution = new ArrayList<Side>(NUM_SIDES);
		List<Side> remainingSides = new ArrayList<Side>(NUM_SIDES);
		
		remainingSides.addAll(sides);
				
		findValidSide(potentialSolution, remainingSides, 0, 0, 0, false);
	}

	private void findValidSide(List<Side> potentialSolution, List<Side> remainingSides, int currPosition, int index, int numRotation, boolean hasFlipped) {
		
		if (currPosition == NUM_SIDES) {
			System.out.println("Solution Found");
			for (Side currSide: potentialSolution) {
				currSide.display();
			}
		}
		
		if (index >= remainingSides.size()) {
			return;
		}
			
		
		// If a match 
		if (sidesMatch(currPosition, potentialSolution, remainingSides.get(index))) {
			
			potentialSolution.add(remainingSides.remove(index));
			findValidSide(potentialSolution, remainingSides, currPosition+1, 0, 0, false);			
			remainingSides.add(index, potentialSolution.remove(currPosition));
		}
		
		// If no match rotate the shape
		if (numRotation < remainingSides.get(index).getNumSupportedRotations()) {
			
			// Rotate the shape to its right
			remainingSides.get(index).rotateRight();
			
			findValidSide(potentialSolution, remainingSides, currPosition, index, numRotation+1, hasFlipped);		
			
			// Rotate back to the original position
			remainingSides.get(index).rotateRight();

		} else if (!hasFlipped && remainingSides.get(index).isFlipAllowed()) {
			
			// Flip
			remainingSides.get(index).flip();
			
			findValidSide(potentialSolution, remainingSides, currPosition, index, 0, true);			

			// Flip back to original orientation
			remainingSides.get(index).flip();
			
		} else if (currPosition > 0){
			
			// Every position except for the first position uses the index
			// increase the index
			findValidSide(potentialSolution, remainingSides, currPosition, index+1, 0, false);			
		}
	}
	

	private boolean sidesMatch(int currPosition, List<Side> potentialSolution, Side currSide) {
		
		if (currPosition == 0) {
			// No comparison needs to be done
			return true;
		} else if (currPosition == 1 || currPosition == 2) {
			return potentialSolution.get(currPosition-1).
					matchesEdge(Edge.BOTTOM,currSide.getEdge(Edge.TOP));
		} else if (currPosition == 3) {
			return currSide.matchesEdge(Edge.TOP, potentialSolution.get(2).getEdge(Edge.BOTTOM))
				&& currSide.matchesEdge(Edge.BOTTOM, potentialSolution.get(0).getEdge(Edge.TOP));
		} else if (currPosition == 4) {
			return currSide.matchesEdge(Edge.RIGHT, potentialSolution.get(3).getEdge(Edge.LEFT))
					&& currSide.matchesEdge(Edge.TOP, potentialSolution.get(2).getEdge(Edge.LEFT))
					&& currSide.matchesEdge(Edge.LEFT, potentialSolution.get(1).getEdge(Edge.LEFT), true)
					&& currSide.matchesEdge(Edge.BOTTOM, potentialSolution.get(0).getEdge(Edge.LEFT), true);
		} else if (currPosition == 5) {
			return currSide.matchesEdge(Edge.LEFT, potentialSolution.get(3).getEdge(Edge.RIGHT))
					&& currSide.matchesEdge(Edge.TOP, potentialSolution.get(2).getEdge(Edge.RIGHT))
					&& currSide.matchesEdge(Edge.RIGHT, potentialSolution.get(1).getEdge(Edge.RIGHT), true)
					&& currSide.matchesEdge(Edge.BOTTOM, potentialSolution.get(0).getEdge(Edge.RIGHT), true);
		}
		
		return false;
	}
	
	
}
