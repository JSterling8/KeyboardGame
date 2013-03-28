package controllers;

public class Settings {

	public static int difficulty;
	
	public static int score;
	
	public static int totalMissed;
	
	public static int totalKilled;

	public static int health;
	public static boolean fromSave;
	public static int level;
	public static int players;
	
	// Makes it so an object of this class will never exist.
	private Settings() {
		// Just in case the constructor accidentally gets called from inside this class:
		throw new AssertionError();
	}
	
}
