package com.threeSixtyT;

import static org.junit.Assert.*;

import org.junit.Test;

public class SideTest {

	/** A fully symmetrical shape */
	private static final String[] SYMMETRICAL_SHAPE = {
		"00110",
		"11110",
		"11111",
		"01111",
		"01100"};
	
	/** A shape that should only be rotated once */
	private static final String[] HALF_SYMMETRICAL_SHAPE = {
		"11011",
		"01110",
		"11111",
		"01110",
		"11011"};
	
	/** A shape mirrored along the horizontal, x, axis */
	private static final String[] HORIZONTAL_MIRROR_SHAPE = {
		"01100",
		"11110",
		"11111",
		"11110",
		"01100"};
	
	/** A shape mirrored along the vertical, y, axis */
	private static final String[] VERTICAL_MIRROR_SHAPE = {
		"01110",
		"01110",
		"11111",
		"11111",
		"00100"};
	
	/** A shape that support full rotation */
	private static final String[] FULL_ROTATION_SHAPE = {
		"01111",
		"11111",
		"11110",
		"01111",
		"00110"};
	

	
	/**
	 * Ensure the top edge is returned correctly
	 */
	@Test
	public void getTopEdgeTest() {
		Edge expected = new Edge("01111");
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertTrue(expected.equals(shape.getTopEdge()));
	}
	
	/**
	 * Ensure the right edge is returned correctly
	 */
	@Test
	public void getRightEdgeTest() {
		Edge expected = new Edge("11010");
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertTrue(expected.equals(shape.getRightEdge()));
	}
	
	/**
	 * Ensure the bottom edge is returned correctly
	 */
	@Test
	public void getBottomEdgeTest() {
		Edge expected = new Edge("01100");
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertTrue(expected.equals(shape.getBottomEdge()));
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void getLeftEdgeTest() {
		Edge expected = new Edge("00110");
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertTrue(expected.equals(shape.getLeftEdge()));
	}
	
	/**
	 * Ensure rotation works correctly
	 */
	@Test
	public void rotateRightTest() {
		Edge expectedTop = new Edge("00110");
		Edge expectedRight = new Edge("01111");
		Edge expectedBottom = new Edge("11010");
		Edge expectedLeft = new Edge("01100");

		Side shape = new Side(FULL_ROTATION_SHAPE);
		shape.rotateRight();
		assertTrue(shape.getTopEdge().equals(expectedTop)
				&& shape.getRightEdge().equals(expectedRight)
				&& shape.getBottomEdge().equals(expectedBottom)
				&& shape.getLeftEdge().equals(expectedLeft));
	}
	
	/**
	 * Ensure rotation works correctly
	 */
	@Test
	public void rotateLeftTest() {
		Edge expectedTop = new Edge("11010");
		Edge expectedRight = new Edge("01100");
		Edge expectedBottom = new Edge("00110");
		Edge expectedLeft = new Edge("01111");

		Side shape = new Side(FULL_ROTATION_SHAPE);
		shape.rotateLeft();
		assertTrue(shape.getTopEdge().equals(expectedTop)
				&& shape.getRightEdge().equals(expectedRight)
				&& shape.getBottomEdge().equals(expectedBottom)
				&& shape.getLeftEdge().equals(expectedLeft));
	}
	

	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void isFullySymmetricalTest0() {
		
		Side shape = new Side(SYMMETRICAL_SHAPE);
		assertTrue(shape.isFullySymmetrical());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void isFullySymmetricalTest1() {
		
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertFalse(shape.isFullySymmetrical());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void isFullySymmetricalTest2() {
		
		Side shape = new Side(HALF_SYMMETRICAL_SHAPE);
		assertFalse(shape.isFullySymmetrical());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void isPartiallySymmetricalTest0() {
		
		Side shape = new Side(HALF_SYMMETRICAL_SHAPE);
		assertTrue(shape.isPartiallySymmetrical());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void isPartiallySymmetricalTest1() {
		
		Side shape = new Side(FULL_ROTATION_SHAPE);
		assertFalse(shape.isPartiallySymmetrical());
	}

	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void containsReflectionTest0() {
		
		Side shape = new Side(HORIZONTAL_MIRROR_SHAPE);
		assertTrue(shape.containsReflection());
	}
	
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void containsReflectionTest1() {
		
		Side shape = new Side(VERTICAL_MIRROR_SHAPE);
		assertTrue(shape.containsReflection());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void containsReflectionTest2() {
		
		Side shape = new Side(SYMMETRICAL_SHAPE);
		assertFalse(shape.containsReflection());
	}
	
	/**
	 * Ensure the left edge is returned correctly
	 */
	@Test
	public void getSideTest() {
		String[] expected = {
				" oooo",
				"ooooo",
				"oooo ",
				" oooo",
				"  oo "};
		
		Side shape = new Side(FULL_ROTATION_SHAPE);		
		assertArrayEquals(expected, shape.getSide());
	}

	
	@Test
	public void flipTest() {
		String[] expected = {
				"oooo ",
				"ooooo",
				" oooo",
				"oooo ",
				" oo  "};
		
		Side shape = new Side(FULL_ROTATION_SHAPE);	
		shape.flip();
		assertArrayEquals(expected, shape.getSide());
	}

	
}
