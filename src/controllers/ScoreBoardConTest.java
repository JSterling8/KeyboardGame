package controllers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import entities.Score;

public class ScoreBoardConTest {

	private ScoreBoardCon sbc;
	private Score score1;
	private Score score2;
	private Score score3;
	
	@Before
	public void setUp() throws Exception {
		sbc = new ScoreBoardCon();
		score1 = new Score("Jonathan1", 1000);
		score2 = new Score ("Jonathan2", 2000);
		score3 = new Score ("Jonathan3", 3000);
	}

	/**
	 * Tests the addScore() method.
	 */
	@Test
	public void testAddScore() {
		// Clear the scores in case a save file is already there.
		sbc.clear();

		// Add score one and assert that .toString() returns it correctly.
		sbc.addScore(score1);
		assertEquals(sbc.toString(), ("1. " + score1.toString()));
		
		// Add score two and assert that .toString() returns both score1 and score2 correctly.
		sbc.addScore(score2);
		assertEquals(sbc.toString(), ("1. " + score2.toString() + "2. " + score1.toString()));
		
		// Clear the scores to clean up after this test.
		sbc.clear();
	}

	@Test
	public void testToString() {
		// No need to test as same as testAddScore();
	}

	@Test
	public void testSave() {
		// Clear the scores in case a save file is already there.
		sbc.clear();

		// Add two scores to the high scores.
		sbc.addScore(score1);
		sbc.addScore(score2);
		
		// Save them
		try {
			sbc.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Make a new scoreboard controller (auto-loads save file if it exists)
		ScoreBoardCon sbc2 = new ScoreBoardCon();
			
		// Make sure that sbc and sbc2 have the same information.
		assertEquals(sbc.toString(), sbc2.toString());
		
		// Clear the high scores to clean up after test.
		sbc.clear();
	}

	@Test
	public void testLoad() {
		// No need to test.  Tested in testSave().
	}

	/**
	 * Tests the clear() clears the high scores.
	 */
	@Test
	public void testClear() {
		// Add a score.
		sbc.addScore(score1);
		
		// Clear the scores.
		sbc.clear();
		
		// Make sure that there are no scores.
		assertEquals("", sbc.toString());
	}
	
	/**
	 * Makes sure the scores get sorted correctly.
	 */
	@Test
	public void testSortList() {
		sbc.clear();
		sbc.addScore(score3);
		sbc.addScore(score1);
		sbc.addScore(score2);
		
		// the toString() method in ScoreBoardCon calls sortList(), so there's no need to do it twice.
		assertEquals(sbc.toString(), "1. " + score3.toString() + 
									"2. " + score2.toString() + 
									"3. " + score1.toString());
	}

}
