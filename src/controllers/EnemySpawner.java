package controllers;

import maths.RandomLocation;

import org.newdawn.slick.SlickException;

import screens.Play;
import entities.Enemy;

public class EnemySpawner {

	private boolean wordAlreadyExists;
	private RandomLocation xLoc;
	private int time;
	private boolean spawned;
	
	public EnemySpawner() throws SlickException {
		xLoc = new RandomLocation();
		spawned = true;
		time = 0;
	}
	
	public void timedSpawn() throws SlickException{
		time = Play.secondsPlayed;
		time %= 3; 
		if ((Play.secondsPlayed > 0 && Play.secondsPlayed <= 20) && (time != 0 && time == 2)){
				addNewEnemy();
				time = 1;
		}
	}
	
	   public void addNewEnemy() throws SlickException{
		   // Gets a new random X location.
		   int randX = xLoc.getX();
		   
		   // Makes an enemy at (randX, 0), but doesn't display it on the screen.
		   Enemy enemy = new Enemy(randX);

		   
		// Runs at least once, then loops if the new enemy's word is the same as any of the current enemies.
		   do{
			   // Words are innocent until proven guilty.
			   wordAlreadyExists  = false;
			   // If there are still words that haven't been used.
			   if (Play.enemiesOnScreen.size() < Play.words.size() && Play.enemiesOnScreen.size() != 0){
				   // Loops through enemies currently on the screen.
				   for(int i = 0; i < Play.enemiesOnScreen.size(); i++){
					   // Check to see if the new enemy has a word that already exists.
					   if(enemy.getWord().equals(Play.enemiesOnScreen.get(i).getWord())){
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
			   else if (Play.enemiesOnScreen.size() == 0){
				   wordAlreadyExists = false;
			   } 
			   // If there are not anymore unused words in the list.
			   else if(Play.enemiesOnScreen.size() == Play.words.size()){
				   // The word must already exist.
				   wordAlreadyExists = true;
				   // But we need to break out because that will remain true until an enemy is shot or reaches the bottom.
				   break;
			   }
		   } while(wordAlreadyExists);
		   
		   if (!wordAlreadyExists){
			   // Add the enemy to the screen.
			   Play.enemiesOnScreen.add(enemy);
		   }
	   }

}
