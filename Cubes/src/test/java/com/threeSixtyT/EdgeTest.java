package com.threeSixtyT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTest {

	private static final String EDGE = "110010001";
	private static final String MATCHING_EDGE = "001101110";
	
	private Edge testEdge;
	
	@Before
	public void setup() {
		testEdge = new Edge(EDGE);
	}
	
	@Test
	public void testGetLength() {
		int expected = EDGE.length();
		assertEquals(expected, testEdge.getLength());
	}

	@Test
	public void testGet() {
		Boolean[] expected = {true, true, false, false, true, false, false, false, true};		
		assertArrayEquals(expected, testEdge.getEdge());
	}

	@Test
	public void testReverse() {
		Boolean[] expected = {true, false, false, false, true, false, false, true, true};		
		testEdge.reverse();
		assertArrayEquals(expected, testEdge.getEdge());
	}
	
	@Test
	public void testEquals0() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("0");
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testEquals1() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("1");
		assertFalse(expected.equals(actual));
	}
	
	@Test
	public void testEquals2() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("0");
		assertFalse(expected.equals(actual));
	}
	
	@Test
	public void testEquals3() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("1");
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testEquals() {
		Edge expected = new Edge("110010001");
		assertTrue(expected.equals(testEdge));
	}
	
	@Test
	public void testEqualsTooShort() {
		Edge expected = new Edge("1100100");
		assertFalse(expected.equals(testEdge));
	}
	
	@Test
	public void testEqualsMissmatch() {
		Edge expected = new Edge(MATCHING_EDGE);
		assertFalse(expected.equals(testEdge));
	}

	@Test
	public void testEqualsInverted() {
		Edge expected = new Edge("100010011");
		assertTrue(expected.equals(testEdge, true));
	}
	
	@Test
	public void testMatches() {
		Edge expected = new Edge(MATCHING_EDGE);
		assertTrue(expected.matches(testEdge));
	}
	
	@Test
	public void testMatchesTooShort() {
		Edge expected = new Edge(MATCHING_EDGE.substring(1));
		assertFalse(expected.matches(testEdge));
	}
	
	@Test
	public void testMatchesMissmatch0() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("0");
		
		assertFalse(expected.matches(actual));
	}
	
	@Test
	public void testMatches0() {
		Edge expected = new Edge("0");
		Edge actual = new Edge("1");
		
		assertTrue(expected.matches(actual));
	}
	
	@Test
	public void testMatches1() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("0");
		
		assertTrue(expected.matches(actual));
	}
	
	@Test
	public void testMatches2() {
		Edge expected = new Edge("1");
		Edge actual = new Edge("1");
		
		assertFalse(expected.matches(actual));
	}
	
	@Test
	public void testMatchesMissmatch() {
		Edge expected = new Edge("000101110");
		assertFalse(expected.matches(testEdge));
	}
	
	@Test
	public void testMatchesInverted() {
		Edge expected = new Edge("011101100");
		assertTrue(expected.matches(testEdge, true));
	}

	@Test
	public void testMatchesSymmetrical0() {
		Edge edge = new Edge("1");
		assertTrue(edge.isSymmetrical());
	}
	
	@Test
	public void testMatchesSymmetrical1() {
		Edge edge = new Edge("11");
		assertTrue(edge.isSymmetrical());
	}
	
	@Test
	public void testMatchesSymmetrical2() {
		Edge edge = new Edge("10");
		assertFalse(edge.isSymmetrical());
	}
	
	@Test
	public void testMatchesSymmetrical3() {
		Edge edge = new Edge("101");
		assertTrue(edge.isSymmetrical());
	}
	
	@Test
	public void testMatchesSymmetrical4() {
		Edge edge = new Edge("100");
		assertFalse(edge.isSymmetrical());
	}
}
