package entities;

import java.awt.geom.Ellipse2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import screens.Play;
import controllers.RandomLocation;
import controllers.Settings;

public class Enemy extends Ellipse2D.Float{

	private int startX;							// The starting x-coordinate of the enemy.
	private Ellipse2D.Float location;			// The enemy's location.
	private String word;						// The word attached to the enemy.
	private int i;								// An iterator for the movement loop.
    private boolean paused;						// Is the game paused?		
    private int speed;							// The lower the number, the higher the speed.  It has to do with how often the objects location is updated.

    
    public Enemy(int startX) throws SlickException{
    	// Initialises things.
    	this.startX = startX;
    	location = new Ellipse2D.Float((float)startX, (float)0,10,10);
    	location.setFrame((float)startX, 0, 10, 10);

    	// Sets the speed based on difficulty.
    	if(Settings.difficulty == 4){speed = 5;}
    	else if(Settings.difficulty == 3){speed = 10;}
    	else if(Settings.difficulty == 2){speed = 15;}
    	else if(Settings.difficulty == 1){speed = 20;}

    	// Sets the word to be attached to the enemy.
    	setWord();
    }
    
    
    /**
     * Moves the enemy down every specific number of frames. 
     * That number of frames is determined by the speed.
     * i.e., at speed = 5, the enemy is moved every 5 frames,
     * whereas at speed = 10 it's updated every 10 frames.
     */
    public void move(){
    	// Sets a temporary variable y equal to the enemy's current y-coordinate.
    	float y = (float) returnY();
    	
    	// If it's one frame past the correct frame, set i back to 0.
    	if (i == speed + 1){
    		i = 0;
    	}
    	
    	// If it's the right frame, and the game is not paused, then move the enemy one pixel down.
    	if (i == speed && !paused){
    		y -= 1;
        	location.setFrame(startX, y, 10, 10);
    	}
    	
    	// Increment i.
    	i++;
    } 
    
    /**
     * @return The x-coordinate centre of the framing rectangle around the enemy.
     */
    public double returnX(){
    	return location.getCenterX();
    }

    /**
     * @return The y-coordinate centre of the framing rectangle around the enemy.
     */
    public double returnY(){
    	return location.getCenterY();
    }
    
    /**
     * Sets the word to be attached to the enemy.
     */
    public void setWord(){
		word = Play.words.getWord();

    }
    
    /**
     * @return The word attached to the enemy.
     */
    public String getWord(){
    	return word;
    }
    
    /**
     * Pauses enemy's movement.
     */
    public void pause(){
    	paused = true;
    }
    
    /**
     * Unpauses enemy's movement.
     */
    public void unpause(){
    	paused = false;
    }
 }	 