package entities;

import java.io.Serializable;
import java.util.ArrayList;

import controllers.Settings;

public class Score implements Serializable {

	private int score;
	private String playerName;
	
	public Score(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
		
	}
	
	public String toString(){
		return playerName + "\n" +
				"Score: " + score + "\n";
	}

	public int getScore() {
		return score;
	}

	public String getPlayerName() {
		return playerName;
	}

	
	
}
