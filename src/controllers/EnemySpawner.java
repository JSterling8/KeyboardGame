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
		spawned = false;
		time = 0;
	}

	public void timedSpawn() throws SlickException{
		time = Play.secondsPlayed;
		
		// From 1-10 seconds in, spawn an enemy every 4 seconds.
		if (!spawned && (Play.secondsPlayed > 0 && Play.secondsPlayed <= 10) && ((time % 4) == 0)){
			addNewEnemy();
			spawned = true;
		}
		else if (spawned && (Play.secondsPlayed > 0 && Play.secondsPlayed <= 10) && ((time % 4) == 1)){
			spawned = false;
		}

		// From 11 - 20 seconds in, spawn an enemy every 3 seconds.
		if (!spawned && (Play.secondsPlayed > 10 && Play.secondsPlayed <= 20) && ((time % 3) == 0)){
			addNewEnemy();
			spawned = true;
		}
		else if (spawned && (Play.secondsPlayed > 10 && Play.secondsPlayed <= 20) && ((time % 3) == 1)){
			spawned = false;
		}

		// From 21 - 40 seconds in, spawn an enemy every 2 seconds.
		if (!spawned && (Play.secondsPlayed > 20 && Play.secondsPlayed <= 40) && ((time % 2) == 0)){
			addNewEnemy();
			spawned = true;
		}
		else if (spawned && (Play.secondsPlayed > 20 && Play.secondsPlayed <= 40) && ((time % 2) == 1)){
			spawned = false;
		}
		
		// From 41 - 60 seconds in, spawn two enemies every two seconds.
		if (!spawned && (Play.secondsPlayed > 40 && Play.secondsPlayed <= 60) && ((time % 2) == 0)){
			addNewEnemy();
			addNewEnemy();
			spawned = true;
		}
		else if (spawned && (Play.secondsPlayed > 40 && Play.secondsPlayed <= 60) && ((time % 2) == 1)){
			spawned = false;
		}
		
		// From 61 - 90 seconds in, spawn three enemies every two seconds.
		if (!spawned && (Play.secondsPlayed > 60 && Play.secondsPlayed <= 90) && ((time % 2) == 0)){
			addNewEnemy();
			addNewEnemy();
			addNewEnemy();
			spawned = true;
		}
		else if (spawned && (Play.secondsPlayed > 60 && Play.secondsPlayed <= 90) && ((time % 2) == 1)){
			spawned = false;
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
