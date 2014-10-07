package com.threeSixtyT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

	public static final Integer NUM_SIDES = 6;


	public static void main(String[] args) {

		String cubeFile = null;
		String[][] inputCube = null;
		

		if (args.length != 1) {
			System.err.println("Usage: programName <cubeFile>");
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

		
		Cube cube = new Cube(inputCube);


		cube.solve();

	}

}
