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
			this.displayCube();
		}
		
		int numRotations = 4;
		
		// 
		if (potentialSolution.size() == 0) {
			// Remove the first side from the remaining sides and put it in the potential solutions
			potentialSolution.add(remainingSides.remove(0));
			
			for (int i=0; i < numRotations; i++) {
				//TODO remove
				potentialSolution.get(0).display();
				
				solve(potentialSolution, remainingSides, 0);
				//TODO remove
				System.out.println("Rotating first side right");
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
						
						//TODO display matching shapes
						potentialSolution.get(1).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
					System.out.println("Rotating second side right");
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
						
						//TODO display matching shapes
						potentialSolution.get(2).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
					System.out.println("Rotating third side right");
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
					
					
					// If a match 
					if (potentialSolution.get(2).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(3).getEdge(Edge.TOP))
						&& potentialSolution.get(3).matchesEdge(Edge.BOTTOM,
							potentialSolution.get(0).getEdge(Edge.TOP)) ){
						
						//TODO display matching shapes
						potentialSolution.get(3).display();
						
						solve(potentialSolution, remainingSides, 0);
					}
					
					System.out.println("Rotating fourth side right");
					potentialSolution.get(3).rotateRight();
				}
				
				remainingSides.add(i, potentialSolution.remove(3));				
			}
		}
		
		
	}
	
	
}
