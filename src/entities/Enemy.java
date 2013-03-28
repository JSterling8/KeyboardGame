package entities;

import java.awt.geom.Ellipse2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import screens.Play;
import controllers.RandomLocation;
import controllers.Settings;

public class Enemy extends Ellipse2D.Float{

	private static final long serialVersionUID = -3945376987588701682L;
	private int startX;
	private Ellipse2D.Float location;
	private String word;
	private int i;
    private boolean paused;						
    private int speed;							// The lower the number, the higher the speed.  It has to do with how often the objects location is  updated.

    
    public Enemy(int startX) throws SlickException{
    	this.startX = startX;
    	location = new Ellipse2D.Float((float)startX, (float)0,10,10);
    	location.setFrame((float)startX, 0, 10, 10);

    	if(Settings.difficulty == 4){speed = 5;}
    	else if(Settings.difficulty == 3){speed = 10;}
    	else if(Settings.difficulty == 2){speed = 15;}
    	else if(Settings.difficulty == 1){speed = 20;}

    	setWord();
    }
    
    
    /**
     * Moves this enemy.
     */
    public void move(){
    	float y = (float) returnY();
    	
    	if (i == speed + 1){
    		i = 0;
    	}
    	
    	// If it's the right frame, and the game is not paused, then move the enemy one pixel down.
    	if (i == speed && !paused){
    		y -= 1;
        	location.setFrame(startX, y, 10, 10);
    	}
    	
    	i++;
    } 
    
    /**
     * @return The centre of the framing rectangle around the ellipse that is the enemy.
     */
    public double returnX(){
    	return location.getCenterX();
    }

    
    public double returnY(){
    	return location.getCenterY();
    }
    
    public void setWord(){
		word = Play.words.getWord();

    }
    
    public String getWord(){
    	return word;
    }
    
    public void pause(){
    	paused = true;
    }
    
    public void unpause(){
    	paused = false;
    }
 }	 