package screens;

import java.io.IOException;
import java.util.ArrayList;

import maths.RandomLocation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;
import controllers.Score;
import controllers.Settings;
import entities.Bullet;
import entities.Enemy;
import entities.WordList;


public class Play extends BasicGameState{
	
	public static WordList words;
	
	private boolean wordListGenerated;
	private Image gameBackground;						// This is the games background
	// private Image turret90;								// This is the turret at the bottom of the screen that fires
	private ArrayList<Enemy> enemiesOnScreen;			// An ArrayList of the current enemies on the screen.
	private ArrayList<Bullet> bulletList;  				// An ArrayList of bullets on the screen.
	
	// These variables store the mouses x and y coordinates.
	double mouseX;
	double mouseY;
	


	private TextField wordEnteredTF;			// User input text field.
	private TextField scoreTF;					// Score text field.
	private Score score;						// Score variable.
	private TextField healthTF;					// Health text field.
	private Color black;						// The colour black.
	private boolean clear;						// A boolean variable to keep track of when to clear the user input text field.
	private int randX;							// A variable to store random x location numbers for enemy spawning.
	private int time;							// The time that the level has been running.
	private boolean wordAlreadyExists;			// A boolean variable to see if a Word List has already been made or not

	private RandomLocation xLoc;	// Will be used to create a random x-coordinate.
	
	private boolean alreadySpawned;
	
	public Play(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initializes the background image
		gameBackground = new Image("res/gamebg.png");
		// turret90 = new Image ("/res/turrets/standard/turret90.png");
	
		// Initializes the color black.
		black = new Color(0,0,0);
		
		// Initializes the keyboard input text field.
		wordEnteredTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 0, 380, 200, 20);
	
		// Initializes the score text field.
		scoreTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 200, 380, 150, 20);
		
		// Initializes the score.
		score = new Score();
		
		// Initializes the health text field.
		healthTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 350, 380, 110, 20);
		
		// Initializes the player's health to 100.
		Settings.health = 100;
		
		// Initializes the random location generator xLoc.
		xLoc = new RandomLocation();
		
		// Initializes the enemies on screen ArrayList.
		enemiesOnScreen = new ArrayList<Enemy>();
		
		// Initializes the bullets on the screen ArrayList.
		bulletList = new ArrayList<Bullet>();
		
		// Initializes the boolean variable to whether or not a Word List has been generated to false.
		wordListGenerated = false;
		
		// Initializes the level time to 0 seconds.
		time = 0;
		
		alreadySpawned = false;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		// Draws the lvel's background image.
		g.drawImage(gameBackground, 0 ,0);

		// Draws the turret.
		// g.drawImage(turret90, 285, 300);

		// Draws the input text field.
		wordEnteredTF.render(gc, g);
		wordEnteredTF.setBackgroundColor(black);
		wordEnteredTF.setFocus(true);
		
		// Clears the text field when enter is pressed.
		if(clear){
			wordEnteredTF.setText("");
			clear = false;
		}

		// Draws the score text field.
		scoreTF.render(gc, g);
		scoreTF.setBackgroundColor(black);
		scoreTF.setText("Score: " + Settings.score);
		
		// Draws the health text field.
		healthTF.render(gc, g);
		healthTF.setBackgroundColor(black);
		healthTF.setText("Health: " + Settings.health);

		// Draw the mouse coordinates.
		g.drawString("" + mouseX, 0, 70);
		g.drawString("" + mouseY, 0, 100);


		// Draw the bullets
		g.setColor(Color.red);
		for(int i = 0; i < bulletList.size(); i++){
			Bullet bullet = bulletList.get(i);
			g.fillRect(bullet.returnX(), bullet.returnY(), 5, 5);
		}

		// Draw the enemies.
		for(int i = 0; i < enemiesOnScreen.size(); i++){
			Enemy enemy = enemiesOnScreen.get(i);
			g.drawImage(enemy.getImage(), (float)enemy.returnX(), (float)enemy.returnY());
			
			// Draws a black rectangle above the enemy, varying in size by the size of the word associated to the enemy.
			g.setColor(Color.black);
			float rectXSize = (enemy.getWord().length() * 10) + 2;
			g.fillRect((float)enemy.returnX() + 15, (float)enemy.returnY() - 5, rectXSize, 20);
			
			// Draws a red word in that black rectangle.
			g.setColor(Color.red);
			g.drawString(enemy.getWord(), (float)enemy.returnX() + 15, (float)enemy.returnY() - 5);	
		}
		
		// Draws the time on the screen.
		g.drawString("Time: " + time/1000, 500, 50);
		
		if (time/1000 == 1 && !alreadySpawned){
			addNewEnemy();
			alreadySpawned = true;
			System.out.println("Test");
		}
		
		
	}
		
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		// If there's not a Word List already.
		if (!wordListGenerated){
			try {
				// Makes a new Word List
				words = new WordList();
				// Fills it with words.
				words.populateList();
				// Sets the boolean variable to true to indicated that a Word List has been created.
				wordListGenerated = true;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("An exception occurred while making a Word List.");
			}
		}
		
		// Increments the time based on delta.
		time += delta;
		
		// A generic input collector.
		Input input = gc.getInput();
		
		// Get the mouses x and y coordinates.
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		// Update the score when a target is hit. and set the clear variable to true.
		if (input.isKeyPressed(Input.KEY_ENTER)){
		    for(int i = 0; i < enemiesOnScreen.size(); i++){
		    	Enemy enemy = enemiesOnScreen.get(i);
		    	if(wordEnteredTF.getText().equals(enemy.getWord())){
		    		addNewBullet((float)enemy.returnX(), (float) enemy.returnY());
		    		
		    		score.enemyKilled(enemy.getWord().length());
	
					// Remove the enemy from the screen.
		    		enemiesOnScreen.remove(i);	
		    		
				    input.clearKeyPressedRecord();
		    	}
		    	
		    	// If the user hit enter but the word was incorrect.
		    	else {
		    		if(!wordEnteredTF.getText().equals("")){
		    			score.missedEnemy();
		    		    input.clearKeyPressedRecord();
		    		}
		    	}
		    	
		    }
		    // Clears the input text field.
		    clear = true;													

		    
		    addNewEnemy();										
		}

		// If input box is clicked, then set the clear variable to true.
		if (mouseX <= 200 && mouseY >= 380 && input.isMouseButtonDown(0)){
			// Clears the input text field.
			clear = true;
		}
		
		// If the text field isn't currently in focus, make it in focus.
		if (!wordEnteredTF.hasFocus()){
			wordEnteredTF.setFocus(true);
		}
		
		//Update the bullet's position.
		for(int i = 0;i<bulletList.size();i++){
			Bullet bullet = bulletList.get(i);
			bullet.move();
	  	  
			// Removes bullets that go off screen.
			if ((bullet.returnX() > 639 || bullet.returnX() <= 1) || (bullet.returnY() < 1 || bullet.returnY() > 399)){
	  		  	bulletList.remove(i);     
			}
	      
		}
		
		// Update the enemies' positions.
		for(int i = 0; i < enemiesOnScreen.size(); i++){
			Enemy enemy = enemiesOnScreen.get(i);
			
			enemy.move();
			
			// Removes enemies that go off screen and decrements the player's health by 10.
			if (enemy.returnY() > 369 ){
				enemiesOnScreen.remove(i);
				// Settings.health -= 10;
				
				if(Settings.health == 0){
					sbg.addState(new Death(Game.DEATH_STATE));
					sbg.getState(Game.DEATH_STATE).init(gc, sbg);
					sbg.enterState(Game.DEATH_STATE);
				}
				
			}
	      
		}		
		
	}
	
	
	   /* (non-Javadoc)
	    * @see org.newdawn.slick.BasicGame#mousePressed(int, int, int)
	    */
	   @Override
	   public void mousePressed ( int button, int x, int y ){
		   try {addNewEnemy();
		   } catch (SlickException e) {
				   e.printStackTrace();
		   }
	   }
	   
	   /**
	    * Shoots a bullet at the current target.
	    * @param x
	    * @param y
	    */
	   private void addNewBullet(float targetX, float targetY){
	      bulletList.add(new Bullet(330, 340, targetX, targetY));
	   }
	   
	   private void addNewEnemy() throws SlickException{
		   // Gets a new random X location.
		   randX = xLoc.getX();
		   
		   // Makes an enemy at (randX, 0), but doesn't display it on the screen.
		   Enemy enemy = new Enemy(randX);
		   
		   // Runs at least once, then loops if the new enemy's word is the same as any of the current enemies.
		   do{
			   // Words are innocent until proven guilty.
			   wordAlreadyExists = false;
			   // If there are still words that haven't been used.
			   if (enemiesOnScreen.size() < words.size() && enemiesOnScreen.size() != 0){
				   // Loops through enemies currently on the screen.
				   for(int i = 0; i < enemiesOnScreen.size(); i++){
					   // Check to see if the new enemy has a word that already exists.
					   if(enemy.getWord().equals(enemiesOnScreen.get(i).getWord())){
						   // If it does then keep the boolean variable true.
						   wordAlreadyExists = true;
						   // Set a new word for the enemy.
						   enemy.setWord();
						   // Break out of the for loop because it only needs to match against one other so going further is pointless.
						   break;
					   }
				   }
			   }
			   // If there are currently no enemies on the screen, go ahead and add one.
			   else if (enemiesOnScreen.size() == 0){
				   wordAlreadyExists = false;
			   } 
			   // If there are not anymore unused words in the list.
			   else if(enemiesOnScreen.size() == words.size()){
				   // The word must already exist.
				   wordAlreadyExists = true;
				   // But we need to break out because that will remain true until an enemy is shot or reaches the bottom.
				   break;
			   }
		   } while(wordAlreadyExists);
		   
		   if (!wordAlreadyExists){
			   // Add the enemy to the screen.
			   enemiesOnScreen.add(enemy);
		   }
	   }

	   
	public int getID(){
		return Game.PLAY_STATE;
	}
}

	
