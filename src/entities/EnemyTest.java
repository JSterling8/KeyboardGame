package entities;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class EnemyTest {

	Enemy enemy;
	
	/**
	 * Will throw exceptions when testing alone because it relies on the Play class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testReturnX() throws SlickException {
		enemy = new Enemy(50);
		assertTrue(enemy.returnX() == 55);
	}

	@Test
	public void testReturnY() throws SlickException {
		enemy = new Enemy(0);
		assertTrue(enemy.returnY() == 5);
	}

}
