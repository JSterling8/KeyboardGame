package controllers;


/**
 * This class stores all of the important information statically so other classes can use it.
 * For example it has the difficulty, score, health, totalKilled, etc.
 * 
 * @author Jonathan Sterling
 *
 */
public class Settings {

	public static int difficulty;							// The difficulty chosen.
	
	public static int currentPlayer;					// The current player.
	
	public static int players;								// The number of players.
	
	public static int score;								// The player's score.
	
	public static int totalMissed;							// The player's number of enemy's missed this level. 

	public static int totalKilled;							// The player's number of enemy's killed this level.

	public static int health;								// The player's health.

	public static int level;								// The current level.
	
	// Makes it so an object of this class will never exist.
	private Settings() {
		// Just in case the constructor accidentally gets called from inside this class:
		throw new AssertionError();
	}
	
}
