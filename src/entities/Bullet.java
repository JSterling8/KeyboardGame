package entities;

import java.awt.Point;

public class Bullet{ 
    private int startX = 0;
    private int startY = 0;
    private float destX = 0;
    private float destY = 0;
    private Point location = new Point(0,0);
    private float speed = 60; //how fast this moves.
    private float dx;
    private float dy;
    
    
    public Bullet(int startX, int startY, float destX, float destY)
    {
       this.startX = startX;
       this.startY = startY;
       location.setLocation(startX, startY);
       this.destX = destX;
       this.destY = destY;
       recalculateVector(destX, destY);

    }
    
    /**
     * Calculates a new vector based on the input destination X and Y.
     * @param destX
     * @param destY
     */
    public void recalculateVector(float destX, float destY){
       float rad = (float)(Math.atan2(destX - startX, startY - destY));
       
       //Can set different speeds here, if you wanted.
       speed = 60;
       
       this.dx = (float) Math.sin(rad) * speed;
       this.dy = -(float) Math.cos(rad) * speed;
    }
    
    /**
     * Recalculates the vector for this bullet based on the current destination.
     */
    public void recalculateVector()
    {
       recalculateVector(destX, destY);
    }
    
    /**
     * Moves this bullet.
     */
    public void move()
    {
       float x = (float) location.getX();
       float y = (float) location.getY();
       
       x += dx;
       y += dy;
       
       location.setLocation(x, y);
    }
    
    public float returnX(){
    	return (float)location.getX();
    }
    
    public float returnY(){
    	return (float)location.getY();
    }

}