package com.threeSixtyT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Cube solution will find all possible solutions for a cube of 6 sides.
 * <p>
 * <b>To Build</b>
 * <p>
 * Cube can be built using 'mvn clean install' from within the Cube project. 
 * The Cube jar file will be in Cube/target.
 * <p>
 * <b>Usage</b>
 * <p>
 * {@literal java -jar Cube <cubeFile>}
 * 
 * @author Zachary Radtka
 */
public class Driver {

	/** The number of sides in a Cube */
	public static final Integer NUM_SIDES = 6;

	public static void main(String[] args) {

		String cubeFile = null;
		String[][] inputCube = null;

		// Ensure the correct number of arguments were specified
		if (args.length != 1) {
			System.err.println("Usage: Cube <cubeFile>");
			System.exit(1);
		}

		// Get the name of the file holding the cube
		cubeFile = args[0];

		// First populate the dictionary
		try (BufferedReader br = new BufferedReader(new FileReader(cubeFile))) {

			// The line being read in
			String currLine;

			// Initialize the cube being read in
			if ((currLine = br.readLine()) != null) {

				int dimension = Integer.valueOf(currLine);
				inputCube = new String[NUM_SIDES][dimension];

				// Read in n lines
				for (int currSide = 0; currSide < NUM_SIDES
						&& (currLine = br.readLine()) != null; currSide++) {

					for (int i = 0; i < dimension
							&& (currLine = br.readLine()) != null; i++) {
						inputCube[currSide][i] = currLine;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File '" + cubeFile
					+ "' could not be found.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Error: Error while reading  '" + cubeFile
					+ "'.");
			System.exit(1);
		}

		// Initialize the cube
		Cube cube = new Cube(inputCube);

		// Iterate through the solutions displaying them to STDOUT
		for (Cube solution : cube.solve()) {
			displaySolution(solution);
			System.out.println();
		}

	}
	
	/**
	 * Display a cube to STDOUT
	 * 
	 * @param cube
	 * 		An {@code Cube} to display
	 */
	static public void displaySolution(Cube cube) {
		
		int dimension = cube.getSide(0).getDimension();	
		String[] currSideMatrix;
		
		// Get a copy of all of the sides
		Side side0 = cube.getSide(0);
		Side side1 = cube.getSide(1);
		Side side2 = cube.getSide(2);
		Side side3 = cube.getSide(3);
		Side side4 = cube.getSide(4);
		Side side5 = cube.getSide(5);
		
		// Rotate 5 to the right once
		side5.rotateRight();
		
		// Display side 5
		currSideMatrix = side5.getSide();
	
		for (int i=0; i< dimension; i++) {
			System.out.format("%" + 2*dimension + "s\n", currSideMatrix[i]);
		}
			
		// Rotate side 0-3 to the left once
		side0.rotateLeft();
		side1.rotateLeft();
		side2.rotateLeft();
		side3.rotateLeft();

		// Display the last three sides
		for (int i=0; i < dimension; i++) {
			System.out.format("%s%s%s%s\n", 
					side0.getSide()[i], 
					side1.getSide()[i], 
					side2.getSide()[i],
					side3.getSide()[i]);
		}

		// Rotate 4 to the right once
		side4.rotateRight();
		
		// Display side 4
		currSideMatrix = side4.getSide();
		
		for (int i=0; i< dimension; i++) {
			System.out.format("%" + 2*dimension + "s\n", currSideMatrix[i]);
		}
	}	

}
