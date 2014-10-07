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
		
		findValidFirstSide(potentialSolution, remainingSides, 0, false);
		
//		solve(potentialSolution, remainingSides, 0);
		
	}
	
	private void solve(List<Side> potentialSolution, List<Side> remainingSides, int index) {
		
		if (potentialSolution.size() == NUM_SIDES) {
			System.out.println("Solution Found");
			for (Side currSide: potentialSolution) {
				currSide.display();
			}
		}
		
		
		// 
		if (potentialSolution.size() == 0) {
			
			solveFirstSide(potentialSolution, remainingSides, 0, false);
			
//			// Remove the first side from the remaining sides and put it in the potential solutions
//			potentialSolution.add(remainingSides.remove(0));
//			
//			for (int i=0; i <= potentialSolution.get(0).getNumSupportedRotations(); i++) {
////				//TODO remove
////				System.out.println("Side 1");
////				potentialSolution.get(0).display();
//				
//				solve(potentialSolution, remainingSides, 0);
//				
//				// Rotate the shape to its right
//				potentialSolution.get(0).rotateRight();
//			}
		} else if (potentialSolution.size() == 1) {
			
			solveSecondSide(potentialSolution, remainingSides, 0, 0, false);
			
			
//			// Iterate over all of the indices and rotate for every side
//			for (int i=0; i < remainingSides.size(); i++) {
//	
//				potentialSolution.add(remainingSides.remove(i));
//				
//				// Rotate to tests out every side
//				for (int j=0; j <= potentialSolution.get(1).getNumSupportedRotations(); j++) {
//					
//					
//					// If a match 
//					if (potentialSolution.get(0).matchesEdge(Edge.BOTTOM,
//							potentialSolution.get(1).getEdge(Edge.TOP))) {
//						
////						//TODO display matching shapes
////						System.out.println("Side 2");
////						potentialSolution.get(1).display();
//						
//						solve(potentialSolution, remainingSides, 0);
//					}					
//					potentialSolution.get(1).rotateRight();
//				}
//				
//				remainingSides.add(i, potentialSolution.remove(1));				
//			}
		} else if (potentialSolution.size() == 2) {
			
			// Iterate over all of the indices and rotate for every side
			for (int i=0; i < remainingSides.size(); i++) {
	
				potentialSolution.add(remainingSides.remove(i));
				
				// Rotate to tests out every side
				for (int j=0; j <= potentialSolution.get(2).getNumSupportedRotations(); j++) {
					
					
					// If a match 
					if (potentialSolution.get(1).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(2).getEdge(Edge.TOP))) {
						
//						//TODO display matching shapes
//						System.out.println("Side 3");
//						potentialSolution.get(2).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
//					System.out.println("Rotating third side right");
					potentialSolution.get(2).rotateRight();
				}
				
				remainingSides.add(i, potentialSolution.remove(2));				
			}
		} else if (potentialSolution.size() == 3) {
			
			// Iterate over all of the indices and rotate for every side
			for (int i=0; i < remainingSides.size(); i++) {
	
				potentialSolution.add(remainingSides.remove(i));
				
				// Rotate to tests out every side
				for (int j=0; j <= potentialSolution.get(3).getNumSupportedRotations(); j++) {
					
					
					// The 4th side must match the bottom edge of side 3
					// And the top edge of side 1
					if (potentialSolution.get(2).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(3).getEdge(Edge.TOP))
						&& potentialSolution.get(3).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(0).getEdge(Edge.TOP)) ){
						
//						//TODO display matching shapes
//						System.out.println("Side 4");
//						potentialSolution.get(3).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
//					System.out.println("Rotating fourth side right");
					potentialSolution.get(3).rotateRight();
				}
				
				remainingSides.add(i, potentialSolution.remove(3));				
			}
		} else if (potentialSolution.size() == 4) {
			// Iterate over all of the indices and rotate for every side
			for (int i=0; i < remainingSides.size(); i++) {
	
				potentialSolution.add(remainingSides.remove(i));
				
				// Rotate to tests out every side
				for (int j=0; j <= potentialSolution.get(4).getNumSupportedRotations(); j++) {
					
					
					// The right edge of 5 must match the left edge of 4
					// The top edge of 5 must match the left edge of 3
					// The left edge of 5 must match the inverted left edge of 2
					// The bottom edge of 5 must match the inverted left edge of 1
					if (potentialSolution.get(4).matchesEdge(Edge.RIGHT, potentialSolution.get(3).getEdge(Edge.LEFT))
							&& potentialSolution.get(4).matchesEdge(Edge.TOP, potentialSolution.get(2).getEdge(Edge.LEFT))
							&& potentialSolution.get(4).matchesEdge(Edge.LEFT, potentialSolution.get(1).getEdge(Edge.LEFT), true)
							&& potentialSolution.get(4).matchesEdge(Edge.BOTTOM, potentialSolution.get(0).getEdge(Edge.LEFT), true)){
						
//						//TODO display matching shapes
//						System.out.println("Side 5");
//						potentialSolution.get(4).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
//					System.out.println("Rotating fourth side right");
					potentialSolution.get(4).rotateRight();
				}
				
				remainingSides.add(i, potentialSolution.remove(4));				
			}
		} else if (potentialSolution.size() == 5) {
			potentialSolution.add(remainingSides.remove(0));
			
			// Rotate to tests out every side
			for (int j=0; j <= potentialSolution.get(5).getNumSupportedRotations(); j++) {
				
				
				// The right edge of 5 must match the left edge of 4
				// The top edge of 5 must match the left edge of 3
				// The left edge of 5 must match the inverted left edge of 2
				// The bottom edge of 5 must match the inverted left edge of 1
				if (potentialSolution.get(5).matchesEdge(Edge.LEFT, potentialSolution.get(3).getEdge(Edge.RIGHT))
						&& potentialSolution.get(5).matchesEdge(Edge.TOP, potentialSolution.get(2).getEdge(Edge.RIGHT))
						&& potentialSolution.get(5).matchesEdge(Edge.RIGHT, potentialSolution.get(1).getEdge(Edge.RIGHT), true)
						&& potentialSolution.get(5).matchesEdge(Edge.BOTTOM, potentialSolution.get(0).getEdge(Edge.RIGHT), true)){
					
//					//TODO display matching shapes
//					System.out.println("Side 6");
//					potentialSolution.get(5).display();
					
					solve(potentialSolution, remainingSides, 0);
				}
				
//				System.out.println("Rotating fourth side right");
				potentialSolution.get(5).rotateRight();
			}
			
			remainingSides.add(0, potentialSolution.remove(5));	
		}
	}
	
	private void solveFirstSide(List<Side> potentialSolution, List<Side> remainingSides, int numRotation, boolean hasFlipped) {
		// Remove the first side from the remaining sides and put it in the potential solutions
		potentialSolution.add(remainingSides.remove(0));
		
		
		solve(potentialSolution, remainingSides, 0);
		
		if (numRotation < potentialSolution.get(0).getNumSupportedRotations()) {
			// Rotate the shape to its right
			potentialSolution.get(0).rotateRight();
			
			solveFirstSide(potentialSolution, remainingSides, ++numRotation, hasFlipped);
		} else if (!hasFlipped && potentialSolution.get(0).isFlipAllowed()) {
			
			// Flip
			potentialSolution.get(0).flip();
			
			solveFirstSide(potentialSolution, remainingSides, 0, true);
		}
	}
	
	private void solveSecondSide(List<Side> potentialSolution, List<Side> remainingSides, int index, int numRotation, boolean hasFlipped) {
		
		if (index >= remainingSides.size()) {
			return;
		}
			
		
		// If a match 
		
		if (potentialSolution.get(0).matchesEdge(Edge.BOTTOM,
				remainingSides.get(index).getEdge(Edge.TOP))) {
			
			potentialSolution.add(remainingSides.remove(index));
			solve(potentialSolution, remainingSides, 0);
			remainingSides.add(index, potentialSolution.remove(1));		

		}
		
		if (numRotation < remainingSides.get(index).getNumSupportedRotations()) {
			// Rotate the shape to its right
			remainingSides.get(index).rotateRight();
			
			solveSecondSide(potentialSolution, remainingSides, index, ++numRotation, hasFlipped);			
		} else if (!hasFlipped && remainingSides.get(index).isFlipAllowed()) {
			
			// Flip
			remainingSides.get(index).flip();
			
			solveSecondSide(potentialSolution, remainingSides, index, 0, true);
		} else {
			// increase the index
			solveSecondSide(potentialSolution, remainingSides, ++index, 0, false);
		}
	
	}
	
	private void findValidFirstSide(List<Side> potentialSolution, List<Side> remainingSides, int numRotation, boolean hasFlipped) {
		
		
		if (potentialSolution.isEmpty()) {
			potentialSolution.add(remainingSides.remove(0));
			findValidSide(potentialSolution, remainingSides, 1, 0, 0, false);
			
			remainingSides.add(0, potentialSolution.remove(0));
			
		}
		
		if (numRotation < remainingSides.get(0).getNumSupportedRotations()) {
			// Rotate the shape to its right
			remainingSides.get(0).rotateRight();
			
			findValidFirstSide(potentialSolution, remainingSides, numRotation + 1, false);
		} else if (!hasFlipped && remainingSides.get(0).isFlipAllowed()) {
			
			// Flip
			remainingSides.get(0).flip();

			findValidFirstSide(potentialSolution, remainingSides, 0, true);
		}
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
			
		
//		//TODO debug
//		if (currPosition == 2) {
//			//TODO display matching shapes
//			System.out.println("currSide");
//			remainingSides.get(index).display();
//			
//		}
		
		
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

			//TODO maybe want to flip back??
			// Flip
			remainingSides.get(index).flip();
			
		} else {
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
