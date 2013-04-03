package entities;

import java.io.Serializable;

/**
 * Score's store a player's name and score.
 * It has a custom toString() method for returning them.
 * 
 * @author Jonathan Sterling
 *
 */
@SuppressWarnings("serial")
public class Score implements Serializable {

	private int score;
	private String playerName;
	
	/**
	 * 
	 * @param playerName The name of the player.
	 * @param score The numerical score that the player earned.
	 */
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
