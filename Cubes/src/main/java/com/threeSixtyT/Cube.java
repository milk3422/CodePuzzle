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
		
		solve(potentialSolution, remainingSides, 0);
		
	}
	
	private void solve(List<Side> potentialSolution, List<Side> remainingSides, int index) {
		
		if (potentialSolution.size() == NUM_SIDES) {
			System.out.println("Solution Found");
			for (Side currSide: potentialSolution) {
				currSide.display();
			}

		}
		
		int numRotations = 4;
		
		// 
		if (potentialSolution.size() == 0) {
			// Remove the first side from the remaining sides and put it in the potential solutions
			potentialSolution.add(remainingSides.remove(0));
			
			for (int i=0; i < numRotations; i++) {
//				//TODO remove
//				System.out.println("Side 1");
//				potentialSolution.get(0).display();
				
				solve(potentialSolution, remainingSides, 0);
				//TODO remove
//				System.out.println("Rotating first side right");
				potentialSolution.get(0).rotateRight();
			}
		} else if (potentialSolution.size() == 1) {
			
			// Iterate over all of the indices and rotate for every side
			for (int i=0; i < remainingSides.size(); i++) {
	
				potentialSolution.add(remainingSides.remove(i));
				
				// Rotate to tests out every side
				for (int j=0; j < numRotations; j++) {
					
					
					// If a match 
					if (potentialSolution.get(0).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(1).getEdge(Edge.TOP))) {
						
//						//TODO display matching shapes
//						System.out.println("Side 2");
//						potentialSolution.get(1).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
//					System.out.println("Rotating second side right");
					potentialSolution.get(1).rotateRight();
				}
				
				remainingSides.add(i, potentialSolution.remove(1));				
			}
		} else if (potentialSolution.size() == 2) {
			
			// Iterate over all of the indices and rotate for every side
			for (int i=0; i < remainingSides.size(); i++) {
	
				potentialSolution.add(remainingSides.remove(i));
				
				// Rotate to tests out every side
				for (int j=0; j < numRotations; j++) {
					
					
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
				for (int j=0; j < numRotations; j++) {
					
					
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
				for (int j=0; j < numRotations; j++) {
					
					
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
			for (int j=0; j < numRotations; j++) {
				
				
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
	
	
}
