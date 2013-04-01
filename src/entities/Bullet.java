package entities;

import java.awt.Point;

/**
 * This class creates bullet objects and calculates their movement.
 * Bullets are fired at Enemies.
 *
 * @author Jonathan Sterling
 *
 */
public class Bullet{
	private int startX = 0;						// The starting x-coordinate of the bullet.
	private int startY = 0;						// The starting y-coordinate of the bullet.
	private float destX = 0;					// The destination x-coordinate of the bullet.
	private float destY = 0;					// The destination y-coordinate of the bullet.
	private Point location = new Point(0,0);	// The bullet's location.
	private float speed = 60;					// How fast the bullet moves.
	private float dx;							// Delta X.  The change in position X.
	private float dy;							// Delta Y.  The change in position Y.


	public Bullet(int startX, int startY, float destX, float destY)
	{
		// Initialises variables.
		this.startX = startX;
		this.startY = startY;
		location.setLocation(startX, startY);
		this.destX = destX;
		this.destY = destY;

		// Calculates where the bullet needs to go.
		recalculateVector(this.destX, this.destY);

		// Sets the bullet's speed.
		speed = 60;
	}

	/**
	 * Calculates the vector in which the bullet must travel to hit the target.
	 *
	 * @param destX The x-coordinate of the enemy.
	 * @param destY The y-coordinate of the enemy.
	 */
	public void recalculateVector(float destX, float destY){
		// Calculates the angle that the bullet must travel in radians.
		float rad = (float)(Math.atan2(destX - startX, startY - destY));

		// Calculates the change in x and y based on the angle.
		this.dx = (float) Math.sin(rad) * speed;
		this.dy = -(float) Math.cos(rad) * speed;
	}

	/**
	 * Moves the bullet.
	 */
	public void move()
	{
		// Gets the bullets current location.
		float x = (float) location.getX();
		float y = (float) location.getY();

		// Sets the new location based on the dx and dy calculated in recalculateVector().
		x += dx;
		y += dy;

		// Updates the location of the bullet.
		location.setLocation(x, y);
	}

	/**
	 * Gets the bullet's x-coordinate.
	 *
	 * @return The bullet's x-coordinate.
	 */
	public float returnX(){
		return (float)location.getX();
	}

	/**
	 * Gets the bullet's y-coordinate.
	 *
	 * @return The bullet's y-coordinate.
	 */
	public float returnY(){
		return (float)location.getY();
	}
}