package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScoreTest {

	Score score;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		score = new Score("Jonathan", 100);
		assertTrue(score.toString().equals("Jonathan\nScore: 100\n"));
	}

	@Test
	public void testGetScore() {
		score = new Score("Jonathan", 100);
		assertEquals(100, score.getScore());
	}

	@Test
	public void testGetPlayerName() {
		score = new Score("Jonathan", 100);
		assertEquals("Jonathan", score.getPlayerName());
	}

}
