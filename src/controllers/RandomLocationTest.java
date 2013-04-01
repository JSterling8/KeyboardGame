package controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RandomLocationTest {

	private RandomLocation randLoc;
	
	@Before
	public void setUp() throws Exception {
		
		randLoc = new RandomLocation();
		
	}

	/**
	 * Checks that getX() returns numbers within the correct ranges.
	 */
	@Test
	public void testGetX() {
		
		// Test for level 1 getX() returns numbers from 0-600.
		Settings.level = 1;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assertTrue(x <= 600 && x >= 0);
		}
		
		// Test for level 2 getX() returns numbers from 0-590.
		Settings.level = 2;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assertTrue(x <= 590 && x >= 0);
		}
		
		// Test for level 3 getX() returns numbers from 0-570.
		Settings.level = 3;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assert(x <= 570 && x >= 0);
		}
		
		// Test for level 4 getX() returns numbers from 0-550.
		Settings.level = 4;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assert(x <= 550 && x >= 0);
		}
		
		// Test for level 5 getX() returns numbers from 0-530.
		Settings.level = 5;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assert(x <= 530 && x >= 0);
		}
		
		// Test for level 6 getX() returns numbers from 0-510.
		Settings.level = 6;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assert(x <= 510 && x >= 0);
		}
		
		// Test for level 7 getX() returns numbers from 0-490.
		Settings.level = 7;
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getX();
			assert(x <= 490 && x >= 0);
		}
	}

	/**
	 * Checks that getRand(int max) returns numbers from 0-max
	 * @throws Exception If the number requested is less than 0.
	 */
	@Test
	public void testGetRand() throws Exception {
		for (int i = 0; i < 1000; i++){
			int x = randLoc.getRand(i);
			assertTrue(x <= i && x >= 0);
		}
	}
}
