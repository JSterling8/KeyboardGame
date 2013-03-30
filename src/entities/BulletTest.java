package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BulletTest {

	Bullet bullet;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReturnX() {
		bullet = new Bullet(50, 50, 0, 0);
		assertTrue(bullet.returnX() == 50);
	}

	@Test
	public void testReturnY() {
		bullet = new Bullet(50, 50, 0, 0);
		assertTrue(bullet.returnY() == 50);
	}

}
