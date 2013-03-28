package entities;

import java.util.ArrayList;

import screens.Play;
import controllers.ScoreCon;
import controllers.Settings;

public class Save {

	private int difficulty;
	private int level;
	private int score;
	private int totalMissed;
	private int totalKilled;
	private int health;
	private int secondsPlayed;
	private WordList words;
	private ArrayList<Enemy> enemiesOnScreen;
	private ArrayList<Bullet> bulletList;
	private ScoreCon scoreCon;
	private boolean bombOnScreen;
	private boolean fullhealthOnScreen;
	
	
	
	public Save(Play parent) {
		setDifficulty(Settings.difficulty);
		setLevel(Settings.level);
		setScore(Settings.score);
		setTotalMissed(Settings.totalMissed);
		setTotalKilled(Settings.totalKilled);
		setHealth(Settings.health);
		setWords(Play.words);
		setEnemiesOnScreen(Play.enemiesOnScreen);
		setSecondsPlayed(Play.secondsPlayed);
		setBulletList(Play.bulletList);
		setScoreCon(parent.getScoreCon());
		setBombOnScreen(parent.getBombOnScreen());
		setFullhealthOnScreen(parent.getFullhealthOnScreen());
		
	}


	public int getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
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

	public boolean getBombOnScreen() {
		return bombOnScreen;
	}


	public void setBombOnScreen(boolean bombOnScreen) {
		this.bombOnScreen = bombOnScreen;
	}


	public boolean getFullhealthOnScreen() {
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


	public ScoreCon getScoreCon() {
		return scoreCon;
	}


	public void setScoreCon(ScoreCon scoreCon) {
		this.scoreCon = scoreCon;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}

}
