package controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import entities.Score;

public class ScoreBoardConTest {

	private ScoreBoardCon sbc;
	private Score score1;
	private Score score2;
	
	@Before
	public void setUp() throws Exception {
		sbc = new ScoreBoardCon();
		score1 = new Score("Jonathan", 1000);
		score2 = new Score ("Jonathan", 2000);
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
		assertEquals(sbc.toString(), (score1.toString()));
		
		// Add score two and assert that .toString() returns both score1 and score2 correctly.
		sbc.addScore(score2);
		assertEquals(sbc.toString(), (score1.toString() + score2.toString()));
		
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

		// Add two scores to the highscores.
		sbc.addScore(score1);
		sbc.addScore(score2);
		
		// Save them
		try {
			sbc.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Make a new scoreboard controller (autoloads save file if it exists)
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

	@Test
	public void testClear() {
		// Add a score.
		sbc.addScore(score1);
		
		// Clear the scores.
		sbc.clear();
		
		// Make sure that there are no scores.
		assertEquals("", sbc.toString());
	}

}
