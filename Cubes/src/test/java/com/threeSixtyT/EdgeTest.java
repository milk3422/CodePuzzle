package com.threeSixtyT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A class to ensure that the {@code Edge} object works correctly.
 * 
 * @author Zachary Radtka
 */
public class EdgeTest {

	/** A sample Edge */
	private static final String EDGE = "110010001";

	/** An Edge that matches the sample Edge */
	private static final String MATCHING_EDGE = "001101110";

	/** An Edge used in all of the tests */
	private Edge testEdge;

	/**
	 * Initialize the test Edge
	 */
	@Before
	public void setup() {
		testEdge = new Edge(EDGE);
	}

	/**
	 * Ensure getLegth returns the correct value
	 */
	@Test
	public void testGetLength() {
		int expected = EDGE.length();
		assertEquals(expected, testEdge.getLength());
	}

	/**
	 * Ensure the getEdge method returns the correct result
	 */
	@Test
	public void testGet() {
		Boolean[] expected = { true, true, false, false, true, false, false,
				false, true };
		assertArrayEquals(expected, testEdge.getEdge());
	}

	/**
	 * Ensure reverse returns the correct result
	 */
	@Test
	public void testReverse() {
		Boolean[] expected = { true, false, false, false, true, false, false,
				true, true };
		testEdge.reverse();
		assertArrayEquals(expected, testEdge.getEdge());
	}

	/**
	 * Ensure equals functions properly
	 */
	@Test
	public void testEquals0() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("0");
		assertTrue("0 is equal to 0", expected.equals(actual));
	}

	/**
	 * Ensure equals functions properly
	 */
	@Test
	public void testEquals1() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("1");
		assertFalse("0 is not equal to 1", expected.equals(actual));
	}

	/**
	 * Ensure equals functions properly
	 */
	@Test
	public void testEquals2() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("0");
		assertFalse("1 is not equal to 0", expected.equals(actual));
	}

	/**
	 * Ensure equals functions properly
	 */
	@Test
	public void testEquals3() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("1");
		assertTrue("1 is equal to 1", expected.equals(actual));
	}

	/**
	 * Ensure equals functions properly
	 */
	@Test
	public void testEquals4() {
		Edge expected = new Edge("110010001");
		assertTrue("Edges should be the same", expected.equals(testEdge));
	}

	/**
	 * Ensure equals returns false for an edges that have different lengths
	 */
	@Test
	public void testEqualsTooShort() {
		Edge expected = new Edge("1100100");
		assertFalse("Edges being compared are of different length",
				expected.equals(testEdge));
	}

	/**
	 * Ensure equals returns false on a mismatch
	 */
	@Test
	public void testEqualsMissmatch() {
		Edge expected = new Edge(MATCHING_EDGE);
		assertFalse("Edges are not equal", expected.equals(testEdge));
	}

	/**
	 * Ensure equals operates correctly on inversion cases
	 */
	@Test
	public void testEqualsInverted() {
		Edge expected = new Edge("100010011");
		assertTrue(expected.equals(testEdge, true));
	}
	
	/**
	 * Ensure matches functions correctly
	 */
	@Test
	public void testMatches() {
		Edge expected = new Edge(MATCHING_EDGE);
		assertTrue(expected.matches(testEdge));
	}

	/**
	 * Ensure matches returns false for edges of a different length 
	 */
	@Test
	public void testMatchesTooShort() {
		Edge expected = new Edge(MATCHING_EDGE.substring(1));
		assertFalse("Edges being compared are of a different length", expected.matches(testEdge));
	}

	@Test
	public void testMatches0() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("1");

		assertTrue(expected.matches(actual));
	}

	/**
	 * Ensure matches functions correctly
	 */
	@Test
	public void testMatches1() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("0");

		assertTrue("1 matches 0", expected.matches(actual));
	}

	/**
	 * Ensure matches functions correctly
	 */
	@Test
	public void testMatches2() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("1");

		assertFalse("1 does not match 1", expected.matches(actual));
	}

	/**
	 * Ensure matches functions correctly
	 */
	@Test
	public void testMatchesMissmatch() {
		Edge expected = new Edge("0101");
		Edge actual = new Edge("0110");
		assertFalse("The two edges do not match", expected.matches(actual));
	}

	/**
	 * Ensure matches functions correctly
	 */
	@Test
	public void testMatchesInverted() {
		Edge expected = new Edge("011101100");
		assertTrue(expected.matches(testEdge, true));
	}

	/**
	 * Ensure isSymmetrical functions correctly
	 */
	@Test
	public void testMatchesSymmetrical0() {
		Edge edge = new Edge("1");
		assertTrue("1 is symmetrical", edge.isSymmetrical());
	}

	/**
	 * Ensure isSymmetrical functions correctly
	 */
	@Test
	public void testMatchesSymmetrical1() {
		Edge edge = new Edge("11");
		assertTrue("11 is symmetrical", edge.isSymmetrical());
	}

	/**
	 * Ensure isSymmetrical functions correctly
	 */
	@Test
	public void testMatchesSymmetrical2() {
		Edge edge = new Edge("10");
		assertFalse("10 is not symmetrical", edge.isSymmetrical());
	}

	/**
	 * Ensure isSymmetrical functions correctly
	 */
	@Test
	public void testMatchesSymmetrical3() {
		Edge edge = new Edge("101");
		assertTrue("101 is symmetrical", edge.isSymmetrical());
	}

	/**
	 * Ensure isSymmetrical functions correctly
	 */
	@Test
	public void testMatchesSymmetrical4() {
		Edge edge = new Edge("100");
		assertFalse("100 is not symmetrical", edge.isSymmetrical());
	}
}
