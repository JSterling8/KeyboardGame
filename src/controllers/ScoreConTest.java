package controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ScoreConTest {

	private ScoreCon sc;

	@Before
	public void setUp() throws Exception {
		sc = new ScoreCon();
		Settings.totalKilled = 0;

	}

	/**
	 * Positive and negative tests.
	 * Ensures that the multiplier works.
	 * Ensures that the recursive summative addition loop works correctly.
	 * Ensures that the score is added to correctly.
	 * Ensures that the numConsecMult works correctly.
	 */
	@Test
	public void testEnemyKilled() {
		// Set everything to defaults.
		Settings.score = 0;
		Settings.totalKilled = 0;
		Settings.totalMissed = 0;
		sc = new ScoreCon();

		// Set the difficulty to one and check the that multiplier is equal to the difficulty.
		Settings.difficulty = 1;
		sc.setDefaultMultiplier();
		assertEquals(1, sc.getMultiplier());

		// Kill an enemy with a word length of 5 and check that total killed is 1 and the score is 150 (10+20+30+40+50)
		sc.enemyKilled(5);
		assertEquals(1, Settings.totalKilled);
		assertEquals(150, Settings.score);
		// Set the score and totalKilled back to 0 for the next test.
		Settings.score = 0;
		Settings.totalKilled = 0;
		// Sets the number consecutive multiplier back down to 0.
		sc.missedEnemy();

		// Set the difficulty to two and make sure that the multiplier is 2.
		Settings.difficulty = 2;
		sc.setDefaultMultiplier();
		assertEquals(2, sc.getMultiplier());

		// Kill two enemies with wordlength of 5.
		// Make sure that total killed is 2 and 
		// that the score is 600 ((10+20+30+40+50) * 2words * 2multiplier)
		sc.enemyKilled(5);
		sc.enemyKilled(5);
		assertEquals(2, Settings.totalKilled);
		assertEquals(600, Settings.score);

		// Negative test.  Make sure nothing happens if the input is negative or 0.
		sc.enemyKilled(-1);
		assertEquals(2, Settings.totalKilled);
		assertEquals(600, Settings.score);
		sc.enemyKilled(0);
		assertEquals(2, Settings.totalKilled);
		assertEquals(600, Settings.score);

		// Put everything back to its default.
		sc.missedEnemy();
		Settings.totalKilled = 0;
		Settings.score = 0;

		// Put the difficulty to 3 and assert that the multiplier is 3.
		Settings.difficulty = 3;
		sc.setDefaultMultiplier();
		assertEquals(3, sc.getMultiplier());

		// Put the difficulty to 4 and assert that the multiplier is 4.
		Settings.difficulty = 4;
		sc.setDefaultMultiplier();
		assertEquals(4, sc.getMultiplier());

		// Makes a new ScoreCon and reset everything to defaults.
		sc = new ScoreCon(); 
		Settings.totalMissed = 0;
		Settings.score = 0;
		Settings.totalKilled = 0;

		// Simulate killing 40 enemies.
		for (int i = 0; i < 40; i++){
			sc.enemyKilled(1);
		}
		// Assert that the multiplier is the difficulty(4) + the numConsecMult(should now be 6)
		assertEquals(10, sc.getMultiplier());

	}

	/**
	 * Tests that the recursive letterBonus() method returns the correct number.
	 */
	@Test
	public void testLetterBonus() {
		// Positive tests.
		assertEquals(10, sc.letterBonus(1));
		assertEquals(30, sc.letterBonus(2));
		assertEquals(60, sc.letterBonus(3));
		assertEquals(100, sc.letterBonus(4));
		assertEquals(150, sc.letterBonus(5));

		// Negative tests.
		assertEquals(0, sc.letterBonus(0));
		assertEquals(0, sc.letterBonus(-1));
	}

	/**
	 * Tests that the multiplier gets set back to its default and 
	 * that the totalMissed variable is incremented.
	 */
	@Test
	public void testMissedEnemy() {
		// Put everything at its default.
		Settings.totalMissed = 0;
		Settings.difficulty = 1;
		sc = new ScoreCon();

		// Make sure the multiplier is just 1.
		assertEquals(1, sc.getMultiplier());

		// Simulate killing 5 enemies.
		for (int i = 0; i < 5; i++){
			sc.enemyKilled(1);
		}

		// Make sure that the multiplier is now 2.
		assertEquals(2, sc.getMultiplier());

		// Simulate missing an enemy.
		sc.missedEnemy();

		// Assert that the multiplier is 1
		assertEquals(1, sc.getMultiplier());
		// Assert that the totalMissed is 1;
		assertEquals(1, Settings.totalMissed);
	}

	/**
	 * Tests that the multiplier is correctly returned.
	 */
	@Test
	public void testGetMultiplier() {
		// Set relevant variables to defaults.
		Settings.totalMissed = 0;
		Settings.totalKilled = 5;
		Settings.difficulty = 1;
		sc = new ScoreCon();	

		// Make sure that the multiplier is the same as the difficulty.
		assertEquals(1, sc.getMultiplier());

		// Simulate killing 5 enemies.
		for (int i = 0; i < 5; i++){
			sc.enemyKilled(1);
		}

		// Make sure that the multiplier is the difficulty (1) plus the numConsecKilled bonus (1).
		assertEquals(2, sc.getMultiplier());

		// Simulate killing 5 enemies.
		for (int i = 0; i < 5; i++){
			sc.enemyKilled(1);
		}

		// Make sure that the multiplier is the difficulty (1) plus the numConsecKilled bonus (2).
		assertEquals(3, sc.getMultiplier());

		// Simulate killing 5 enemies.
		for (int i = 0; i < 5; i++){
			sc.enemyKilled(1);
		}

		// Make sure that the multiplier is the difficulty (1) plus the numConsecKilled bonus.
		assertEquals(4, sc.getMultiplier());

		// Simulate killing 5 enemies.
		for (int i = 0; i < 5; i++){
			sc.enemyKilled(1);
		}

		// Make sure that the multiplier is the difficulty (1) plus the numConsecKilled bonus.
		assertEquals(5, sc.getMultiplier());

		// Simulate killing 20 enemies.
		for (int i = 0; i < 20; i++){
			sc.enemyKilled(1);
		}
		// Make sure that the multiplier is the difficulty plus the numConsecKilled bonus.
		assertEquals(7, sc.getMultiplier());
		
		// Make sure that if an enemy is missed the multiplier goes back to 1.
		sc.missedEnemy();
		assertEquals(1, sc.getMultiplier());
		
		// Make sure that the multiplier equals the difficulty if no enemies have been killed.
		Settings.difficulty = 2;
		sc.setDefaultMultiplier();
		assertEquals(2, sc.getMultiplier());
		Settings.difficulty = 3;
		sc.setDefaultMultiplier();
		assertEquals(3, sc.getMultiplier());
		Settings.difficulty = 4;
		sc.setDefaultMultiplier();
		assertEquals(4, sc.getMultiplier());
	}

	/**
	 * Positive and negative tests to make sure that the 
	 * default multiplier is always set from 1-4.
	 */
	@Test
	public void testSetDefaultMultiplier() {
		// Positive tests.
		Settings.difficulty = 1;
		sc.setDefaultMultiplier();
		assertEquals(1, sc.getMultiplier());
		Settings.difficulty = 2;
		sc.setDefaultMultiplier();
		assertEquals(2, sc.getMultiplier());
		Settings.difficulty = 3;
		sc.setDefaultMultiplier();
		assertEquals(3, sc.getMultiplier());
		Settings.difficulty = 4;
		sc.setDefaultMultiplier();
		assertEquals(4, sc.getMultiplier());
		
		// Negative tests.
		Settings.difficulty = 0;
		sc.setDefaultMultiplier();
		assertEquals(1, sc.getMultiplier());
		Settings.difficulty = -1;
		sc.setDefaultMultiplier();
		assertEquals(1, sc.getMultiplier());
	}

}
