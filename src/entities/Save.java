package entities;

import java.util.ArrayList;

import controllers.Settings;

import screens.Play;

public class Save {

	private int difficulty;
	private int wordSize;
	private int score;
	private int totalMissed;
	private int totalKilled;
	private int health;
	private int secondsPlayed;
	private WordList words;
	private ArrayList<Enemy> enemiesOnScreen;
	private ArrayList<Bullet> bulletList;
	private boolean wordListGenerated;
	private boolean bombOnScreen;
	private boolean fullhealthOnScreen;
	
	
	
	public Save() {
		setDifficulty(Settings.difficulty);
		setWordSize(Settings.wordSize);
		setScore(Settings.score);
		setTotalMissed(Settings.totalMissed);
		setTotalKilled(Settings.totalKilled);
		setHealth(Settings.health);
		setWordListGenerated(true);
		setWords(Play.words);
		setEnemiesOnScreen(Play.enemiesOnScreen);
		setSecondsPlayed(Play.secondsPlayed);
		setBulletList(Play.bulletList);
	}


	public int getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}


	public int getWordSize() {
		return wordSize;
	}


	public void setWordSize(int wordSize) {
		this.wordSize = wordSize;
	}
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getTotalMissed() {
		return totalMissed;
	}


	public void setTotalMissed(int totalMissed) {
		this.totalMissed = totalMissed;
	}


	public int getTotalKilled() {
		return totalKilled;
	}


	public void setTotalKilled(int totalKilled) {
		this.totalKilled = totalKilled;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getSecondsPlayed() {
		return secondsPlayed;
	}


	public void setSecondsPlayed(int secondsPlayed) {
		this.secondsPlayed = secondsPlayed;
	}


	public boolean isWordListGenerated() {
		return wordListGenerated;
	}


	public void setWordListGenerated(boolean wordListGenerated) {
		this.wordListGenerated = wordListGenerated;
	}


	public boolean isBombOnScreen() {
		return bombOnScreen;
	}


	public void setBombOnScreen(boolean bombOnScreen) {
		this.bombOnScreen = bombOnScreen;
	}


	public boolean isFullhealthOnScreen() {
		return fullhealthOnScreen;
	}


	public void setFullhealthOnScreen(boolean fullhealthOnScreen) {
		this.fullhealthOnScreen = fullhealthOnScreen;
	}


	public WordList getWords() {
		return words;
	}


	public void setWords(WordList words) {
		this.words = words;
	}


	public ArrayList<Enemy> getEnemiesOnScreen() {
		return enemiesOnScreen;
	}


	public void setEnemiesOnScreen(ArrayList<Enemy> enemiesOnScreen) {
		this.enemiesOnScreen = enemiesOnScreen;
	}


	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}


	public void setBulletList(ArrayList<Bullet> bulletList) {
		this.bulletList = bulletList;
	}

}
