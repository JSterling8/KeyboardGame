package controllers;

import java.util.ArrayList;

/**
 * This class stores all of the important information statically so other classes can use it.
 * For example it has the difficulty, score, health, totalKilled, etc.
 * 
 * @author Jonathan Sterling
 *
 */
public class Settings {

	public static int difficulty;							// The difficulty chosen.
	
	public static int score;								// The current player's score.
	public static ArrayList<Integer> scoreByPlayer;			// Each player's score (for multiplayer).
	
	public static int totalMissed;							// The current player's number of enemy's missed this level. 

	public static int totalKilled;							// The current player's number of enemy's killed this level.

	public static int health;								// The current player's health.
	public static ArrayList<Integer> healthByPlayer;		// Each player's health (for multiplayer).

	public static int level;								// The current level.
	public static int players;								// The number of players.
	
	// Makes it so an object of this class will never exist.
	private Settings() {
		// Just in case the constructor accidentally gets called from inside this class:
		throw new AssertionError();
	}
	
}
